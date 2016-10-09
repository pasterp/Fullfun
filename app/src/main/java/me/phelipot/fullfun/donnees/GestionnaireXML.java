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

import me.phelipot.fullfun.modeles.GroupeJoueur;
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

    protected final String JOUEUR = "joueur";

    protected final String GROUPE = "groupe";

    /***** Attributs *****/

    protected XmlPullParser lecteur;

    /***** Constructeurs *****/

    public GestionnaireXML() {

    }

    /***** Methodes *****/


    /**
     * GOOD VERSION - USE IT
     * @param flux - InputStream (openFile())
     * @return Le SetQuestion du fichier.
     */
    public SetQuestions lireSetQuestions(InputStream flux) {
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

    public List<GroupeJoueur> lireGroupesJoueurs(InputStream flux){
        List<Joueur> joueurs = new ArrayList<>();
        List<GroupeJoueur> groupes = new ArrayList<>();
        Joueur joueur;
        GroupeJoueur groupe = null;
        try {
            lecteur = Xml.newPullParser();
            lecteur.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            lecteur.setInput(flux, null);
            String nom;
            while (lecteur.next() != XmlPullParser.END_DOCUMENT){
                nom = lecteur.getName();
                switch (lecteur.getEventType()){
                    case XmlPullParser.START_TAG:
                        if (nom.equals(GROUPE)){
                            groupe = new GroupeJoueur();
                            groupe.setId(Integer.parseInt(lecteur.getAttributeValue(null, ID)));
                            groupe.setNom(lecteur.getAttributeValue(null, NOM));


                        }else if(nom.equals(JOUEUR)){
                            joueur = new Joueur();
                            joueur.setId(Integer.parseInt(lecteur.getAttributeValue(null, ID)));
                            joueur.setPseudo(lecteur.getAttributeValue(null, PSEUDO));
                            joueur.setSexe(lecteur.getAttributeValue(null, SEXE));
                            joueurs.add(joueur);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (groupe != null && !joueurs.isEmpty() && nom.equals(GROUPE)){
                            groupe.setJoueurs(joueurs);
                            groupes.add(groupe);
                            joueurs = new ArrayList<>();
                        }
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
        return groupes;
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
