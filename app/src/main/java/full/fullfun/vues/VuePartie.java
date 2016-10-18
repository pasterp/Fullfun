package full.fullfun.vues;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import full.fullfun.R;
import full.fullfun.donnees.BaseDeDonnees;
import full.fullfun.modeles.Partie;
import full.fullfun.vues.adapteurs.ListeQuestionsAdapteur;
import full.fullfun.vues.adapteurs.ToastCustom;
import full.fullfun.vues.animations.ZoomAnimationListe;

public class VuePartie extends AppCompatActivity {

    /***** Attributs *****/

    protected ViewPager vueQuestions;

    protected Partie partie;

    protected PagerAdapter listeAdapteur;

    /***** Méthodes *****/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_partie);

        partie = (Partie) getIntent().getExtras().get("partie");
        initialiserVue();
        initialiserAdapteurs();
    }

    private void initialiserAdapteurs() {
        listeAdapteur = new ListeQuestionsAdapteur(getSupportFragmentManager(), partie, this);
        vueQuestions.setAdapter(listeAdapteur);
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
}
