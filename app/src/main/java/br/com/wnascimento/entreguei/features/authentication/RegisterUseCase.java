package br.com.wnascimento.entreguei.features.authentication;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.shared.preferences.ApplicationPreferencesInterface;
import br.com.wnascimento.entreguei.shared.scheduler.IOScheduler;
import br.com.wnascimento.entreguei.shared.scheduler.MainScheduler;
import br.com.wnascimento.entreguei.shared.usecase.CompletableUseCase;
import io.reactivex.Completable;
import io.reactivex.Scheduler;

public class RegisterUseCase extends CompletableUseCase<RegisterUseCase.Request> {

    private final UserLocalRepository userLocalRepository;


    @Inject
    public RegisterUseCase(@IOScheduler Scheduler executor, @MainScheduler Scheduler main, UserLocalRepository userLocalRepository) {
        super(executor, main);
        this.userLocalRepository = userLocalRepository;
    }

    @Override
    protected Completable create(Request request) {
        User user = new User(request.getEmail(), request.getPassword());
        return userLocalRepository.registerNew(user);
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
