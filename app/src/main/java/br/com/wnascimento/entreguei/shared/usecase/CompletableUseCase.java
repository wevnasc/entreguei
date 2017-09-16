package br.com.wnascimento.entreguei.shared.usecase;

import io.reactivex.Completable;
import io.reactivex.Scheduler;


public abstract class CompletableUseCase<R extends CompletableUseCase.Request> {

    private final Scheduler executor;
    private final Scheduler main;

    protected CompletableUseCase(Scheduler executor, Scheduler main) {
        this.executor = executor;
        this.main = main;
    }

    protected abstract Completable create(R request);

    public Completable execute(R request) {
        return create(request)
                .subscribeOn(executor)
                .observeOn(main);
    }

    public static abstract class Request {
    }
}
