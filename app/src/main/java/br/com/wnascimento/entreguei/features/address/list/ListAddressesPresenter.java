package br.com.wnascimento.entreguei.features.address.list;


import java.util.List;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.features.address.Address;
import io.reactivex.observers.DisposableSingleObserver;

public class ListAddressesPresenter implements ListAddressesContract.Presenter{

    private final ListAddressesContract.View listAddressesView;
    private final ListAddressesUseCase listAddressesUseCase;

    @Inject
    public ListAddressesPresenter(ListAddressesContract.View listAddressesView, ListAddressesUseCase listAddressesUseCase) {
        this.listAddressesView = listAddressesView;
        this.listAddressesUseCase = listAddressesUseCase;
    }

    @Override
    public void restart() {
        listAddresses();
    }

    @Override
    public void stop() {

    }

    @Override
    public void listAddresses() {
        listAddressesView.showProgress();
        listAddressesUseCase.execute(new ListAddressesUseCase.Request())
                .doFinally(listAddressesView::hideProgress)
                .subscribe(new DisposableSingleObserver<List<Address>>() {
                    @Override
                    public void onSuccess(List<Address> addresses) {
                        listAddressesView.showAddresses(addresses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listAddressesView.notifyEmptyList();
                    }
                });
    }
}
