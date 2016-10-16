package full.fullfun.donnees;


import java.util.ArrayList;
import java.util.List;
import full.fullfun.modeles.Question;


public class QuestionDAO {


    /***** Attributs *****/

    /**
     * ArrayList des Questions connus.
     */
    protected List<Question> listeQuestion;

    /**
     * Singleton du DAO. Accessible via <code>getInstance()</code>.
     */
    private static QuestionDAO instance = null;

    /***** Constructeurs *****/

    /**
     * Méthode utilisée pour accéder au Singleton du DAO. Seul moyen d'accéder à cette classe.
     * @return Le Singleton QuestionDAO.
     */
    public static QuestionDAO getInstance(){
        if(null == instance){
            instance = new QuestionDAO();
        }
        return instance;
    }

    /**
     * Constructeur privé uniquement utilisé par <code>getInstance()</code>.
     */
    private QuestionDAO(){
        listeQuestion = new ArrayList<>();
    }


    /***** Accesseurs *****/

    /**
     * Getter de la liste de question.
     * @return ArrayList de Question
     * @see Question
     */
    public List<Question> getListeQuestion() {
        return listeQuestion;
    }

    /**
     * Setter de la liste de question.
     * @param listeQuestion ArrayList de Question.
     */
    public void setListeQuestion(List<Question> listeQuestion) {
        this.listeQuestion = listeQuestion;
    }


    /***** Méthodes *****/

    /**
     * Permet d'ajouter une Question au DAO.
     * @param question La Question à ajouter.
     */
    public void ajouterQuestion(Question question){
        listeQuestion.add(question);
    }
}
