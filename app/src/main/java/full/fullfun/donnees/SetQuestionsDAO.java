package full.fullfun.donnees;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import full.fullfun.modeles.SetQuestions;


public class SetQuestionsDAO {

    /***** Attributs *****/

    /**
     * ArrayList des SetsQuestions.
     * @see SetQuestions
     */
    protected List<SetQuestions> listeSetQuestion;

    /**
     * L'AssetManager permet d'accéder au Set de base stocké dans les assets du projet.
     */
    private AssetManager manageurAsset;

    /**
     * Instance Singleton du DAO. Le seul moyen d'y accéder et de passer par <code>getInstance()</code>.
     */
    private static SetQuestionsDAO instance = null;


    /***** Constructeurs *****/

    /**
     * Méthode permettant d'accéder à l'instance du DAO.
     * @return Le singleton de SetQuestionsDAO.
     */
    public static SetQuestionsDAO getInstance(){
        if(null == instance){
            instance = new SetQuestionsDAO();
        }
        return instance;
    }

    /**
     * Constructeur privé utilisé par <code>getInstance()</code>.
     */
    private SetQuestionsDAO(){
        listeSetQuestion = new ArrayList<>();
    }


    /***** Accesseurs *****/

    /**
     * Getter de la liste des sets de question.
     * @return Arraylist de SetQuestions.
     * @see SetQuestions
     */
    public List<SetQuestions> getListeSetQuestion() {
        return listeSetQuestion;
    }


    /***** Méthodes *****/

    /**
     * Permet de charger le SetQuestion de base. Nécessite d'être appelée une unique fois.
     * @param manageurAssets L'AssetManager permettant de trouver les assets.
     */
    public void chargerSauvegardeAsset(AssetManager manageurAssets){
        if (this.manageurAsset == null) {
            this.manageurAsset = manageurAssets;

            try {
                listeSetQuestion.add(new GestionnaireXML().lireSetQuestions(manageurAsset.open("set_01.xml")));
                listeSetQuestion.add(new GestionnaireXML().lireSetQuestions(manageurAsset.open("set_02.xml")));
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


    /**
     * Ajoute le set de question passé en paramètre au DAO.
     * @param setQuestion SetQuestions à ajouter.
     */
    public void ajouterSetQuestions(SetQuestions setQuestion) {
        listeSetQuestion.add(setQuestion);
    }
}
