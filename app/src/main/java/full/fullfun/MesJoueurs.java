package full.fullfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import full.fullfun.donnees.JoueurDAO;

public class MesJoueurs extends AppCompatActivity {

    protected JoueurDAO accesseurJoueurDAO;
    protected List<HashMap<String, String>> listeJoueur;
    protected ListView vueListeJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_joueurs);
        accesseurJoueurDAO = JoueurDAO.getInstance();
        vueListeJoueur = (ListView) findViewById(R.id.vueListeJoueur);
        afficherMesJoueurs();
        //REFRESH !
    }
    public void afficherMesJoueurs(){
        listeJoueur = accesseurJoueurDAO.listerJoueurEnHasmap();
        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeJoueur,
                android.R.layout.two_line_list_item,
                new String[]{"pseudo", "sexe"},
                new int []{android.R.id.text1,android.R.id. text2} );

        vueListeJoueur.setAdapter(adapteur);

    }
}
