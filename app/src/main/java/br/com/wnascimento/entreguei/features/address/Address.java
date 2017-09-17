package br.com.wnascimento.entreguei.features.address;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address implements Serializable{

    private String cep;

    @SerializedName("logradouro")
    private String street;

    @SerializedName("bairro")
    private String neighborhood;

    @SerializedName("localidade")
    private String city;

    @SerializedName("uf")
    private String state;

    @SerializedName("complemento")
    private String complement;

    public Address(String cep, String street, String neighborhood, String city, String state, String complement) {
        this.cep = cep;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.complement = complement;
    }

    public String getCep() {
        return cep;
    }

    public String getCapFormatted() {
        return cep.substring(0, 5) + "-" + cep.substring(5, 7);
    }

    public String getCityWithState(){
        return city + " - " +state;
    }

    public int getCepToInt() {
        return Integer.parseInt(cep.replace("-", ""));
    }

    public String getStreet() {
        return street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getComplement() {
        return complement;
    }

    @Override
    public String toString() {
        return "Address{" +
                "cep='" + cep + '\'' +
                ", street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", complement='" + complement + '\'' +
                '}';
    }
}
