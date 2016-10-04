package me.phelipot.fullfun.donnees;

import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import me.phelipot.fullfun.modeles.Joueur;
import me.phelipot.fullfun.modeles.Question;
import me.phelipot.fullfun.modeles.SetQuestions;

public class GestionnaireXML {


    /***** Attributs statiques *****/

    protected final String ID = "id";

    protected final String CATEGORIE = "categorie";

    protected final String NOM = "nom";

    protected final String DATE = "date";

    protected final String SCORE = "score";

    protected final String DIFFICULTE = "difficulte";

    protected final String DUREE = "duree";

    protected final String CREATEUR = "createur";

    protected final String SET = "set";

    protected final String QUESTION = "question";

    protected final String PSEUDO = "pseudo";

    protected final String SEXE = "sexe";

    protected static final String JOUEUR = "joueur";

    /***** Attributs *****/

    protected InputStream flux;

    protected XmlPullParser lecteur;

    /***** Constructeurs *****/

    public GestionnaireXML() {

    }

    /***** Methodes *****/

    /**
     * Génère le Set de Questions à partir du fichier XMl passé en paramètre.
     * @param fichierXML Le fichier contenant les questions.
     * @return Le SetQuestions correspondant.
     */
    public SetQuestions lireSetQuestions(String fichierXML) {
        Question question = null;
        SetQuestions setQ = new SetQuestions();

        try {
            flux = new FileInputStream(new File(fichierXML));
            lecteur = Xml.newPullParser();
            lecteur.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            lecteur.setInput(flux, null);
            String nom;
            while (lecteur.next() != XmlPullParser.END_DOCUMENT){
                nom = lecteur.getName();
                switch (lecteur.getEventType()){
                    case XmlPullParser.START_TAG:
                        if (nom.equals(SET)){
                            lireSetQuestionsDonnees(lecteur, setQ);
                        }else if (nom.equals(QUESTION)){
                            setQ.ajouterQuestion(lireQuestionsDonnees(lecteur));
                        }
                        break;

                }
            }

            // Enfin on ferme le flux de lecture
            flux.close();
        }catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return setQ;
    }

    public SetQuestions lireSetQuestions(XmlResourceParser fichierXML) {
        Log.d("XLM_Reader", "Debut de la lecture des questions du set");
        Question question = null;
        SetQuestions setQ = new SetQuestions();

        try {
            lecteur = fichierXML;
            lecteur.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            lecteur.setInput(flux, null);
            String nom;
            while (lecteur.next() != XmlPullParser.END_DOCUMENT){
                Log.d("XLM_Reader", "question lue ");
                nom = lecteur.getName();
                switch (lecteur.getEventType()){
                    case XmlPullParser.START_TAG:
                        if (nom.equals(SET)){
                            lireSetQuestionsDonnees(lecteur, setQ);
                        }else if (nom.equals(QUESTION)){
                            setQ.ajouterQuestion(lireQuestionsDonnees(lecteur));
                        }
                        break;

                }
            }

            // Enfin on ferme le flux de lecture
            flux.close();
        }catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return setQ;
    }


    /**
     * GOOD VERSION - USE IT
     * @param flux - InputStream (openFile())
     * @return
     */
    public SetQuestions lireSetQuestions(InputStream flux) {
        Question question = null;
        SetQuestions setQ = new SetQuestions();

        try {
            lecteur = Xml.newPullParser();
            lecteur.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            lecteur.setInput(flux, null);
            String nom;
            while (lecteur.next() != XmlPullParser.END_DOCUMENT){
                nom = lecteur.getName();
                switch (lecteur.getEventType()){
                    case XmlPullParser.START_TAG:
                        if (nom.equals(SET)){
                            lireSetQuestionsDonnees(lecteur, setQ);
                        }else if (nom.equals(QUESTION)){
                            setQ.ajouterQuestion(lireQuestionsDonnees(lecteur));
                        }
                        break;

                }
            }

            // Enfin on ferme le flux de lecture
            flux.close();
        }catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return setQ;
    }

    private Question lireQuestionsDonnees(XmlPullParser lecteur) throws IOException, XmlPullParserException {
        Question question =new Question();
        question.setId(Integer.parseInt(lecteur.getAttributeValue(null, ID)));
        question.setCategorie(lecteur.getAttributeValue(null ,CATEGORIE));
        if (lecteur.next() == XmlPullParser.TEXT)
            question.setTexte(lecteur.getText());
        return question;
    }

    public List<Joueur> lireListeJoueurs(String fichierXML){
        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur = null;

        return joueurs;
    }

    public void lireSetQuestionsDonnees(XmlPullParser lecteur, SetQuestions setQuestions){
        setQuestions.setId(Integer.parseInt(lecteur.getAttributeValue(null, ID)));
        setQuestions.setCreateur(lecteur.getAttributeValue(null, CREATEUR));
        setQuestions.setDate(lecteur.getAttributeValue(null, DATE));
        setQuestions.setDifficulte(Integer.parseInt(lecteur.getAttributeValue(null, DIFFICULTE)));
        setQuestions.setDuree(Integer.parseInt(lecteur.getAttributeValue(null, DUREE)));
        setQuestions.setNom(lecteur.getAttributeValue(null, NOM));
        setQuestions.setScore(Integer.parseInt(lecteur.getAttributeValue(null, SCORE)));
    }
}
