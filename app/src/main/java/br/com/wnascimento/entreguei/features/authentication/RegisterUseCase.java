package br.com.wnascimento.entreguei.features.authentication;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.shared.scheduler.ExecutorScheduler;
import br.com.wnascimento.entreguei.shared.scheduler.MainScheduler;
import br.com.wnascimento.entreguei.shared.usecase.InteractorCompletable;
import io.reactivex.Completable;
import io.reactivex.Scheduler;

public class RegisterUseCase extends InteractorCompletable<RegisterUseCase.Request> {

    private final MotoboyRepository motoboyRepository;

    @Inject
    public RegisterUseCase(@ExecutorScheduler Scheduler executor, @MainScheduler Scheduler main, MotoboyRepository motoboyRepository) {
        super(executor, main);
        this.motoboyRepository = motoboyRepository;
    }

    @Override
    protected Completable create(Request request) {
        Motoboy motoboy = new Motoboy(request.getEmail(), request.getPassword());
        return motoboyRepository.registerNew(motoboy);
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
