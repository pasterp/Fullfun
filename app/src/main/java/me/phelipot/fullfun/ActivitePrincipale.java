package me.phelipot.fullfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivitePrincipale extends AppCompatActivity {
    protected Button bouttonMesJoueurs;
    protected Button bouttonMesSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_principale);

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
    }
}
