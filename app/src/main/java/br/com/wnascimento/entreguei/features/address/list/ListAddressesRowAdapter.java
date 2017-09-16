package br.com.wnascimento.entreguei.features.address.list;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.wnascimento.entreguei.R;
import br.com.wnascimento.entreguei.features.address.Address;
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
        holder.cityText.setText(address.getCity());
        holder.stateText.setText(address.getState());
        holder.neighborhoodText.setText(address.getNeighborhood());
        holder.cepText.setText(address.getCep());

        holder.itemView.setOnClickListener(v -> callbackAddressList.onClickItem(address));
        holder.itemView.setOnLongClickListener(v -> {
            callbackAddressList.onLongClickItem(address.getCepToInt());
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return addressList != null ? addressList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_text)
        TextView cityText;

        @BindView(R.id.state_text)
        TextView stateText;

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

        void onClickItem(Address address);

        void onLongClickItem(int id);
    }

}
