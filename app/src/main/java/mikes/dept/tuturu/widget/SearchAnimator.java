package mikes.dept.tuturu.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * Created by mikes on 17.12.16.
 */

public class SearchAnimator {

    private static final int DURATION = 700;
    private static final float TENSION = 1;
    private static final String PROP_TRANSLATIONY = "translationY";
    private static final String PROP_ALPHA = "alpha";

    public enum Action {
        FADEIN,
        FADEOUT
    }

    @NonNull
    public static AnimatorSet fade(@NonNull View view, Action anim) {

        float startY = (anim == Action.FADEIN) ? -view.getHeight() : 0f;
        float endY = (anim == Action.FADEIN) ? 0f : -view.getHeight();
        float startAlpha = (anim == Action.FADEIN) ? 0f : 1f;
        float endAlpha = (anim == Action.FADEIN) ? 1f : 0f;

        return init(view, startY, endY, startAlpha, endAlpha);
    }

    @NonNull
    private static AnimatorSet init(@NonNull View view, float startY, float endY, float startAlpha, float endAlpha) {
        ObjectAnimator mover = ObjectAnimator.ofFloat(view, PROP_TRANSLATIONY, startY, endY);
        mover.setDuration(DURATION);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, PROP_ALPHA, startAlpha, endAlpha);
        fadeIn.setDuration(DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(mover).with(fadeIn);
        animatorSet.setInterpolator(new OvershootInterpolator(TENSION));

        return animatorSet;
    }

}
