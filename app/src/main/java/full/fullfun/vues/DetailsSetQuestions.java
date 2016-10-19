package full.fullfun.vues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import full.fullfun.R;
import full.fullfun.modeles.Question;
import full.fullfun.modeles.SetQuestions;

public class DetailsSetQuestions extends AppCompatActivity {

    /***** Attributs *****/

    private EditText champTitre;

    private EditText champCreateur;

    private EditText champDate ;

    private EditText champScore;

    private  EditText champDifficulte;

    private ListView listeViewQuestions;

    private SetQuestions setQuestions;

    /***** Méthodes *****/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_set_questions);

        initialiserVue();

        setQuestions = (SetQuestions) getIntent().getExtras().get("SetQuestions");

        initialiserControleurs();

    }

    private void initialiserControleurs() {
        champTitre.setText(setQuestions.getNom());
        champCreateur.setText(setQuestions.getCreateur());
        champDate.setText(setQuestions.getDate());
        champScore.setText(String.valueOf(setQuestions.getScore()));
        champDifficulte.setText(String.valueOf(setQuestions.getDifficulte()));

        String[] questions = new String[setQuestions.getListeQuestions().size()];
        for (int i = 0; i < setQuestions.getListeQuestions().size(); i++){
            questions[i] = setQuestions.getListeQuestions().get(i).getTexte();
        }
        ArrayAdapter<String> listeAdapteur = new ArrayAdapter<String>(
          getBaseContext(),
                android.R.layout.simple_list_item_1,
                questions
        );
        listeViewQuestions.setAdapter(listeAdapteur);
    }

    private void initialiserVue() {
        champTitre = (EditText) findViewById(R.id.champNomSet);
        champCreateur = (EditText) findViewById(R.id.champCreateur);
        champDate = (EditText) findViewById(R.id.champDate);
        champDifficulte = (EditText) findViewById(R.id.champDIfficulte);
        champScore = (EditText) findViewById(R.id.champScore);
        listeViewQuestions = (ListView) findViewById(R.id.listeViewQuestions);
    }
}
