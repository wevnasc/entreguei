package br.com.wnascimento.entreguei.features.address.list;


import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;

import br.com.wnascimento.entreguei.features.address.search.AddressLocalRepository;
import br.com.wnascimento.entreguei.util.ImmediateScheduler;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RemoveAddressUseCaseTest {

    private static final String CEP_TEST = "04944050";

    @ClassRule
    public static final ImmediateScheduler schedulers = new ImmediateScheduler();

    @Mock
    private AddressLocalRepository addressLocalRepository;

    private RemoveAddressUseCase removeAddressUseCase;

    @Before
    public void setup() {
        initMocks(this);
        removeAddressUseCase = new RemoveAddressUseCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), addressLocalRepository);
    }

    @Test
    public void shouldGetAllAddress() {

        when(addressLocalRepository.removeAddress(anyString()))
                .thenReturn(Completable.complete());

        removeAddressUseCase.execute(new RemoveAddressUseCase.Request(CEP_TEST))
                .subscribe();

        verify(addressLocalRepository).removeAddress(anyString());

    }


}
