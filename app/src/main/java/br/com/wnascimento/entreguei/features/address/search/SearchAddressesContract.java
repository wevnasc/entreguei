package br.com.wnascimento.entreguei.features.address.search;


import br.com.wnascimento.entreguei.features.address.Address;
import br.com.wnascimento.entreguei.shared.BaseContract;

public interface SearchAddressesContract {

    interface Presenter extends BaseContract.Presenter {
        void searchAddress(String cep);

        void saveAddress(Address address);
    }

    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showAddressInformation(Address address);

        void showErrorAddressNotFound();

        void notifySaveSuccess();

        void notifySaveError();
    }

}
