package br.com.wnascimento.entreguei.features.address.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;

import br.com.wnascimento.entreguei.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class SearchAddressActivity extends DaggerAppCompatActivity implements SearchAddressesContract.View {

    @BindView(R.id.address_search)
    SearchView addressSearch;

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
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };



}
