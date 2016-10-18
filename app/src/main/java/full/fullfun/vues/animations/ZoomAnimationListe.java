package full.fullfun.vues.animations;

import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

public class ZoomAnimationListe implements ViewPager.PageTransformer {

    /***** Constantes *****/

    private static final float MIN_SCALE = 0.85f;

    private static final float MIN_ALPHA = 0.5f;

    /***** Attributs *****/


    /***** Constructeurs *****/

    public ZoomAnimationListe(){
    }

    /***** Méthodes *****/

    @Override
    public void transformPage(View vue, float position) {
        int largeurPage = vue.getWidth();
        int hauteurPage = vue.getHeight();


        if (position < -1){
            vue.setAlpha(0);
        }else if (position <= 1){
            float scaling = Math.max(MIN_SCALE, 1 - Math.abs(position));

            vue.setTranslationX(largeurPage * -position);

            vue.setTranslationY(hauteurPage * position);

            vue.setScaleX(scaling);
            vue.setScaleY(scaling);

            vue.setAlpha(
                    MIN_ALPHA +
                            (scaling - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA)
            );
        }else {
            vue.setAlpha(0);
        }
    }
}
