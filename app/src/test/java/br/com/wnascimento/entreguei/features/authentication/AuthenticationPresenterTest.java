package br.com.wnascimento.entreguei.features.authentication;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Completable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthenticationPresenterTest {

    private static final String EMAIL_TEST = "EMAIL";
    private static final String PASSWORD_TEST = "PASSWORD";

    @Mock
    private RegisterUseCase registerUseCase;

    @Mock
    private LoginUseCase loginUseCase;


    @Mock
    private AuthenticationContract.View authenticationView;

    private AuthenticationContract.Presenter authenticationPresenter;

    @Before
    public void setup() {
        initMocks(this);
        authenticationPresenter = new AuthenticationPresenter(authenticationView, registerUseCase, loginUseCase);
    }

    @Test
    public void onClickInRegister_ShowAddresses() {

        registerMotoboy(Completable.complete());

        verify(authenticationView).showProgress();
        verify(authenticationView).toMain();
        verify(authenticationView).hideProgress();

    }

    @Test
    public void onClickInRegister_showError() {

        registerMotoboy(Completable.error(mock(Exception.class)));

        verify(authenticationView).showProgress();
        verify(authenticationView).showErrorRegister();
        verify(authenticationView).hideProgress();

    }

    private void registerMotoboy(Completable complete) {
        when(loginUseCase.execute(any(LoginUseCase.Request.class)))
                .thenReturn(Completable.complete());

        when(registerUseCase.execute(any(RegisterUseCase.Request.class)))
                .thenReturn(complete);

        authenticationPresenter.register(EMAIL_TEST, PASSWORD_TEST);
    }



    @Test
    public void onClickLogin_showAddresses() {

        login(Completable.complete());

        verify(authenticationView).showProgress();
        verify(authenticationView).hideProgress();
        verify(authenticationView).toMain();

    }

    @Test
    public void onClickLogin_showLoginError() {

        login(Completable.error(mock(Exception.class)));

        verify(authenticationView).showProgress();
        verify(authenticationView).hideProgress();
        verify(authenticationView).showErrorLogin();

    }

    private void login(Completable error) {
        when(loginUseCase.execute(any(LoginUseCase.Request.class)))
                .thenReturn(error);

        authenticationPresenter.login(EMAIL_TEST, PASSWORD_TEST);
    }

}
