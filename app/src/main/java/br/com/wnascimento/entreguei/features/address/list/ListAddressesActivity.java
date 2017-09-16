package br.com.wnascimento.entreguei.features.address.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.R;
import br.com.wnascimento.entreguei.features.address.Address;
import br.com.wnascimento.entreguei.features.address.search.SearchAddressActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class ListAddressesActivity extends DaggerAppCompatActivity implements ListAddressesContract.View {

    @Inject
    ListAddressesContract.Presenter listAddressPresenter;

    @BindView(R.id.list_address)
    RecyclerView listAddress;

    public static void start(Context context) {
        Intent starter = new Intent(context, ListAddressesActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_addresses);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        listAddressPresenter.restart();
    }

    @OnClick(R.id.search_address_button)
    public void onClick() {
        SearchAddressActivity.start(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showAddresses(List<Address> addressList) {
        listAddress.setLayoutManager(new LinearLayoutManager(this));
        listAddress.setAdapter(new ListAddressesRowAdapter(addressList));
    }

    @Override
    public void notifyEmptyList() {
        Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show();
    }
}
