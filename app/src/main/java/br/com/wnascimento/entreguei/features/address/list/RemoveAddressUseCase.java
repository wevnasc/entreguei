package br.com.wnascimento.entreguei.features.address.list;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.features.address.search.AddressLocalRepository;
import br.com.wnascimento.entreguei.shared.scheduler.IOScheduler;
import br.com.wnascimento.entreguei.shared.scheduler.MainScheduler;
import br.com.wnascimento.entreguei.shared.usecase.CompletableUseCase;
import io.reactivex.Completable;
import io.reactivex.Scheduler;


class RemoveAddressUseCase extends CompletableUseCase<RemoveAddressUseCase.Request> {

    private final AddressLocalRepository addressLocalRepository;

    @Inject
    protected RemoveAddressUseCase(@IOScheduler Scheduler executor, @MainScheduler Scheduler main, AddressLocalRepository addressLocalRepository) {
        super(executor, main);
        this.addressLocalRepository = addressLocalRepository;
    }

    @Override
    protected Completable create(Request request) {
        return addressLocalRepository.removeAddress(request.getCep());
    }

    public static final class Request extends CompletableUseCase.Request {

        private final int cep;

        public Request(int cep) {
            this.cep = cep;
        }

        public int getCep() {
            return cep;
        }
    }
}
