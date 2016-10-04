package me.phelipot.fullfun.donnees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.phelipot.fullfun.modeles.SetQuestions;

/**
 * Created by 1634836 on 03/10/2016.
 */

public class SetQuestionsDAO {
    protected List<SetQuestions> listeSetQuestion;
    protected QuestionDAO accesseurQuestionDAO = QuestionDAO.getInstance();
    private static SetQuestionsDAO instance = null;

    public static SetQuestionsDAO getInstance(){
        if(null == instance){
            instance = new SetQuestionsDAO();
        }
        return instance;
    }
    private SetQuestionsDAO(){
        listeSetQuestion = new ArrayList<SetQuestions>();
        SetQuestions setQuestions;

        setQuestions = new GestionnaireXML().lireSetQuestions("C:\\Users\\quent\\Documents\\GitHub\\Fullfun\\app\\src\\main\\java\\full\\fullfun\\modeles\\questions.xml");

        listeSetQuestion.add(setQuestions);
    }

    public List<HashMap<String, String>> listerSetQuestionsEnHasmap(){

        List<HashMap<String , String>> listeSetQuestionHashMap = new ArrayList<>();

        for (SetQuestions sq : listeSetQuestion){
            listeSetQuestionHashMap.add(sq.exporterHashMap());
        }
        return listeSetQuestionHashMap;
    }
}
