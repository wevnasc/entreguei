package br.com.wnascimento.entreguei.features.address.list;


import java.util.List;

import br.com.wnascimento.entreguei.features.address.Address;
import br.com.wnascimento.entreguei.shared.BaseContract;

public interface ListAddressesContract {


    interface View extends BaseContract.View{

        void showProgress();

        void hideProgress();

        void showAddresses(List<Address> addressList);

        void notifyEmptyList();

        void notifyAddressRemoved();

        void toAuthentication();
    }

    interface Presenter extends BaseContract.Presenter {

        void listAddresses();

        void removeAddress(String cep);

        void logout();
    }

}
