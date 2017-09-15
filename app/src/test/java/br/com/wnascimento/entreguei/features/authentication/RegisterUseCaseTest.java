package br.com.wnascimento.entreguei.features.authentication;


import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;

import br.com.wnascimento.entreguei.util.ImmediateScheduler;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegisterUseCaseTest {

    private static final String EMAIL_TEST = "EMAIL";
    private static final String PASSWORD_TEST = "PASSWORD";

    @ClassRule
    public static final ImmediateScheduler schedulers = new ImmediateScheduler();

    @Mock
    private UserLocalRepository userLocalRepository;

    private RegisterUseCase registerUseCase;

    @Before
    public void setup() {
        initMocks(this);
        registerUseCase = new RegisterUseCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), userLocalRepository);
    }

    @Test
    public void shouldRegisterNewMotoboy() {

        User user = mock(User.class);
        when(userLocalRepository.registerNew(user))
                .thenReturn(Completable.complete());

        registerUseCase
                .create(new RegisterUseCase.Request(EMAIL_TEST, PASSWORD_TEST));

        verify(userLocalRepository).registerNew(any(User.class));
    }

}
