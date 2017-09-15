package br.com.wnascimento.entreguei.features.address.search;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class SearchAddressActivity extends DaggerAppCompatActivity implements SearchAddressesContract.View {

    @BindView(R.id.address_search)
    SearchView addressSearch;

    @BindView(R.id.city_text)
    TextView cityText;

    @BindView(R.id.separator_text)
    TextView separatorText;

    @BindView(R.id.state_text)
    TextView stateText;

    @BindView(R.id.street_text)
    TextView streetText;

    @BindView(R.id.neighborhood_text)
    TextView neighborhoodText;

    @BindView(R.id.complement_text)
    TextView complementText;

    @BindView(R.id.cep_text)
    TextView cepText;

    @Inject
    SearchAddressesContract.Presenter searchAddressPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        addressSearch.setOnQueryTextListener(searchAddress);
    }


    private SearchView.OnQueryTextListener searchAddress = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            searchAddressPresenter.searchAddress(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showAddressInformation(Address address) {
        cityText.setText(address.getCity());
        stateText.setText(address.getState());
        streetText.setText(address.getStreet());
        neighborhoodText.setText(address.getNeighborhood());
        complementText.setText(address.getComplement());
        cepText.setText(address.getCep());
    }

    @Override
    public void showErrorAddressNotFound() {

    }
}