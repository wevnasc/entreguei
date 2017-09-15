package br.com.wnascimento.entreguei.features.address.search;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class SearchAddressesPresenter implements SearchAddressesContract.Presenter {

    private final SearchAddressesContract.View searchAddressView;
    private final SearchAddressUseCase searchAddressUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public SearchAddressesPresenter(SearchAddressesContract.View searchAddressView, SearchAddressUseCase searchAddressUseCase) {
        this.searchAddressView = searchAddressView;
        this.searchAddressUseCase = searchAddressUseCase;
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
                .doFinally(searchAddressView::hideProgress)
                .subscribeWith(new DisposableSingleObserver<Address>() {

                    @Override
                    public void onSuccess(Address address) {
                        searchAddressView.showAddressInformation(address);
                    }

                    @Override
                    public void onError(Throwable e) {
                        searchAddressView.showErrorAddressNotFound();
                    }
                });
        compositeDisposable.add(disposable);
    }
}
