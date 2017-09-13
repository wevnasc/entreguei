package br.com.wnascimento.entreguei.features.authentication;

import br.com.wnascimento.entreguei.shared.BaseContract;

public interface AuthenticationContract {

    interface Presenter extends BaseContract.Presenter {

        void register(String email, String password);

    }

    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showErrorRegister();

        void toAddresses();

    }


}
