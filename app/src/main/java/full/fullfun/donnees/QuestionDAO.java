package full.fullfun.donnees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import full.fullfun.modeles.Question;

/**
 * Created by 1634836 on 03/10/2016.
 */

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
        listeQuestion = new ArrayList<Question>();
        Question question;

        question = new Question(0 , "Question", "Si tu n'as pas mangé de pates cette semaine leve la main.");
        listeQuestion.add(question);

        question = new Question(1 , "Question", "A&W ou McDo ? Votez en meme temps.");
        listeQuestion.add(question);

        question = new Question(2 , "Question", "Jordan doit sortir de la classe !");
        listeQuestion.add(question);

        question = new Question(3 , "Question", "Jordan peut rentrer ! ");
        listeQuestion.add(question);
    }

    public List<Question> getListeQuestion() {
        return listeQuestion;
    }

    public void setListeQuestion(List<Question> listeQuestion) {
        this.listeQuestion = listeQuestion;
    }
}
