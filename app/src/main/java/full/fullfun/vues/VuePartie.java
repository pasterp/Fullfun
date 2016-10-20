package full.fullfun.vues;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import full.fullfun.R;
import full.fullfun.donnees.BaseDeDonnees;
import full.fullfun.minigames.DetectionSecousse;
import full.fullfun.minigames.MaSurfaceOpenGl;
import full.fullfun.modeles.Partie;
import full.fullfun.vues.adapteurs.ListeQuestionsAdapteur;
import full.fullfun.vues.adapteurs.ToastCustom;
import full.fullfun.vues.animations.ZoomAnimationListe;

public class VuePartie extends AppCompatActivity {

    /***** Attributs *****/

    protected ViewPager vueQuestions;

    protected Partie partie;

    protected PagerAdapter listeAdapteur;

    protected SensorManager manageur;
    protected Sensor sensor;
    protected DetectionSecousse detectionSecousse;
    private MaSurfaceOpenGl surfaceOpenGlDé;



    /***** Méthodes *****/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_partie);

        //Initialisation pour pouvoir secouer
        manageur = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manageur.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        detectionSecousse = new DetectionSecousse();
        detectionSecousse.setSecousseListener(new DetectionSecousse.SecousseListener() {
            @Override
            public void onSecoue(int nbSecousse) {
                if(surfaceOpenGlDé != null)
                    surfaceOpenGlDé.lancerLeDé();
            }
        });

        partie = (Partie) getIntent().getExtras().get("partie");
        initialiserVue();
        initialiserAdapteurs();
    }

    private void initialiserAdapteurs() {

        listeAdapteur = new ListeQuestionsAdapteur(getSupportFragmentManager(), partie, this);
        vueQuestions.setAdapter(listeAdapteur);
        // Set the gesture detector as the double tap
        // listener.
    }

    private void initialiserVue() {
        ((FloatingActionButton)findViewById(R.id.boutonFinPartie)).hide();
        vueQuestions = (ViewPager) findViewById(R.id.vueQuestions);
        vueQuestions.setPageTransformer(false, new ZoomAnimationListe());

    }

    @Override
    protected void onResume() {
        super.onResume();
        partie = (Partie) getIntent().getExtras().get("partie");
        initialiserVue();
        initialiserAdapteurs();

        manageur.registerListener(detectionSecousse, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        manageur.unregisterListener(detectionSecousse);
        super.onPause();
    }

    public void finPartie(){
        FloatingActionButton boutonFinpartie = (FloatingActionButton) findViewById(R.id.boutonFinPartie);
        boutonFinpartie.show();
        boutonFinpartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ToastCustom(VuePartie.this, ToastCustom.FIN_PARTIE);
                BaseDeDonnees.getInstance().viderBase();
                finish();
            }
        });
    }

    public void jouerAuxDes(View view){
        Toast.makeText(this, "C'est parti pour une partie de dés ! Secouez pour joueur !", Toast.LENGTH_LONG).show();

        surfaceOpenGlDé = new MaSurfaceOpenGl(this);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_vue_partie);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        layout.addView(surfaceOpenGlDé, lp);
    }

    @Override
    public void onBackPressed() {
        if(surfaceOpenGlDé == null)
            super.onBackPressed();
        else {
            surfaceOpenGlDé.vider();
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_vue_partie);
            layout.removeView(surfaceOpenGlDé);
            surfaceOpenGlDé = null;
        }
    }
}
