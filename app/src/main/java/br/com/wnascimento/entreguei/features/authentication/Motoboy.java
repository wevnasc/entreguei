package br.com.wnascimento.entreguei.features.authentication;


public class Motoboy {

    private Long id;
    private String email;
    private String password;


    public Motoboy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
