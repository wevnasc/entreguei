package br.com.wnascimento.entreguei.features.authentication;


import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;

import br.com.wnascimento.entreguei.shared.preferences.ApplicationPreferencesInterface;
import br.com.wnascimento.entreguei.util.ImmediateScheduler;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LogoutUseCaseTest {

    private static final String EMAIL_TEST = "EMAIL";
    private static final String PASSWORD_TEST = "PASSWORD";

    @ClassRule
    public static final ImmediateScheduler schedulers = new ImmediateScheduler();

    @Mock
    private ApplicationPreferencesInterface applicationPreferences;

    private LogoutUseCase logoutUseCase;

    @Before
    public void setup() {
        initMocks(this);
        logoutUseCase = new LogoutUseCase(Schedulers.newThread(), AndroidSchedulers.mainThread(),  applicationPreferences);
    }

    @Test
    public void shouldReturnClearSession() {
        logoutUseCase.execute(new LogoutUseCase.Request())
                .subscribe();

        verify(applicationPreferences).clear();
    }

}
