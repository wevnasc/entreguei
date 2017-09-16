package br.com.wnascimento.entreguei.features.address.search;


import javax.inject.Inject;

import br.com.wnascimento.entreguei.features.address.Address;
import br.com.wnascimento.entreguei.shared.scheduler.IOScheduler;
import br.com.wnascimento.entreguei.shared.scheduler.MainScheduler;
import br.com.wnascimento.entreguei.shared.usecase.CompletableUseCase;
import io.reactivex.Completable;
import io.reactivex.Scheduler;

public class SaveAddressUseCase extends CompletableUseCase<SaveAddressUseCase.Request> {

    private final AddressLocalRepository addressLocalRepository;

    @Inject
    protected SaveAddressUseCase(@IOScheduler Scheduler executor, @MainScheduler Scheduler main, AddressLocalRepository addressLocalRepository) {
        super(executor, main);
        this.addressLocalRepository = addressLocalRepository;
    }

    @Override
    protected Completable create(Request request) {
        return addressLocalRepository.save(request.getAddress());
    }

    public static class Request extends CompletableUseCase.Request {

        private final Address address;

        public Request(Address address) {
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }
    }

}
