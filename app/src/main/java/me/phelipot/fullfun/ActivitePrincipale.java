package me.phelipot.fullfun;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.phelipot.fullfun.donnees.GenerateurPartie;
import me.phelipot.fullfun.donnees.GestionnaireXML;
import me.phelipot.fullfun.donnees.JoueurDAO;
import me.phelipot.fullfun.donnees.Partie;
import me.phelipot.fullfun.donnees.SetQuestionsDAO;
import me.phelipot.fullfun.modeles.SetQuestions;

public class ActivitePrincipale extends AppCompatActivity {
    protected Button bouttonMesJoueurs;
    protected Button bouttonMesSets;
    protected Button boutonJouer;

    protected SetQuestionsDAO accesseurSetQuestionDAO;

    public static Partie partieActuelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_principale);

        accesseurSetQuestionDAO = SetQuestionsDAO.getInstance();

        bouttonMesJoueurs = (Button) findViewById(R.id.actionMesJoueurs);
        bouttonMesJoueurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentionNavigueurMesJoueurs = new Intent (ActivitePrincipale.this, MesJoueurs.class);
                startActivity(intentionNavigueurMesJoueurs);
            }
        });

        bouttonMesSets = (Button) findViewById(R.id.actionMesSets);
        bouttonMesSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentionNavigueurMesSets = new Intent (ActivitePrincipale.this, MesSets.class);
                startActivity(intentionNavigueurMesSets);
            }
        });

        boutonJouer = (Button) findViewById(R.id.playBouton);
        boutonJouer.setEnabled(accesseurSetQuestionDAO.isInitialise());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (accesseurSetQuestionDAO.isInitialise()){
            boutonJouer.setEnabled(true);

            boutonJouer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intention = new Intent(ActivitePrincipale.this, VueQuestion.class);

                    partieActuelle = new Partie();
                    List<SetQuestions> setPourJouer = new ArrayList<>();
                    setPourJouer.add(accesseurSetQuestionDAO.getListeSetQuestion().get(0));
                    partieActuelle.ajouterSet(
                            new GenerateurPartie().genererSet(
                                    setPourJouer,
                                    JoueurDAO.getInstance().getListeJoueur()
                            )
                    );
                    partieActuelle.melangerQuestions();

                    startActivity(intention);
                }
            });
        }
    }
}
