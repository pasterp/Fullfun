package full.fullfun.minigames;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import full.fullfun.R;


/**
 * Created by pascal on 16-10-04.
 */

public class MaSurfaceOpenGl extends GLSurfaceView {
    MonRenduOpenGL renduOpenGL;


    public MaSurfaceOpenGl(Context context) {
        super(context);

        initialisation(context);
    }

    public MaSurfaceOpenGl(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialisation(context);
    }

    private void initialisation(Context context){
        Log.d("OpenGL", "Initialisation du dé !");
        setEGLContextClientVersion(1);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);

        setZOrderOnTop(true); //Needed

        renduOpenGL = new MonRenduOpenGL(context);

        setRenderer(renduOpenGL);

        getHolder().setFormat(PixelFormat.TRANSLUCENT); // Fond de la surface transparent
    }

    public void lancerLeDé(){
        renduOpenGL.lancerLeDé();
    }

    public void vider(){
        renduOpenGL.nettoyer();
    }
}
