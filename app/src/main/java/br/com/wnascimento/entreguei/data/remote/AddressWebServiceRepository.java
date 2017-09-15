package br.com.wnascimento.entreguei.data.remote;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import br.com.wnascimento.entreguei.features.address.search.Address;
import br.com.wnascimento.entreguei.features.address.search.AddressRemoteRepository;
import io.reactivex.Single;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddressWebServiceRepository implements AddressRemoteRepository{

    private static final String URL = "http://viacep.com.br/ws/";
    private static final String CONTENT_TYPE = "/json";

    private final OkHttpClient client;
    private final Gson gson;

    public AddressWebServiceRepository(OkHttpClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @Override
    public Single<Address> getAddressByCep(String cep) {
        String url = URL + cep + CONTENT_TYPE;
        Request request = new Request.Builder()
                .url(url)
                .build();

        return Single.create(emitter -> client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                emitter.onError(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Address address = gson.fromJson(response.body().charStream(), Address.class);
                emitter.onSuccess(address);
            }
        }));
    }

}
