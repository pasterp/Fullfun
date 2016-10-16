package full.fullfun.donnees;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import full.fullfun.modeles.Joueur;
import full.fullfun.modeles.Question;
import full.fullfun.modeles.SetQuestions;


public class Partie {

    /***** Attributs *****/

    private List<Question> questions;

    private List<Question> questionsArchivees;

    private List<Joueur> joueurs;

    /***** Constructeurs *****/

    public Partie(){
        questions = new ArrayList<>();
        questionsArchivees = new ArrayList<>();
        joueurs = new ArrayList<>();
    }

    /***** Méthodes *****/

    /**
     * Permet d'ajouter le setQuestions à utiliser.
     * @param set Le SetQuestions de la partie.
     */
    public void ajouterSet(SetQuestions set){
        Log.d("Partie", "Ajout du set "+set.getNom()+" crée par "+set.getCreateur());
        for (Question q : set.getListeQuestions()) {
            questions.add(q);
        }
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
     * Mélange les questions. NE PLUS UTILISER ! Désormais fait dans le GenerateurPartie.
     * @deprecated
     * @see GenerateurPartie
     */
    public void melangerQuestions(){
        Collections.shuffle(questions);
    }

    /**
     * Retourne la question suivante à lire.
     * @return La Question à afficher.
     */
    public Question getQuestionSuivante(){
        Question q = questions.get(0);
        questionsArchivees.add(questions.remove(0));
        return q;
    }

    /**
     * @return true tant que la partie a encore des questions non lue.
     */
    public boolean hasQuestions(){
        return questions.size() > 0;
    }
}
