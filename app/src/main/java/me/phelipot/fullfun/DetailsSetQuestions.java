package me.phelipot.fullfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.phelipot.fullfun.donnees.SetQuestionsDAO;
import me.phelipot.fullfun.modeles.Question;
import me.phelipot.fullfun.modeles.SetQuestions;

public class DetailsSetQuestions extends AppCompatActivity {

    /***** Attributs *****/

    protected SetQuestionsDAO setQuestionsDAO = SetQuestionsDAO.getInstance();

    protected SetQuestions setQuestions = null;

    protected TextView texteNomSet;

    protected TextView texteCreateurSet;

    protected ListView listeQuestions;

    private ArrayList<HashMap<String, String>> donneesQuestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_set_questions);

        String nomSet = getIntent().getExtras().getString("set");

        for (SetQuestions setQ : setQuestionsDAO.getListeSetQuestion()){
            if (setQ.getNom().equals(nomSet))
                setQuestions = setQ;
        }

        // On affiche les éléments
        initialiserVue();
    }

    private void initialiserVue() {
        texteNomSet = (TextView) findViewById(R.id.texteNomSet);
        texteCreateurSet = (TextView) findViewById(R.id.texteCreateurSet);
        listeQuestions = (ListView) findViewById(R.id.listeQuestions);

        texteNomSet.setText(String.format("%s%s", String.valueOf(texteNomSet.getText()), setQuestions.getNom()));
        texteCreateurSet.setText(String.format("%s%s", String.valueOf(texteCreateurSet.getText()), setQuestions.getCreateur()));

        donneesQuestions = new ArrayList<>();
        for (Question question : setQuestions.getListeQuestions()){
            donneesQuestions.add(question.exporterHashmap());
        }

        listeQuestions.setAdapter(new SimpleAdapter(
                this,
                donneesQuestions,
                android.R.layout.two_line_list_item,
                new String[] {"categorie", "texte"},
                new int[] {android.R.id.text1, android.R.id.text2}
        ));

    }
}
