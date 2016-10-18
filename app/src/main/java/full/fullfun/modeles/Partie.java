package full.fullfun.modeles;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import full.fullfun.donnees.GenerateurPartie;


public class Partie implements Serializable{

    /***** Attributs *****/

    private SetQuestions setQuestions;

    private List<Question> questionsArchivees;

    private List<Joueur> joueurs;

    private int taille;

    /***** Constructeurs *****/

    public Partie(){
        setQuestions = new SetQuestions();
        questionsArchivees = new ArrayList<>();
        joueurs = new ArrayList<>();
        taille = 0;
    }

    /***** Méthodes *****/

    /**
     * Permet d'ajouter le setQuestions à utiliser.
     * @param set Le SetQuestions de la partie.
     */
    public void ajouterSet(SetQuestions set){
        setQuestions.ajouterQuestionsFromSetQuestions(set);
        taille += set.getListeQuestions().size();
    }

    /**
     * permet d'ajouter une liste de joueurs.
     * @param joueurs La List à ajouter.
     */
    public void ajouterJoueurs(List<Joueur> joueurs){
        this.joueurs.addAll(joueurs);
    }

    /**
     * Permet d'ajouter un joueur.
     * @param joueur Le Joueur à ajouter.
     */
    public void ajouterJoueur(Joueur joueur){
        this.joueurs.add(joueur);
    }

    /**
     * Mélange les setQuestions. NE PLUS UTILISER ! Désormais fait dans le GenerateurPartie.
     * @deprecated
     * @see GenerateurPartie
     */
    public void melangerQuestions(){
        Collections.shuffle(setQuestions.getListeQuestions());
    }

    /**
     * Retourne la question suivante à lire.
     * @return La Question à afficher.
     */
    public Question getQuestionSuivante(){
        Question q = setQuestions.getListeQuestions().get(0);
        questionsArchivees.add(setQuestions.getListeQuestions().remove(0));
        return q;
    }

    /**
     * @return true tant que la partie a encore des setQuestions non lue.
     */
    public boolean hasQuestions(){
        return setQuestions.getListeQuestions().size() > 0;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public SetQuestions getSetQuestions() {
        return setQuestions;
    }

    public int getTaille() {
        return taille;
    }

    public boolean isBeforeLast() {
        return setQuestions.getListeQuestions().size() == 1;
    }

    public Question getQuestionArchivee(int i){
        return questionsArchivees.get(i);
    }
}
