package br.com.wnascimento.entreguei.features.address.list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class ListAddressesActivity extends DaggerAppCompatActivity implements ListAddressesContract.View, ListAddressesRowAdapter.CallbackAddressList {

    @Inject
    ListAddressesContract.Presenter listAddressPresenter;

    @BindView(R.id.list_address)
    RecyclerView listAddress;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void start(Context context) {
        Intent starter = new Intent(context, ListAddressesActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_addresses);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(R.string.title_my_addresses);
        }
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
        listAddress.setAdapter(new ListAddressesRowAdapter(addressList, this));
    }

    @Override
    public void notifyEmptyList() {
        Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifyAddressRemoved() {

    }

    @Override
    public void onLongClickItem(int cep) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_warning)
                .setMessage(R.string.message_remove_address)
                .setPositiveButton(R.string.action_yes, (dialogInterface, i) -> {
                    listAddressPresenter.removeAddress(cep);
                })
                .setNegativeButton(R.string.action_no, null)
                .show();
    }

}
