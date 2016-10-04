package me.phelipot.fullfun.donnees;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.phelipot.fullfun.R;
import me.phelipot.fullfun.modeles.SetQuestions;

/**
 * Created by 1634836 on 03/10/2016.
 */

public class SetQuestionsDAO {
    protected List<SetQuestions> listeSetQuestion;
    protected QuestionDAO accesseurQuestionDAO = QuestionDAO.getInstance();
    private static SetQuestionsDAO instance = null;
    private AssetManager manageurAsset;

    public static SetQuestionsDAO getInstance(){
        if(null == instance){
            instance = new SetQuestionsDAO();
        }
        return instance;
    }
    private SetQuestionsDAO(){
        listeSetQuestion = new ArrayList<SetQuestions>();
    }

    public void initialisationQuestions(AssetManager manageurAssets){
        if (this.manageurAsset == null) {
            SetQuestions setQuestions = null;
            this.manageurAsset = manageurAssets;

            Log.d("XML_Assets", "Liste des assets:");
            try {
                for (String s :
                        Resources.getSystem().getAssets().list(File.separator + "assets" + File.separator)) {
                    Log.d("XML_Assets", s);
                }


                setQuestions = new GestionnaireXML().lireSetQuestions(manageurAsset.open("questions.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            listeSetQuestion.add(setQuestions);
        }
    }

    public List<HashMap<String, String>> listerSetQuestionsEnHasmap(){

        List<HashMap<String , String>> listeSetQuestionHashMap = new ArrayList<>();

        for (SetQuestions sq : listeSetQuestion){
            listeSetQuestionHashMap.add(sq.exporterHashMap());
        }
        return listeSetQuestionHashMap;
    }

    public boolean isInitialise(){
        return manageurAsset != null;
    }

    public List<SetQuestions> getListeSetQuestion() {
        return listeSetQuestion;
    }
}
