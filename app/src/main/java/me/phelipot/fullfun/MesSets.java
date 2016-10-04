package me.phelipot.fullfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import me.phelipot.fullfun.donnees.SetQuestionsDAO;

public class MesSets extends AppCompatActivity {
    protected SetQuestionsDAO accesseurSetQuestionDAO;
    protected List<HashMap<String, String>> listeSetQuestion;
    protected ListView vueListeSetQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_sets);
        accesseurSetQuestionDAO = SetQuestionsDAO.getInstance();
        accesseurSetQuestionDAO.initialisationQuestions(getResources().getAssets());
        vueListeSetQuestions = (ListView) findViewById(R.id.listeViewSetQuestions);
        afficherMesSets();

        vueListeSetQuestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ouvrirDetails = new Intent(MesSets.this, DetailsSetQuestions.class);
                ouvrirDetails.putExtra("set", listeSetQuestion.get(position).get("nom"));
                startActivity(ouvrirDetails);
            }
        });

    }
    public void afficherMesSets(){
        listeSetQuestion = accesseurSetQuestionDAO.listerSetQuestionsEnHasmap();
        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeSetQuestion,
                android.R.layout.two_line_list_item,
                new String[]{"nom", "createur"},
                new int []{android.R.id.text1,android.R.id. text2} );

        vueListeSetQuestions.setAdapter(adapteur);

    }
}
