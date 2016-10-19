package full.fullfun.minigames;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import full.fullfun.R;

/**
 * Created by pascal on 16-10-18.
 */

public class Face {
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    private ByteBuffer mIndexBuffer;

    private int[] textures = new int[1];
    private FloatBuffer mTextureBuffer;

    private float x, y, z;
    private float rotX, rotY, rotZ;

    private float vertices[] = {
            0.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f
    };

    private float colors[] = {
            01.0f,  0.0f,  0.0f,  0.40f, // Vert
            01.0f,  0.0f, 0.0f, 0.40f,
            01.0f,  0.0f, 0.0f, 0.40f,
            01.0f,  0.0f, 0.0f, 0.40f
    };

    private byte indices[] = {
        0, 1, 2, 1, 2, 3
    };

    private float texture[] = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 0.0f,
            1.0f, 1.0f //Une face
    };

    public Face(){
        this(0,0,0,0,0,0);
    }

    public Face(float x, float y, float z){
        this(x, y, z, 0, 0, 0);
    }

    public Face(float x, float y, float z, float rx, float ry, float rz){
        this.x = x;
        this.y = y;
        this.z = z;

        rotX = rx;
        rotY = ry;
        rotZ = rz;

        initialisation();
    }

    private void initialisation(){
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mVertexBuffer = byteBuf.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        byteBuf = ByteBuffer.allocateDirect(colors.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mColorBuffer = byteBuf.asFloatBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);

        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);

        mTextureBuffer = ByteBuffer.allocateDirect(texture.length * 4 ).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mTextureBuffer.put(texture);
        mTextureBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glTranslatef(x, y, z);
        gl.glRotatef(rotX, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(rotY, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(rotZ, 0.0f, 0.0f, 1.0f);


        gl.glFrontFace(GL10.GL_CW);


        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        //gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE,
                mIndexBuffer);


        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        //gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

    }

    public void chargerTexture(GL10 gl, Context c, int idRessource){
        InputStream is = c.getResources().openRawResource(idRessource);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
            }
        }

        gl.glGenTextures(1, textures, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
                GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
                GL10.GL_CLAMP_TO_EDGE);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        //Nettoyage
        bitmap.recycle();
    }
}
