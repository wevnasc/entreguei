package br.com.wnascimento.entreguei.features.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.wnascimento.entreguei.R;
import br.com.wnascimento.entreguei.features.address.search.SearchAddressActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresses);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.search_address_button)
    public void onClick() {
        SearchAddressActivity.start(this);
    }
}
