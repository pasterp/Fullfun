package full.mesrecycleviews.modeles;

/**
 * Created by quent on 13/10/2016.
 */

public class QuestionEtat extends Question {

    /***** Attributs *****/

    protected String etat;

    protected int duree;

    /**
     * Question affichée à la fin de l'état.
     */
    protected Question questionFinEtat;

    /***** Constructeurs *****/

    public QuestionEtat(){
        super();
    }

    public QuestionEtat(int id, String categorie, String texte, String etat){
        super(id, categorie, texte);
        this.etat = etat;
    }


    /***** Accesseurs *****/

    public void setEtat(String etat){
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Question getQuestionFinEtat() {
        return questionFinEtat;
    }

    public void setQuestionFinEtat(Question questionFinEtat) {
        this.questionFinEtat = questionFinEtat;
    }

    /***** Methodes *****/
}
