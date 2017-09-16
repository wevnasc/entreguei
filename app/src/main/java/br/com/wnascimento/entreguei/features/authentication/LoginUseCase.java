package br.com.wnascimento.entreguei.features.authentication;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.shared.preferences.ApplicationPreferencesInterface;
import br.com.wnascimento.entreguei.shared.scheduler.IOScheduler;
import br.com.wnascimento.entreguei.shared.scheduler.MainScheduler;
import br.com.wnascimento.entreguei.shared.usecase.CompletableUseCase;
import io.reactivex.Completable;
import io.reactivex.Scheduler;

class LoginUseCase extends CompletableUseCase<LoginUseCase.Request> {

    private final UserLocalRepository userLocalRepository;

    private final ApplicationPreferencesInterface applicationPreferences;

    @Inject
    public LoginUseCase(@IOScheduler Scheduler executor, @MainScheduler Scheduler main, UserLocalRepository userLocalRepository, ApplicationPreferencesInterface applicationPreferences) {
        super(executor, main);
        this.userLocalRepository = userLocalRepository;
        this.applicationPreferences = applicationPreferences;
    }

    @Override
    protected Completable create(Request request) {
        return userLocalRepository.getUserByEmailAndPassword(request.getEmail(),
                request.getPassword())
                .flatMapCompletable(user ->
                        Completable.fromAction(() ->
                                applicationPreferences.saveCurrentUser(user)));
    }

    public static final class Request extends CompletableUseCase.Request {

        private final String email;
        private final String password;


        public Request(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

}
