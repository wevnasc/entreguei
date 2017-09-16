package br.com.wnascimento.entreguei.features.address.list;

import java.util.List;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.features.address.Address;
import br.com.wnascimento.entreguei.features.address.search.AddressLocalRepository;
import br.com.wnascimento.entreguei.shared.scheduler.IOScheduler;
import br.com.wnascimento.entreguei.shared.scheduler.MainScheduler;
import br.com.wnascimento.entreguei.shared.usecase.SingleUseCase;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class ListAddressesUseCase extends SingleUseCase<List<Address>, ListAddressesUseCase.Request>{

    private final AddressLocalRepository addressLocalRepository;

    @Inject
    protected ListAddressesUseCase(@IOScheduler Scheduler executor, @MainScheduler Scheduler main, AddressLocalRepository addressDatabaseRepository) {
        super(executor, main);
        addressLocalRepository = addressDatabaseRepository;
    }

    @Override
    protected Single<List<Address>> create(Request request) {
        return addressLocalRepository.getAddresses()
                .sorted((a1, a2) -> a1.getCity().compareTo(a2.getCity()))
                .toList();
    }

    public static final class Request extends SingleUseCase.Request {
    }

}
