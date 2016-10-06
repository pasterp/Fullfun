package me.phelipot.fullfun.modeles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SetQuestions {

    /***** Attributs *****/

    protected int id;

    protected String nom;

    protected String createur;

    protected int difficulte;

    protected String date;

    protected int duree;

    protected List<Question> listeQuestions;

    protected int score;

    /***** Constructeurs *****/

    public SetQuestions(){
        listeQuestions = new ArrayList<>();
    }

    public SetQuestions(int id, String nom, List<Question> listeQuestions){
        this();
        this.id = id;
        this.nom = nom;
        this.listeQuestions = listeQuestions;
    }

    public SetQuestions(int id, String nom, String createur, String date, int difficulte, int score){
        this.id = id;
        this.nom = nom;
        this.createur = createur;
        this.date = date;
        this.difficulte = difficulte;
        this.score = score;
    }

    /***** Accesseurs *****/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public List<Question> getListeQuestions() {
        return listeQuestions;
    }

    public void setListeQuestions(List<Question> listeQuestions) {
        this.listeQuestions = listeQuestions;
    }

    public void ajouterQuestion(Question question){
        listeQuestions.add(question);
    }

    public Question getQuestion(int id){
        for (Question q : listeQuestions){
            if (q.getId() == id)
                return q;
        }
        return null;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /***** Methodes *****/

    public HashMap<String, String> exporterHashMap(){
        HashMap<String, String> setQuestion = new HashMap<String, String>();
        setQuestion.put("id", String.valueOf(this.id));
        setQuestion.put("nom", String.valueOf(this.nom));
        setQuestion.put("createur", String.valueOf(this.createur));

        return setQuestion;
    }


    /**
     * Ajoute toutes les questions du set donné.
     * @param set Le set contenant les questions à ajouter.
     */
    public void ajouterSetQuestions(SetQuestions set) {
        for (Question q : set.listeQuestions){
            listeQuestions.add(new Question(q.getId(), q.getCategorie(), q.getTexte()));
        }
    }
}

