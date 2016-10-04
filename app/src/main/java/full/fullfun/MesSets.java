package full.fullfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import full.fullfun.donnees.SetQuestionsDAO;

public class MesSets extends AppCompatActivity {
    protected SetQuestionsDAO accesseurSetQuestionDAO;
    protected List<HashMap<String, String>> listeSetQuestion;
    protected ListView vueListeSetQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_sets);
        accesseurSetQuestionDAO = SetQuestionsDAO.getInstance();
        vueListeSetQuestions = (ListView) findViewById(R.id.listeViewSetQuestions);
        afficherMesSets();
        //REFRESH !

    }
    public void afficherMesSets(){
        listeSetQuestion = accesseurSetQuestionDAO.listerSetQuestionsEnHasmap();
        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeSetQuestion,
                android.R.layout.two_line_list_item,
                new String[]{"nom", "id"},
                new int []{android.R.id.text1,android.R.id. text2} );

        vueListeSetQuestions.setAdapter(adapteur);

    }
}
