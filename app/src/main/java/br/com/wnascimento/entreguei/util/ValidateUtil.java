package br.com.wnascimento.entreguei.util;


import android.util.Patterns;

public class ValidateUtil {

    public static boolean isValidateEmail(String email) {
        return email.contains("gmail.com") && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean validateMinLength(String string, int maxLength) {
        return string.length() < maxLength;
    }

}
