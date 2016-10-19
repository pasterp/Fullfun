package full.fullfun.minigames;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import full.fullfun.R;

/**
 * Created by pascal on 16-10-18.
 */

public class Dé {
    private List<Face> faces;
    private float x, y, z;
    private float rotX, rotY, rotZ;

    private int[] textures = {
            R.drawable.de1,
            R.drawable.de2,
            R.drawable.de3,
            R.drawable.de4,
            R.drawable.de5,
            R.drawable.de6
    };


    public Dé(){
        this(0, 0, 0, 0, 0, 0);
    }

    public Dé(float x, float y, float z){
        this(x, y, z, 0, 0, 0);
    }

    public Dé(float x, float y, float z, float rx, float ry, float rz){
        this.x = x;
        this.y = y;
        this.z = z;

        rotX = rx;
        rotY = ry;
        rotZ = rz;

        initialisation();
    }

    private void initialisation(){
        faces = new ArrayList<>();

        //Nos 6 faces
        faces.add(new Face(0, 0, 0));
        faces.add(new Face(0, 0, -1, 0, -90, 0));
        faces.add(new Face(1, 0, -1, 0, 180, 0));
        faces.add(new Face(1, 0, 0, 0, -270, 0));
        faces.add(new Face(0, 1, 0, -90, 0, 0));
        faces.add(new Face(0, 0, 0, -90, 0, 0));
    }

    public void chargerTextures(GL10 gl, Context c){
        for(int i = 0; i < faces.size(); i++){
            faces.get(i).chargerTexture(gl, c, textures[i]);
        }
    }

    public void draw(GL10 gl) {
        for(Face face : faces){
            gl.glLoadIdentity();
            gl.glTranslatef(x, y, z);
            gl.glRotatef(rotX, 1.0f, 0.0f, 0.0f);
            gl.glRotatef(rotY, 0.0f, 1.0f, 0.0f);
            gl.glRotatef(rotZ, 0.0f, 0.0f, 1.0f);

            face.draw(gl);
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getRotX() {
        return rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public float getRotZ() {
        return rotZ;
    }
}
