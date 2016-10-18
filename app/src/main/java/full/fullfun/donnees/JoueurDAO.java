package full.fullfun.donnees;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import full.fullfun.modeles.Joueur;


public class JoueurDAO {

    /***** Attributs *****/

    protected List<Joueur> listeJoueurs;

    private static JoueurDAO instance = null;


    /***** Constructeurs *****/

    /**
     * Méthode utilisée pour récupérer le singleton du DAO. Seul moyen d'accès.
     * @return Le singleton JoueurDAO.
     */
    public static JoueurDAO getInstance(){
        if(null == instance){
            instance = new JoueurDAO();
        }
        return instance;
    }

    /**
     * Constructeur privé utilisé seulement par <code>getInstance()</code>.
     */
    private JoueurDAO(){
        listeJoueurs = new ArrayList<>();
    }


    /***** Accesseurs *****/

    /**
     * Getter de la liste des joueurs.
     * @return ArrayList de Joueur.
     * @see Joueur
     */
    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }


    /***** Méthodes *****/

    /**
     * Méthode à appeler pour charger la sauvegarde XML contenue dans le flux passé en paramètre.
     * @param flux Le flux permettant de lire le fichier XML; utilisé par le GestionnaireXML.
     * @see InputStream
     * @see GestionnaireXML
     */
    public void chargerSauvegarde(InputStream flux){
        listeJoueurs = new GestionnaireXML().lireJoueurs(flux);
        for (Joueur j : listeJoueurs){
            if (j.getId() > Joueur.lastId)
                Joueur.setLastId(j.getId());
        }
    }


    /**
     * Sauvegarde les joueurs dans le XML.
     * @param flux Flux d'écriture.
     */
    public void sauvegarderJoueurs(OutputStream flux){
        new GestionnaireXML().sauvegarderJoueurs(listeJoueurs, flux);
    }

    /**
     * Permet d'ajouter un joueur créé par l'utilisateur. Ne sauvegarde pas le joueur ajouté dans le XML!
     * @param joueur Le Joueur à ajouter.
     * @see Joueur
     */
    public void ajouterJoueur(Joueur joueur){
        for (Joueur j : listeJoueurs){
            if (j.equals(joueur)){
                j.setPseudo(joueur.getPseudo());
                j.setSexe(joueur.getSexe());
                return;
            }
        }
        listeJoueurs.add(joueur);
    }

    public void supprimerJoueur(Joueur joueur) {
        listeJoueurs.remove(joueur);
    }
}
