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
    private MotoboyRepository motoboyRepository;

    private RegisterUseCase registerUseCase;

    @Before
    public void setup() {
        initMocks(this);
        registerUseCase = new RegisterUseCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), motoboyRepository);
    }

    @Test
    public void shouldRegisterNewMotoboy() {

        Motoboy motoboy = mock(Motoboy.class);
        when(motoboyRepository.registerNew(motoboy))
                .thenReturn(Completable.complete());

        registerUseCase
                .create(new RegisterUseCase.Request(EMAIL_TEST, PASSWORD_TEST));

        verify(motoboyRepository).registerNew(any(Motoboy.class));
    }

}
