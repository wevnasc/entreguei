package br.com.wnascimento.entreguei.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.R;
import br.com.wnascimento.entreguei.data.local.PersistenceContract;
import br.com.wnascimento.entreguei.shared.preferences.ApplicationPreferencesInterface;
import br.com.wnascimento.entreguei.features.authentication.User;

public class ApplicationPreferences implements ApplicationPreferencesInterface {

    private final SharedPreferences sharedPreferences;

    @Inject
    public ApplicationPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    @Override
    public void saveCurrentUser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(PersistenceContract.UserEntry.COLUMN_NAME_ID, user.getId());
        editor.putString(PersistenceContract.UserEntry.COLUMN_NAME_EMAIL, user.getEmail());
        editor.putString(PersistenceContract.UserEntry.COLUMN_NAME_PASSWORD, user.getPassword());
        editor.apply();
    }

    @Override
    public User getCurrentUser() {
        return new User(
                sharedPreferences.getLong(PersistenceContract.UserEntry.COLUMN_NAME_ID, 0),
                sharedPreferences.getString(PersistenceContract.UserEntry.COLUMN_NAME_EMAIL, ""),
                sharedPreferences.getString(PersistenceContract.UserEntry.COLUMN_NAME_PASSWORD, "")
        );
    }
}
