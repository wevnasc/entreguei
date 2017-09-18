package br.com.wnascimento.entreguei.features.authentication;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.shared.preferences.ApplicationPreferencesInterface;
import br.com.wnascimento.entreguei.shared.scheduler.IOScheduler;
import br.com.wnascimento.entreguei.shared.scheduler.MainScheduler;
import br.com.wnascimento.entreguei.shared.usecase.CompletableUseCase;
import io.reactivex.Completable;
import io.reactivex.Scheduler;

public class LogoutUseCase extends CompletableUseCase<LogoutUseCase.Request> {

    private final ApplicationPreferencesInterface applicationPreferences;


    @Inject
    protected LogoutUseCase(@IOScheduler Scheduler executor, @MainScheduler Scheduler main, ApplicationPreferencesInterface applicationPreferences) {
        super(executor, main);
        this.applicationPreferences = applicationPreferences;
    }

    @Override
    protected Completable create(Request request) {
        return Completable.fromAction(applicationPreferences::clear);
    }


    public static class Request extends CompletableUseCase.Request {

    }

}
