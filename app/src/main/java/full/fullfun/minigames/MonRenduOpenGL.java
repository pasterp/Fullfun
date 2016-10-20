package full.fullfun.minigames;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import full.fullfun.R;

/**
 * Created by pascal on 16-10-04.
 */

public class MonRenduOpenGL implements Renderer {
    private Dé mDé;
    private float mRotX, mRotY;
    private float mTargX, mTargY;
    private float mVitesse = 5;

    private boolean active;

    private Context mContexte;

    public MonRenduOpenGL(Context c){
        mContexte = c;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0,0,0,0);

        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_TEXTURE_2D); // ON VEUT DE LA TEXTURE
        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_NICEST);

        mDé = new Dé(0, 0, -5);
        mDé.chargerTextures(gl, mContexte);

        active = true;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        if (!active)
            return;

        gl.glLoadIdentity();

        mDé.setRotX(mRotX);
        mDé.setRotY(mRotY);
        //mDé.setRotZ(mDéRotation*5);

        if (mDé.getRotX() == mTargX){

        }else{
            mRotX += 0.5f*mVitesse;
        }

        if (mDé.getRotY() == mTargY){

        }else{
            mRotY += 0.5f*mVitesse;
        }

        mDé.draw(gl);
        gl.glLoadIdentity();
    }

    private float randomFace(){
        List<Float> angles = new ArrayList<Float>();
        angles.add(0.0f);
        angles.add(90.0f);
        angles.add(180.0f);
        angles.add(270.0f);

        Collections.shuffle(angles);

        return angles.get(0);
    }

    public void lancerLeDé(){
        mRotX = 0.0f;
        mRotY = 0.0f;
        mTargX = randomFace();
        mTargY = randomFace();
    }

    public void nettoyer() {
        active = false;
    }
}
