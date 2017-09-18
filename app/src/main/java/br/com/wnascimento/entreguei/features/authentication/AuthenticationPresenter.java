package br.com.wnascimento.entreguei.features.authentication;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;

public class AuthenticationPresenter implements AuthenticationContract.Presenter {

    private final AuthenticationContract.View authenticationView;
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public AuthenticationPresenter(AuthenticationContract.View authenticationView, RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
        this.authenticationView = authenticationView;
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
        authenticationView.showProgress();
        Disposable disposable = registerUseCase.execute(new RegisterUseCase.Request(email, password))
                .doFinally(authenticationView::hideProgress)
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        authenticationView.toMain();
                    }

                    @Override
                    public void onError(Throwable e) {
                        authenticationView.showErrorRegister();
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void login(String email, String password) {
        authenticationView.showProgress();
        Disposable disposable = loginUseCase.execute(new LoginUseCase.Request(email, password))
                .doFinally(authenticationView::hideProgress)
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        authenticationView.toMain();
                    }

                    @Override
                    public void onError(Throwable e) {
                        authenticationView.showErrorLogin();
                    }
                });

        compositeDisposable.add(disposable);

    }




}
