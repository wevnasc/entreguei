package br.com.wnascimento.entreguei.features.address.list;


import java.util.List;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.features.address.Address;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class ListAddressesPresenter implements ListAddressesContract.Presenter{

    private final ListAddressesContract.View listAddressesView;
    private final ListAddressesUseCase listAddressesUseCase;
    private final RemoveAddressUseCase removeAddressUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ListAddressesPresenter(ListAddressesContract.View listAddressesView, ListAddressesUseCase listAddressesUseCase, RemoveAddressUseCase removeAddressUseCase) {
        this.listAddressesView = listAddressesView;
        this.listAddressesUseCase = listAddressesUseCase;
        this.removeAddressUseCase = removeAddressUseCase;
    }

    @Override
    public void restart() {
        listAddresses();
    }

    @Override
    public void stop() {
        compositeDisposable.clear();
    }

    @Override
    public void listAddresses() {
        listAddressesView.showProgress();
        Disposable disposable = listAddressesUseCase.execute(new ListAddressesUseCase.Request())
                .doFinally(listAddressesView::hideProgress)
                .subscribeWith(new DisposableSingleObserver<List<Address>>() {
                    @Override
                    public void onSuccess(List<Address> addresses) {
                        listAddressesView.showAddresses(addresses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listAddressesView.notifyEmptyList();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void removeAddress(int cep) {
        listAddressesView.showProgress();
        Disposable disposable = removeAddressUseCase.execute(new RemoveAddressUseCase.Request(cep))
                .doFinally(listAddressesView::hideProgress)
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        listAddressesView.notifyAddressRemoved();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        compositeDisposable.add(disposable);
    }
}
