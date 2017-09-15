package br.com.wnascimento.entreguei.features.address.search;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.shared.scheduler.IOScheduler;
import br.com.wnascimento.entreguei.shared.scheduler.MainScheduler;
import br.com.wnascimento.entreguei.shared.usecase.InteractorSingle;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class SearchAddressUseCase extends InteractorSingle<Address, SearchAddressUseCase.Request> {

    private final AddressRemoteRepository addressRemoteRepository;

    @Inject
    protected SearchAddressUseCase(@IOScheduler Scheduler executor, @MainScheduler Scheduler main, AddressRemoteRepository addressRemoteRepository) {
        super(executor, main);
        this.addressRemoteRepository = addressRemoteRepository;
    }

    @Override
    protected Single<Address> create(Request request) {
        return addressRemoteRepository.getAddressByCep(request.getCep());
    }

    public static final class Request extends InteractorSingle.Request{

        private final String cep;

        public Request(String cep) {
            this.cep = cep;

        }

        public String getCep() {
            return cep;
        }
    }

}
