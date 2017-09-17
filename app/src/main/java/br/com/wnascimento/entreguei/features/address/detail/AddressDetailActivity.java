package br.com.wnascimento.entreguei.features.address.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import br.com.wnascimento.entreguei.R;
import br.com.wnascimento.entreguei.features.address.Address;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ADDRESS = "EXTRA_ADDRESS";

    @BindView(R.id.city_text)
    TextView cityText;

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

    public static void start(Activity context, Address address, Pair<View, String>... views) {
        Intent starter = new Intent(context, AddressDetailActivity.class);
        starter.putExtra(EXTRA_ADDRESS, address);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, views);
        context.startActivity(starter, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_detail);
        ButterKnife.bind(this);
        showCollaboratorDetail();
    }

    private void showCollaboratorDetail() {
        Bundle data = getIntent().getExtras();
        if(data != null) {
            Address address = (Address) data.getSerializable(EXTRA_ADDRESS);

            if(address != null) {
                cityText.setText(address.getCity());
                stateText.setText(address.getState());
                streetText.setText(address.getStreet());
                neighborhoodText.setText(address.getNeighborhood());
                complementText.setText(address.getComplement());
                cepText.setText(address.getCep());
            }
        }
    }
}
