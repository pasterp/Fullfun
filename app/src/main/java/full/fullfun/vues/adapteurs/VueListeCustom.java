package full.fullfun.vues.adapteurs;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class VueListeCustom extends ViewPager {


    /***** Attributs *****/


    /***** Constructeurs *****/

    public VueListeCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public VueListeCustom(Context context){
        super(context);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }


    /***** Méthodes *****/

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = super.onInterceptTouchEvent(inverserXY(ev));

        inverserXY(ev);
        return intercepted;
    }

    private MotionEvent inverserXY(MotionEvent ev) {
        float largeur = getWidth();
        float hauteur = getHeight();
        float x = ev.getX();
        float y = ev.getY();

        ev.setLocation((y / hauteur) * largeur, (x / largeur) * hauteur);

        return ev;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(inverserXY(ev));
    }
}
