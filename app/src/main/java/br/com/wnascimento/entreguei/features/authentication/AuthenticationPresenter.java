package br.com.wnascimento.entreguei.features.authentication;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;

public class AuthenticationPresenter implements AuthenticationContract.Presenter {

    private final AuthenticationContract.View registerView;
    private final RegisterUseCase registerUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public AuthenticationPresenter(AuthenticationContract.View registerView, RegisterUseCase registerUseCase) {
        this.registerView = registerView;
        this.registerUseCase = registerUseCase;
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {
        compositeDisposable.clear();
    }

    @Override
    public void register(String email, String password) {
        registerView.showProgress();
        RegisterUseCase.Request request = new RegisterUseCase.Request(email, password);
        Disposable disposable = registerUseCase.create(request)
                .doFinally(registerView::hideProgress)
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        registerView.toAddresses();
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerView.showErrorRegister();
                    }
                });

        compositeDisposable.add(disposable);
    }


}
