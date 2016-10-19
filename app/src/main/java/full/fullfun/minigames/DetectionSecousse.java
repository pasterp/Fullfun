package full.fullfun.minigames;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by pascal on 16-10-19.
 */

public class DetectionSecousse implements SensorEventListener{
    private static final float LIMITE_GRAVITE = 2.F;
    private static final int DUREE_SECOUSSE_MS = 500;
    private static final int DUREE_RESET_SANS_SECOUSSE_MS = 3000;

    private SecousseListener mListener;
    private long mSecousseTimestamp;
    private int mNombreSecousse;

    public void setSecousseListener(SecousseListener secousseListener){
        mListener = secousseListener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float gX = event.values[0] / SensorManager.GRAVITY_EARTH;
        float gY = event.values[1] / SensorManager.GRAVITY_EARTH;
        float gZ = event.values[2] / SensorManager.GRAVITY_EARTH;

        // Proche de 1 si il n'y a pas de mouvement
        float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);

        if(gForce > LIMITE_GRAVITE){
            final long now = System.currentTimeMillis();

            if(mSecousseTimestamp + DUREE_SECOUSSE_MS > now)
                return;

            if(mSecousseTimestamp + DUREE_RESET_SANS_SECOUSSE_MS > now)
                mNombreSecousse = 0;

            mSecousseTimestamp = now;
            mNombreSecousse++;

            mListener.onSecoue(mNombreSecousse);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Nobody cares
    }

    public interface SecousseListener{
        public void onSecoue(int nbSecousse);
    }
}
