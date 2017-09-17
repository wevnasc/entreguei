package br.com.wnascimento.entreguei.features.address.list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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

    @BindView(R.id.empty_list)
    LinearLayout emptyList;

    @BindView(R.id.progress)
    ProgressBar progress;

    ListAddressesRowAdapter listAddressesRowAdapter;


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
        if (actionBar != null) {
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
        progress.setVisibility(View.VISIBLE);
        listAddress.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
        listAddress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddresses(List<Address> addressList) {
        listAddress.setVisibility(View.VISIBLE);
        emptyList.setVisibility(View.GONE);
        listAddress.setLayoutManager(new LinearLayoutManager(this));
        listAddressesRowAdapter = new ListAddressesRowAdapter(addressList, this);
        listAddress.setAdapter(listAddressesRowAdapter);
    }

    @Override
    public void notifyEmptyList() {
        listAddress.setVisibility(View.GONE);
        emptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void notifyAddressRemoved() {

    }

    @Override
    public void onLongClickItem(int cep, int position) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_warning)
                .setMessage(R.string.message_remove_address)
                .setPositiveButton(R.string.action_yes, (dialogInterface, i) -> {
                    listAddressPresenter.removeAddress(cep);
                    listAddressesRowAdapter.removeItem(position);
                    if(listAddressesRowAdapter.getItemCount() == 0) {
                        notifyEmptyList();
                    }
                })
                .setNegativeButton(R.string.action_no, null)
                .show();
    }

}
