package full.mesrecycleviews.donnees;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import full.mesrecycleviews.modeles.SetQuestions;


/**
 * Created by 1634836 on 03/10/2016.
 */

public class SetQuestionsDAO {
    protected List<SetQuestions> listeSetQuestion;
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


                setQuestions = new GestionnaireXML().lireSetQuestions(manageurAsset.open("set_01.xml"));
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
