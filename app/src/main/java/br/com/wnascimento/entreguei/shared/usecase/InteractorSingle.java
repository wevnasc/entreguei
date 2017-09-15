package br.com.wnascimento.entreguei.shared.usecase;

import io.reactivex.Scheduler;
import io.reactivex.Single;


public abstract class InteractorSingle<T, R extends InteractorSingle.Request> {

    private final Scheduler executor;
    private final Scheduler main;

    protected InteractorSingle(Scheduler executor, Scheduler main) {
        this.executor = executor;
        this.main = main;
    }

    protected abstract Single<T> create(R request);

    public Single<T> execute(R request) {
        return create(request)
                .subscribeOn(executor)
                .observeOn(main);
    }

    public static abstract class Request {
    }
}
