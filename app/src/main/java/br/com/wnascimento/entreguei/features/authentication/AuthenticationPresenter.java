package br.com.wnascimento.entreguei.features.authentication;


import javax.inject.Inject;

import br.com.wnascimento.entreguei.shared.exception.MotoboyNotFoundException;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;

public class AuthenticationPresenter implements AuthenticationContract.Presenter {

    private final AuthenticationContract.View registerView;
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public AuthenticationPresenter(AuthenticationContract.View registerView, RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
        this.registerView = registerView;
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
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
        Disposable disposable = registerUseCase.execute(new RegisterUseCase.Request(email, password))
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

    @Override
    public void login(String email, String password) {
        registerView.showProgress();
        Disposable disposable = loginUseCase.execute(new LoginUseCase.Request(email, password))
                .doFinally(registerView::hideProgress)
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        registerView.toAddresses();
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerView.showErrorLogin();
                    }
                });

        compositeDisposable.add(disposable);

    }


}
