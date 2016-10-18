package full.fullfun.vues;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.FileNotFoundException;

import full.fullfun.R;
import full.fullfun.donnees.JoueurDAO;
import full.fullfun.modeles.Joueur;
import full.fullfun.modeles.Sexe;

public class AjoutJoueur extends AppCompatActivity {

    /***** Attributs *****/

    private EditText champPseudo;

    private Spinner spinnerSexe;

    private FloatingActionButton boutonValider;

    private Joueur joueurEdition;

    private JoueurDAO joueurDAO = JoueurDAO.getInstance();

    /***** Méthodes *****/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_joueur);

        joueurEdition = (Joueur) getIntent().getExtras().get("joueur");
        if (joueurEdition == null)
            joueurEdition = new Joueur();

        initialiserVue();
        initialiserControleurs();
    }

    private void initialiserVue(){
        champPseudo = (EditText) findViewById(R.id.champPseudo);
        spinnerSexe = (Spinner) findViewById(R.id.spinnerSexe);
        boutonValider = (FloatingActionButton) findViewById(R.id.boutonValiderJoueur);

    }

    private void initialiserControleurs(){
        champPseudo.setText(joueurEdition.getPseudo());

        ArrayAdapter<CharSequence> spinnerAdapteur = ArrayAdapter.createFromResource(
                this, R.array.sexeListe, android.R.layout.simple_spinner_item
        );
        spinnerAdapteur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexe.setAdapter(spinnerAdapteur);
        String[] sexes = getResources().getStringArray(R.array.sexeListe);
        for (int i = 0; i < sexes.length; i++){
            if (sexes[i].equals(joueurEdition.getSexe().toString()))
                spinnerSexe.setSelection(i);
        }
        spinnerSexe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                joueurEdition.setSexe(Sexe.factory((String) parent.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Rien à faire
            }
        });

        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joueurEdition.setPseudo(String.valueOf(champPseudo.getText()));
                joueurDAO.ajouterJoueur(joueurEdition);
                try {
                    joueurDAO.sauvegarderJoueurs(openFileOutput("joueurs.xml", Context.MODE_PRIVATE));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
