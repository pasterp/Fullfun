package full.fullfun.vues;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.ImageButton;

import java.io.FileNotFoundException;
import java.io.IOException;

import full.fullfun.R;
import full.fullfun.donnees.BaseDeDonnees;
import full.fullfun.donnees.JoueurDAO;
import full.fullfun.vues.adapteurs.PageVueAdapteur;
import full.fullfun.vues.adapteurs.ToastCustom;
import full.fullfun.vues.fragments.FragmentJoueurs;
import full.fullfun.vues.fragments.FragmentSets;


public class MainActivity extends AppCompatActivity {


    /***** Constantes *****/

    private static final int FRAGMENT_SETS = 1;

    private static final int FRAGMENT_JOUEURS = 0;

    private static final int TOAST_SET = 1;

    private static final int TOAST_JOUEUR = 2;

    private static final int TOAST_PARTIE = 3;



    /***** Attributs *****/

    protected FragmentJoueurs fragmentJoueurs;

    protected FragmentSets fragmentSets;

    protected FloatingActionButton boutonLancerPartie;

    protected ImageButton ajouterSetOuJoueur;


    /***** Méthodes *****/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialisation des DAO
        BaseDeDonnees.getInstance(getBaseContext());
        try {
            JoueurDAO.getInstance().chargerSauvegarde(openFileInput("joueurs.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                openFileOutput("joueurs.xml", Context.MODE_PRIVATE);
                JoueurDAO.getInstance().chargerSauvegarde(getResources().getAssets().open("joueurs.xml"));
                JoueurDAO.getInstance().sauvegarderJoueurs(openFileOutput("joueurs.xml", Context.MODE_PRIVATE));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        instancierFragments();

        //Ajout de la toolbar a l'activite principale
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ajouterSetOuJoueur = (ImageButton) findViewById(R.id.ajouter);
        ajouterSetOuJoueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ToastCustom(MainActivity.this, 0);
            }
        });


        // Reglage du viewpager pour chaque tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // mise en place de la tabs dans la toolbar
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
                    new ToastCustom(MainActivity.this,TOAST_SET);
                    ((ViewPager)findViewById(R.id.viewpager)).setCurrentItem(FRAGMENT_SETS);

                }
            });
        }else if (fragmentJoueurs.getJoueursSelect().isEmpty() && !fragmentSets.getSetSelect().isEmpty()){
            boutonLancerPartie.show();
            boutonLancerPartie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ToastCustom(MainActivity.this,TOAST_JOUEUR);
                    ((ViewPager)findViewById(R.id.viewpager)).setCurrentItem(FRAGMENT_JOUEURS);
                }
            });
        }else if(!fragmentJoueurs.getJoueursSelect().isEmpty() && !fragmentSets.getSetSelect().isEmpty()){
            boutonLancerPartie.show();
            boutonLancerPartie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ToastCustom(MainActivity.this,TOAST_PARTIE);
                }
            });
        }else {
            boutonLancerPartie.hide();
        }
    }
}
