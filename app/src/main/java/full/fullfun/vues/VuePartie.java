package full.fullfun.vues;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import full.fullfun.R;
import full.fullfun.modeles.Partie;
import full.fullfun.vues.adapteurs.ListeQuestionsAdapteur;
import full.fullfun.vues.animations.ZoomAnimationListe;

public class VuePartie extends FragmentActivity {

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
        listeAdapteur = new ListeQuestionsAdapteur(getSupportFragmentManager(), partie.getSetQuestions().getListeQuestions());
        vueQuestions.setAdapter(listeAdapteur);
    }

    private void initialiserVue() {
        vueQuestions = (ViewPager) findViewById(R.id.vueQuestions);
        vueQuestions.setPageTransformer(false, new ZoomAnimationListe());
        //vueQuestions.setRotation(90);
    }

    @Override
    protected void onResume() {
        super.onResume();
        partie = (Partie) getIntent().getExtras().get("partie");

        initialiserVue();
        initialiserAdapteurs();
    }
}
