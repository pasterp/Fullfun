package full.mesrecycleviews.donnees;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import full.mesrecycleviews.modeles.GroupeJoueur;
import full.mesrecycleviews.modeles.Joueur;


/**
 * Created by 1634836 on 03/10/2016.
 */

public class JoueurDAO {

    /***** Attributs *****/

    private AssetManager manageurAsset;

    protected List<GroupeJoueur> listeGroupes;

    private static JoueurDAO instance = null;


    /***** Constructeurs *****/

    public static JoueurDAO getInstance(){
        if(null == instance){
            instance = new JoueurDAO();
        }
        return instance;
    }

    private JoueurDAO(){
        listeGroupes = new ArrayList<>();
    }


    /***** Accesseurs *****/

    public List<GroupeJoueur> getListeGroupes() {
        return listeGroupes;
    }


    /***** Methodes *****/

    public void initialiserJoueurs(AssetManager manageurAssets){
        if (this.manageurAsset == null) {
            List<GroupeJoueur> listeGroupes = null;
            this.manageurAsset = manageurAssets;

            Log.d("XML_Assets", "Liste des assets:");
            try {
                for (String s :
                        Resources.getSystem().getAssets().list(File.separator + "assets" + File.separator)) {
                    Log.d("XML_Assets", s);
                }


                listeGroupes = new GestionnaireXML().lireGroupesJoueurs(manageurAsset.open("joueurs.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.listeGroupes = listeGroupes;
        }
    }

    public List<HashMap<String, String>> listerJoueurEnHasmap(){

        List<HashMap<String , String>> listeJoueurHashMap = new ArrayList<>();

        for (GroupeJoueur g : this.getListeGroupes()){
            for (Joueur j : g.getJoueurs()){
                listeJoueurHashMap.add(j.exporterHashMap());
            }
        }
        return listeJoueurHashMap;
    }

    public boolean isInitialise() {
        return manageurAsset != null;
    }

    /**
     * Sauvegarde les joueurs dans le XML. Si le DAO n'a pas été initialisé via <code>initialiserJoueur</code>
     * alors cela ne fera rien.
     */
    public void sauvegarderJoueurs(){
        if (isInitialise()){
           // @todo Trouver le moyen de sauvegarder.
        }else{
            Log.d("Sauvegarde Impossible", "DAO non initialisé: Appel à initialiserJoueur d'abord.");
        }
    }
}
