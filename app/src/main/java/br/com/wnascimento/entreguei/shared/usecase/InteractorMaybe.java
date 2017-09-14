package br.com.wnascimento.entreguei.shared.usecase;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;


public abstract class InteractorMaybe<T, R extends InteractorMaybe.Request> {

    private final Scheduler executor;
    private final Scheduler main;

    protected InteractorMaybe(Scheduler executor, Scheduler main) {
        this.executor = executor;
        this.main = main;
    }

    protected abstract Maybe<T> create(R request);

    public Maybe<T> execute(R request) {
        return create(request)
                .subscribeOn(executor)
                .observeOn(main);
    }

    public static abstract class Request {
    }
}
