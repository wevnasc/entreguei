package br.com.wnascimento.entreguei.features.authentication;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.shared.scheduler.IOScheduler;
import br.com.wnascimento.entreguei.shared.scheduler.MainScheduler;
import br.com.wnascimento.entreguei.shared.usecase.InteractorCompletable;
import io.reactivex.Completable;
import io.reactivex.Scheduler;

class LoginUseCase extends InteractorCompletable<LoginUseCase.Request> {

    private final UserLocalRepository userLocalRepository;

    @Inject
    public LoginUseCase(@IOScheduler Scheduler executor, @MainScheduler Scheduler main, UserLocalRepository userLocalRepository) {
        super(executor, main);
        this.userLocalRepository = userLocalRepository;
    }

    @Override
    protected Completable create(Request request) {
        return userLocalRepository.getUserByEmailAndPassword(request.getEmail(),
                request.getPassword())
                .flatMapCompletable(motoboy -> Completable.complete());
    }

    public static final class Request extends InteractorCompletable.Request {

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