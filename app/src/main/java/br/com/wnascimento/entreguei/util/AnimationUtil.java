package br.com.wnascimento.entreguei.util;


import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimationUtil {

    public static void fade(View view, int duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(duration);
        view.setAnimation(alphaAnimation);
    }

    public static void leftToRigth(View view, int duration) {
        final Animation animation = new TranslateAnimation(-800, 0, 0, 0);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

}
