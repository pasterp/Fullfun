package full.fullfun.donnees;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import full.fullfun.modeles.GroupeJoueur;
import full.fullfun.modeles.Joueur;


/**
 * Created by 1634836 on 03/10/2016.
 */

public class JoueurDAO {

    /***** Attributs *****/

    protected List<Joueur> listeJoueurs;

    private static JoueurDAO instance = null;


    /***** Constructeurs *****/

    public static JoueurDAO getInstance(){
        if(null == instance){
            instance = new JoueurDAO();
        }
        return instance;
    }

    private JoueurDAO(){
        listeJoueurs = new ArrayList<>();
    }


    /***** Accesseurs *****/

    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public void initialiserJoueurs(InputStream flux){
        listeJoueurs = new GestionnaireXML().lireJoueurs(flux);
    }


    /**
     * Sauvegarde les joueurs dans le XML. Si le DAO n'a pas été initialisé via <code>initialiserJoueur</code>
     * alors cela ne fera rien.
     * @param joueurs
     * @param flux
     */
    public void sauvegarderJoueurs(List<Joueur> joueurs, OutputStream flux){
        new GestionnaireXML().sauvegarderGroupeJoueurs(joueurs, flux);
    }

    public void ajouterJoueur(Joueur joueur){
        listeJoueurs.add(joueur);
    }
}
