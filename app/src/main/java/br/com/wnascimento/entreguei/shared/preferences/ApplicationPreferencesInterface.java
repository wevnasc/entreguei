package br.com.wnascimento.entreguei.shared.preferences;


import br.com.wnascimento.entreguei.features.authentication.User;

public interface ApplicationPreferencesInterface {

    void saveCurrentUser(User user);

    User getCurrentUser();

}
