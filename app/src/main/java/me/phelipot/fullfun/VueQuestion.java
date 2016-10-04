package me.phelipot.fullfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import me.phelipot.fullfun.modeles.Question;

public class VueQuestion extends AppCompatActivity {
    private TextView texteQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_question);

        Question q = ActivitePrincipale.partieActuelle.getQuestionSuivante();

        texteQuestion = (TextView) findViewById(R.id.texteQuestion);
        texteQuestion.setText(q.getTexte());

        findViewById(R.id.vue_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivitePrincipale.partieActuelle.hasQuestions()) {
                    Question q = ActivitePrincipale.partieActuelle.getQuestionSuivante();

                    texteQuestion = (TextView) findViewById(R.id.texteQuestion);
                    texteQuestion.setText(q.getTexte());
                }else{
                    Toast.makeText(v.getContext(), "Partie terminée", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}
