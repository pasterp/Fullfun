package full.fullfun.donnees;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import full.fullfun.modeles.GroupeJoueur;
import full.fullfun.modeles.Joueur;
import full.fullfun.modeles.Question;
import full.fullfun.modeles.QuestionEtat;
import full.fullfun.modeles.SetQuestions;

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

    //protected final String GROUPE = "groupe";

    private final String CATEGORIE_ETAT = "etat";

    /***** Attributs *****/

    protected XmlPullParser lecteur;

    /***** Constructeurs *****/

    public GestionnaireXML() {

    }

    /***** Methodes *****/


    /**
     * Lit le SetQuestion passé en paramètre par le biais du flux.
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
                        // Si on trouve un tag de Set on le lit
                        if (nom.equals(SET)){
                            lireSetQuestionsDonnees(lecteur, setQ);
                        // Sinon si c'est une question on l'ajoute au set.
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
     * Lit les données du Setquestion détecté.
     * @param lecteur Le lecteur XML.
     * @param setQuestions Le SetQuestions à paramétrer.
     */
    public void lireSetQuestionsDonnees(XmlPullParser lecteur, SetQuestions setQuestions){
        setQuestions.setId(Integer.parseInt(lecteur.getAttributeValue(null, ID)));
        setQuestions.setCreateur(lecteur.getAttributeValue(null, CREATEUR));
        setQuestions.setDate(lecteur.getAttributeValue(null, DATE));
        setQuestions.setDifficulte(Integer.parseInt(lecteur.getAttributeValue(null, DIFFICULTE)));
        setQuestions.setDuree(Integer.parseInt(lecteur.getAttributeValue(null, DUREE)));
        setQuestions.setNom(lecteur.getAttributeValue(null, NOM));
        setQuestions.setScore(Integer.parseInt(lecteur.getAttributeValue(null, SCORE)));
    }

    /**
     * Lit la question suicante dans le XML lorsqu'on en détecte une.
     * @param lecteur Le lecteur XML
     * @return La Question générée à partir du XML.
     * @throws IOException
     * @throws XmlPullParserException
     */
    private Question lireQuestionsDonnees(XmlPullParser lecteur) throws IOException, XmlPullParserException {
        Question question;
        int id = Integer.parseInt(lecteur.getAttributeValue(null, ID));
        String categorie = lecteur.getAttributeValue(null ,CATEGORIE);
        String texte = "";
        if (lecteur.next() == XmlPullParser.TEXT)
            texte = lecteur.getText();
        switch (categorie){
            case CATEGORIE_ETAT:
                lecteur.next();
                Question finEtat = lireQuestionsDonnees(lecteur);
                question = new QuestionEtat();
                ((QuestionEtat)question).setQuestionFinEtat(finEtat);
                Log.e("Texte fin etat", ((QuestionEtat)question).getQuestionFinEtat().getTexte());
                break;
            default:
                question = new Question();
        }
        question.setId(id);
        question.setCategorie(categorie);
        question.setTexte(texte);
        return question;
    }

    /**
     * Lit tout les groupes de joueur dans le fichier XML passé en paramètre par le biais du flux.
     * @param flux Le flux de lecture.
     * @return Une List de GroupeJoueur.
     */
    public List<Joueur> lireJoueurs(InputStream flux){
        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur = null;
        try {
            lecteur = Xml.newPullParser();
            lecteur.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            lecteur.setInput(flux, null);
            String nom;
            while (lecteur.next() != XmlPullParser.END_DOCUMENT){
                nom = lecteur.getName();
                switch (lecteur.getEventType()){
                    case XmlPullParser.START_TAG:
                        // Si on détecte une balise Joueur.
                        if(nom.equals(JOUEUR)){
                            joueur = new Joueur();
                            joueur.setId(Integer.parseInt(lecteur.getAttributeValue(null, ID)));
                            joueur.setPseudo(lecteur.getAttributeValue(null, PSEUDO));
                            joueur.setSexe(lecteur.getAttributeValue(null, SEXE));
                            joueurs.add(joueur);
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
        return joueurs;
    }


    /**
     * Ecrit tout les GroupeJoueurs dans le fichier passé en paramètre par le biais du flux.
     * @param flux Le flux d'écriture.
     */
    public void sauvegarderJoueurs(List<Joueur> joueurs, OutputStream flux){
        XmlSerializer serializer = Xml.newSerializer();
        try {
            serializer.setOutput(flux, "UTF-8");
            serializer.startDocument(null, true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            // Iteration des Joueurs
            for (Joueur j : joueurs){
                serializer.startTag(null, JOUEUR);
                serializer.attribute(null, ID, String.valueOf(j.getId()));
                serializer.attribute(null, PSEUDO, j.getPseudo());
                serializer.attribute(null, SEXE, j.getSexe().toString());
                serializer.endTag(null, JOUEUR);
            }
            // Fin d'écriture - Flush du buffer puis fermeture du flux.
            serializer.endDocument();
            serializer.flush();
            flux.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sauvegarde le SetQuestion passé en paramètre dans son propre fichier XML.
     * @param setQ Le SetQuestions à sauvegarder.
     * @param flux Le flux d'écriture.
     */
    public void sauvegarderSet(SetQuestions setQ, OutputStream flux){
        XmlSerializer serializer = Xml.newSerializer();
        try{
            serializer.setOutput(flux, "UTF-8");
            serializer.startDocument(null ,true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

            // Ecriture des attributs du SetQuestion
            serializer.startTag(null, SET);
            serializer.attribute(null, ID, String.valueOf(setQ.getId()));
            serializer.attribute(null, CREATEUR, setQ.getCreateur());
            serializer.attribute(null, DATE, setQ.getDate());
            serializer.attribute(null, DIFFICULTE, String.valueOf(setQ.getDifficulte()));
            serializer.attribute(null, DUREE, String.valueOf(setQ.getDuree()));
            serializer.attribute(null, NOM, setQ.getNom());
            serializer.attribute(null, SCORE, String.valueOf(setQ.getScore()));
            // Ecriture des questions du Set
            for (Question q : setQ.getListeQuestions()){
                serializer.startTag(null, QUESTION);
                serializer.attribute(null, ID, String.valueOf(q.getId()));
                serializer.attribute(null, CATEGORIE, q.getCategorie());
                serializer.text(q.getTexte());
                serializer.endTag(null, QUESTION);
            }
            serializer.endTag(null, SET);
            flux.flush();
            flux.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
