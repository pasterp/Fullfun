package me.phelipot.fullfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VueQuestion extends AppCompatActivity {
    private TextView texteQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_question);

        Bundle bundle = getIntent().getExtras();

        texteQuestion = (TextView) findViewById(R.id.texteQuestion);
        texteQuestion.setText(bundle.getString("question"));

    }
}
