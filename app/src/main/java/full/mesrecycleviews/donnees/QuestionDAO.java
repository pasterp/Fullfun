package full.mesrecycleviews.donnees;


import java.util.ArrayList;
import java.util.List;
import full.mesrecycleviews.modeles.Question;


public class QuestionDAO {


    protected List<Question> listeQuestion;

    private static QuestionDAO instance = null;

    public static QuestionDAO getInstance(){
        if(null == instance){
            instance = new QuestionDAO();
        }
        return instance;
    }
    private QuestionDAO(){
        listeQuestion = new ArrayList<>();
    }

    public List<Question> getListeQuestion() {
        return listeQuestion;
    }

    public void setListeQuestion(List<Question> listeQuestion) {
        this.listeQuestion = listeQuestion;
    }
}
