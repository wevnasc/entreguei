package br.com.wnascimento.entreguei.features.address.search;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.features.address.Address;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class SearchAddressesPresenter implements SearchAddressesContract.Presenter {

    private final SearchAddressesContract.View searchAddressView;
    private final SearchAddressUseCase searchAddressUseCase;
    private final SaveAddressUseCase saveAddressUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public SearchAddressesPresenter(SearchAddressesContract.View searchAddressView, SearchAddressUseCase searchAddressUseCase, SaveAddressUseCase saveAddressUseCase) {
        this.searchAddressView = searchAddressView;
        this.searchAddressUseCase = searchAddressUseCase;
        this.saveAddressUseCase = saveAddressUseCase;
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {
        compositeDisposable.clear();
    }

    @Override
    public void searchAddress(String cep) {
        searchAddressView.showProgress();
        Disposable disposable = searchAddressUseCase.execute(new SearchAddressUseCase.Request(cep))
                .subscribeWith(new DisposableSingleObserver<Address>() {

                    @Override
                    public void onSuccess(Address address) {
                        searchAddressView.hideProgress();
                        if(address.getCep() == null) {
                            searchAddressView.showErrorAddressNotFound();
                        } else {
                            searchAddressView.showAddressInformation(address);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        searchAddressView.hideProgress();
                        searchAddressView.showErrorAddressNotFound();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void saveAddress(Address address) {
        searchAddressView.showProgress();
        Disposable disposable = saveAddressUseCase.execute(new SaveAddressUseCase.Request(address))
                .doFinally(searchAddressView::hideProgress)
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        searchAddressView.notifySaveSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        searchAddressView.notifySaveError();
                    }
                });

        compositeDisposable.add(disposable);
    }
}
