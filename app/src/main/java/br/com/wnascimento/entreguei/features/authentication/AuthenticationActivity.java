package br.com.wnascimento.entreguei.features.authentication;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.R;
import br.com.wnascimento.entreguei.util.StringUtil;
import br.com.wnascimento.entreguei.util.ValidateUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class AuthenticationActivity extends DaggerAppCompatActivity implements AuthenticationContract.View {

    private static final int MIN_CHARACTER_PASSWORD = 6;

    @BindView(R.id.register_progress)
    ProgressBar registerProgress;

    @BindView(R.id.email)
    AutoCompleteTextView email;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.register_button)
    Button registerButton;

    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;

    @Inject
    AuthenticationPresenter authenticationPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.authenticate_button)
    public void onClick() {
        authenticationPresenter.login(getEmail(), getPassword());
    }

    @OnClick(R.id.register_button)
    public void onRegister() {
        if (isFormValid()) {
            authenticationPresenter.register(getEmail(), getPassword());
        }
    }

    private boolean isFormValid() {

        boolean formValid = true;

        if (StringUtil.isEmpty(getEmail())) {
            formValid = false;
            email.setError(getString(R.string.error_email_empty));
        }

        if (ValidateUtil.validateEmail(getEmail())) {
            formValid = false;
            email.setError(getString(R.string.error_email_invalid));
        }

        if (StringUtil.isEmpty(getPassword())) {
            formValid = false;
            password.setError(getString(R.string.error_password_empty));
        }

        if (ValidateUtil.validateMinLength(getPassword(), MIN_CHARACTER_PASSWORD)) {
            formValid = false;
            password.setError(getString(R.string.error_password_little));
        }

        return formValid;
    }

    @Override
    public void showProgress() {
        emailLoginForm.setVisibility(View.GONE);
        registerProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        emailLoginForm.setVisibility(View.VISIBLE);
        registerProgress.setVisibility(View.GONE);
    }

    @Override
    public void showErrorRegister() {
        Toast.makeText(this, R.string.error_register_new_motoboy, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorLogin() {
        Toast.makeText(this, R.string.error_login, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void toAddresses() {
        Toast.makeText(this, "Motoboy", Toast.LENGTH_SHORT).show();
    }


    public String getEmail() {
        return email.getText().toString();
    }

    public String getPassword() {
        return password.getText().toString();
    }


}
