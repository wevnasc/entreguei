package br.com.wnascimento.entreguei.shared.usecase;


import io.reactivex.Flowable;
import io.reactivex.Scheduler;

public abstract class FlowableUseCase <T, R extends FlowableUseCase.Request> {

    private final Scheduler executor;
    private final Scheduler main;

    protected FlowableUseCase(Scheduler executor, Scheduler main) {
        this.executor = executor;
        this.main = main;
    }

    protected abstract Flowable<T> create(R request);

    public Flowable<T> execute(R request) {
        return create(request)
                .subscribeOn(executor)
                .observeOn(main);
    }

    public static abstract class Request {
    }
}
