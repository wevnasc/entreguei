package br.com.wnascimento.entreguei.features.address.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.R;
import br.com.wnascimento.entreguei.features.address.Address;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class SearchAddressActivity extends DaggerAppCompatActivity implements SearchAddressesContract.View {

    @BindView(R.id.address_search)
    SearchView addressSearch;


    @BindView(R.id.street_text)
    TextView streetText;

    @BindView(R.id.neighborhood_text)
    TextView neighborhoodText;

    @BindView(R.id.complement_text)
    TextView complementText;

    @BindView(R.id.cep_text)
    TextView cepText;

    @BindView(R.id.city_and_state_text)
    TextView cityAndStateText;

    @Inject
    SearchAddressesContract.Presenter searchAddressPresenter;


    public static void start(Context context) {
        Intent starter = new Intent(context, SearchAddressActivity.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(context,
                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        context.startActivity(starter, bundle);
    }

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
        cityAndStateText.setText(address.getCityWithState());
        streetText.setText(address.getStreet());
        neighborhoodText.setText(address.getNeighborhood());
        complementText.setText(address.getComplement());
        cepText.setText(address.getCep());
    }

    @Override
    public void showErrorAddressNotFound() {

    }

    @Override
    public void notifySaveSuccess() {
        Toast.makeText(this, "Success Address", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifySaveError() {
        Toast.makeText(this, "Error Address", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.save_button)
    public void saveAddress() {
        String [] cityAndState = cityAndStateText.getText()
                .toString()
                .split("-");

        Address address = new Address(
                cepText.getText().toString(),
                streetText.getText().toString(),
                neighborhoodText.getText().toString(),
                cityAndState[0],
                cityAndState[1],
                complementText.getText().toString()
        );

        searchAddressPresenter.saveAddress(address);
    }
}
