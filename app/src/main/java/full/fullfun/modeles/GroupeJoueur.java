package full.fullfun.modeles;

import java.util.ArrayList;
import java.util.List;

public class GroupeJoueur {


    /***** Attributs *****/

    protected List<Joueur> joueurs;

    protected int id;

    protected String nom;

    /***** Constructeurs *****/

    public GroupeJoueur(){
        joueurs = new ArrayList<>();
    }

    public GroupeJoueur(int id, String nom, List<Joueur> joueurs){
        this();
        this.id = id;
        this.nom = nom;
        this.joueurs.addAll(joueurs);
    }

    /***** Accesseurs *****/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /***** Methodes *****/

    public void ajouterJoueur(Joueur joueur){
        joueurs.add(joueur);
    }
}
