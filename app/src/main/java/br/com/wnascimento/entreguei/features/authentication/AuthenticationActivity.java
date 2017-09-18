package br.com.wnascimento.entreguei.features.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.wnascimento.entreguei.R;
import br.com.wnascimento.entreguei.features.address.list.ListAddressesActivity;
import br.com.wnascimento.entreguei.util.AnimationUtil;
import br.com.wnascimento.entreguei.util.StringUtil;
import br.com.wnascimento.entreguei.util.ValidateUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class AuthenticationActivity extends DaggerAppCompatActivity implements AuthenticationContract.View {

    private static final int MIN_CHARACTER_PASSWORD = 6;

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.email)
    AutoCompleteTextView email;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.register_button)
    Button registerButton;

    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;

    @BindView(R.id.authenticate_button)
    Button authenticateButton;

    @BindView(R.id.motoboy_image)
    ImageView motoboyImage;

    @BindView(R.id.register_form)
    ScrollView registerForm;

    @Inject
    AuthenticationPresenter authenticationPresenter;


    public static void start(Context context) {
        Intent starter = new Intent(context, AuthenticationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        ButterKnife.bind(this);
        animateMotoboy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        authenticationPresenter.restart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        authenticationPresenter.stop();
    }

    private void animateMotoboy() {
        AnimationUtil.leftToRigth(motoboyImage, 2000);
    }

    @OnClick(R.id.authenticate_button)
    public void onClick() {
        if (isFormValid()) {
            authenticationPresenter.login(getEmail(), getPassword());
        }
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
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        emailLoginForm.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
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
    public void toMain() {
        ListAddressesActivity.start(this);
    }


    public String getEmail() {
        return email.getText().toString();
    }

    public String getPassword() {
        return password.getText().toString();
    }


}
