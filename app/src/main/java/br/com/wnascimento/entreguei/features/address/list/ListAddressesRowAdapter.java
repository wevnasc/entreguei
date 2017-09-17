package br.com.wnascimento.entreguei.features.address.list;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.wnascimento.entreguei.R;
import br.com.wnascimento.entreguei.features.address.Address;
import br.com.wnascimento.entreguei.features.address.detail.AddressDetailActivity;
import br.com.wnascimento.entreguei.util.AnimationUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAddressesRowAdapter extends RecyclerView.Adapter<ListAddressesRowAdapter.ViewHolder> {

    private final List<Address> addressList;
    private final CallbackAddressList callbackAddressList;


    public ListAddressesRowAdapter(List<Address> addressList, CallbackAddressList callbackAddressList) {
        this.addressList = addressList;
        this.callbackAddressList = callbackAddressList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_address, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Address address = addressList.get(position);
        holder.cityAnStateText.setText(address.getCityWithState());
        holder.neighborhoodText.setText(address.getNeighborhood());
        holder.cepText.setText(address.getCapFormatted());

        holder.itemView.setOnClickListener(v -> showDetail(holder, address));
        holder.itemView.setOnLongClickListener(v -> {
            callbackAddressList.onLongClickItem(address.getCepToInt());
            return false;
        });

        AnimationUtil.fade(holder.itemView, 1000);

    }

    private void showDetail(ViewHolder v, Address address) {

        Activity context = (Activity) v.itemView.getContext();

        Intent intent = new Intent(context, AddressDetailActivity.class);
        intent.putExtra(AddressDetailActivity.EXTRA_ADDRESS, address);

        Pair<View, String> cityAnState = Pair.create(v.cityAnStateText, "city_and_state");
        Pair<View, String> neighborhood = Pair.create(v.neighborhoodText, "neighborhood");
        Pair<View, String> cep = Pair.create(v.cepText, "cep");

        AddressDetailActivity.start(context, address, cityAnState , neighborhood, cep);

    }

    @Override
    public int getItemCount() {
        return addressList != null ? addressList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_and_state_text)
        TextView cityAnStateText;

        @BindView(R.id.neighborhood_text)
        TextView neighborhoodText;

        @BindView(R.id.cep_text)
        TextView cepText;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface CallbackAddressList{

        void onLongClickItem(int id);
    }

}
