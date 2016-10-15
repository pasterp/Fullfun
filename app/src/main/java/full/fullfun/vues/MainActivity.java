package full.fullfun.vues;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
=======
>>>>>>> origin/master
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import full.fullfun.R;
import full.fullfun.donnees.JoueurDAO;
import full.fullfun.modeles.GroupeJoueur;
import full.fullfun.modeles.Joueur;
import full.fullfun.modeles.Sexe;
import full.fullfun.vues.adapteurs.PageVueAdapteur;
import full.fullfun.vues.adapteurs.ToastCustom;
import full.fullfun.vues.fragments.FragmentJoueurs;
import full.fullfun.vues.fragments.FragmentSets;


public class MainActivity extends AppCompatActivity {


    private static final int FRAGMENT_SETS = 1;

    private static final int FRAGMENT_JOUEURS = 0;

    /***** Attributs *****/

    protected FragmentJoueurs fragmentJoueurs;

    protected FragmentSets fragmentSets;

    protected FloatingActionButton boutonLancerPartie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialisation des DAO
        try {
            JoueurDAO.getInstance().initialiserJoueurs(openFileInput("joueurs.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                openFileOutput("joueurs.xml", Context.MODE_PRIVATE);
                JoueurDAO.getInstance().initialiserJoueurs(getResources().getAssets().open("joueurs.xml"));
                JoueurDAO.getInstance().sauvegarderJoueurs(JoueurDAO.getInstance().getListeJoueurs(), openFileOutput("joueurs.xml", Context.MODE_PRIVATE));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.ajouterJoueur);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Joueur> listeG = new ArrayList<>();
                    listeG.add(new Joueur(0, "Quentin", Sexe.Homme));
                    JoueurDAO.getInstance().sauvegarderJoueurs(listeG, openFileOutput("joueurs.xml", Context.MODE_PRIVATE));
                    fragmentJoueurs.rafraichir();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        instancierFragments();

        /***** Ajout de la toolbar a l'activite principale *****/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /***** Reglage du viewpager pour chaque tabs   *****/
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        /***** mise en place de la tabs dans la toolbar *****/
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        boutonLancerPartie = (FloatingActionButton) findViewById(R.id.boutonLancerPartie);
        boutonLancerPartie.hide();
    }

    private void instancierFragments() {
        fragmentJoueurs = new FragmentJoueurs();
        fragmentSets = new FragmentSets();


    }

    /***** Ajout des fragments a la tabs   *****/
    private void setupViewPager(ViewPager viewPager) {
        PageVueAdapteur adapter = new PageVueAdapteur(getSupportFragmentManager());
        adapter.addFragment(fragmentJoueurs, "Joueurs");
        adapter.addFragment(fragmentSets, "Sets");
        viewPager.setAdapter(adapter);
        fragmentJoueurs.setMainActivity(this);
        fragmentSets.setMainActivity(this);
    }


    public void verifierLancementPartie(){
        if (!fragmentJoueurs.getJoueursSelect().isEmpty() && fragmentSets.getSetSelect().isEmpty()){
            boutonLancerPartie.show();
            boutonLancerPartie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ToastCustom("Selectionner au moins un set", getApplicationContext(), getLayoutInflater(), findViewById(R.id.toast_id));
                    ((ViewPager)findViewById(R.id.viewpager)).setCurrentItem(FRAGMENT_SETS);

                }
            });
        }else if (fragmentJoueurs.getJoueursSelect().isEmpty() && !fragmentSets.getSetSelect().isEmpty()){
            boutonLancerPartie.show();
            boutonLancerPartie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ToastCustom("Selectionner au moins un joueur", getApplicationContext(), getLayoutInflater(), findViewById(R.id.toast_id));
                    ((ViewPager)findViewById(R.id.viewpager)).setCurrentItem(FRAGMENT_JOUEURS);
                }
            });
        }else if(!fragmentJoueurs.getJoueursSelect().isEmpty() && !fragmentSets.getSetSelect().isEmpty()){
            boutonLancerPartie.show();
            boutonLancerPartie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ToastCustom("Lancement de la partie ici", getApplicationContext(), getLayoutInflater(), findViewById(R.id.toast_id));
                }
            });
        }else {
            boutonLancerPartie.hide();
        }
    }
}
