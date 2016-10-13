package full.mesrecycleviews.donnees;

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

import full.mesrecycleviews.modeles.GroupeJoueur;
import full.mesrecycleviews.modeles.Joueur;
import full.mesrecycleviews.modeles.Question;
import full.mesrecycleviews.modeles.QuestionEtat;
import full.mesrecycleviews.modeles.SetQuestions;

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
                        // Si on détecte une balise Groupe.
                        if (nom.equals(GROUPE)){
                            groupe = new GroupeJoueur();
                            groupe.setId(Integer.parseInt(lecteur.getAttributeValue(null, ID)));
                            groupe.setNom(lecteur.getAttributeValue(null, NOM));
                        // Si on détecte une balise Joueur.
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


    /**
     * Ecrit tout les GroupeJoueurs dans le fichier passé en paramètre par le biais du flux.
     * @param groupes La liste de tout les groupeJoueurs.
     * @param flux Le flux d'écriture.
     */
    public void sauvegarderGroupeJoueurs(List<GroupeJoueur> groupes, OutputStream flux){

        XmlSerializer serializer = Xml.newSerializer();

        try {
            serializer.setOutput(flux, "UTF-8");
            serializer.startDocument(null, true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            // Iteration des GroupesJoueurs
            for (GroupeJoueur g : groupes){
                serializer.startTag(null, GROUPE);
                serializer.attribute(null, ID, String.valueOf(g.getId()));
                serializer.attribute(null, NOM, g.getNom());
                // Itération des Joueurs
                for (Joueur j : g.getJoueurs()){
                    serializer.startTag(null, JOUEUR);
                    serializer.attribute(null, ID, String.valueOf(j.getId()));
                    serializer.attribute(null, PSEUDO, j.getPseudo());
                    serializer.attribute(null, SEXE, j.getSexe().toString());
                    serializer.endTag(null, JOUEUR);
                }
                serializer.endTag(null, GROUPE);
            }
            // Fin d'écriture - Flush du buffer puis fermeture du flux.
            serializer.endDocument();
            serializer.flush();
            flux.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
