package full.fullfun.donnees;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import full.fullfun.exceptions.ParsingNomJoueurImpossibleException;
import full.fullfun.modeles.Joueur;
import full.fullfun.modeles.Partie;
import full.fullfun.modeles.Question;
import full.fullfun.modeles.QuestionEtat;
import full.fullfun.modeles.SetQuestions;
import full.fullfun.modeles.Sexe;


/**
 * Génère le set final à partir des joueurs et des sets, remplace les balises par les valeurs
 * correspondantes.
 */
public class GenerateurPartie {


    /**
     * Cette valeur est ajouté à la borne supérieur du random pour le nombre de gorgée afin
     * d'amplifier la range de génération artificiellement. Pour le moment ça semble nécessaire
     * pour avoir des valeurs "correctes" de gorgées.
     */
    private static final int MARGE_GORGEE = 2;
    private static final int LIMITE_GORGEE = 10;

    /***** Constantes *****/

    private final String TAG_HOMME                     = "#{h}";

    private final String TAG_FEMME                     = "#{f}";

    private final String TAG_MIXTE                     = "#{m}";

    private final String TAG_GORGEE_ALEATOIRE          = "#{g}";

    private final String TAG_GORGEE_ALEATOIRE_MULTIPLE = "#{gs}"; // Gorgée aléatoire strictement supérieur à 1

    private final String TAG_GORGEE_ALEATOIRE_EXTREME  = "#{gx}"; // Nombre élevé de gorgée !

    /***** Attributs *****/

    private static GenerateurPartie instance;

    /***** Constructeur *****/

    private GenerateurPartie(){

    }


    public static GenerateurPartie getInstance(){
        if (instance == null)
            instance = new GenerateurPartie();
        return instance;
    }

    /***** Accesseurs *****/


    /***** Methodes *****/

    /**
     * Méthode à appeler pour générer le SetQuestion utilisé lors de la partie. Il va regrouper toutes
     * les questions de tout les SetQuestions passés en paramètres puis d'y générer les différents
     * éléments dynamiques comme les prénoms et les nombres de gorgées.
     * @param sets Une List de SetQuestions qui seront utilisés pour la partie.
     * @param joueurs Une List contenant tout les joueurs de la partie.
     * @return La Partie prête à l'utilisage.
     */
    public Partie genererPartie(List<SetQuestions> sets, List<Joueur> joueurs)
            throws ParsingNomJoueurImpossibleException {
        Partie partie = new Partie();
        partie.ajouterJoueurs(joueurs);

        SetQuestions setFinal = new SetQuestions();
        setFinal.setId(999);
        setFinal.setCreateur("FullFun");
        setFinal.setScore(5);
        setFinal.setDate("2016-01-01");
        int totalDifficulte = 0;
        // Regroupe toutes les questions dans un même set.
        List<Question> questions = new ArrayList<>();
        for (SetQuestions set : sets){
            questions.addAll(set.getListeQuestions());
            totalDifficulte += set.getDifficulte();
        }
        //setFinal.setListeQuestions(preparerListeQuestions(questions));
        setFinal.setListeQuestions(questions);
        // Moyenne des difficultés de tout les sets.
        setFinal.setDifficulte(totalDifficulte / sets.size());
        parserQuestions(setFinal, joueurs);
        Collections.shuffle(setFinal.getListeQuestions());
        partie.ajouterSet(setFinal);

        // Ajout de la partie à la base de données.
        BaseDeDonnees bdd = BaseDeDonnees.getInstance();
        if (bdd != null){
            bdd.sauvegarderPartie(partie);
        }
        return partie;
    }

    /**
     * Mélange correctement toutes les questions.
     * Pour le moment, fait juste un shuffle classique mais après avec les nouveaux types de questions
     * il faudra modifier ici.
     * @param questions Le setFinal.
     */
    private List<Question> preparerListeQuestions(List<Question> questions) {
        // TODO: 13/10/2016 Extraire les questions nécessitant un placement particulier, shuffle les autres puis placer procéduralement celles extraites.
        // Séparations des types de questions.
        List<Question> questionsSpeciales = new ArrayList<>();
        List<Question> questionsNormales = new ArrayList<>();
        for (Question q : questions){
            if (q instanceof QuestionEtat){
                questionsSpeciales.add(q);
            }else{
                questionsNormales.add(q);
            }
        }
        // Ajout de toutes les questions normales
        Collections.shuffle(questionsNormales);
        // Traitement des questions à placement particulier.
        for (Question q : questionsSpeciales){
            if (q instanceof QuestionEtat){
                Random rng = new Random();
                QuestionEtat qEtat = (QuestionEtat)q;
                // Génération de la durée de l'état en fonction de la longueur du Set final.
                int duree = rng.nextInt(2 + (questionsNormales.size() / 4));
                if (duree < 2){
                    duree = 2;
                }
                qEtat.setDuree(duree);
                // Calcul de la place maximale de la question en fonction de la durée de l'état.
                int place = rng.nextInt(questionsNormales.size() - qEtat.getDuree() - 1);
                Log.d("Place:", String.valueOf(place));
                if (place < 0)
                    place = 0;
                // Ajout de la QuestionEtat et de sa Question servant de fin d'état.
                questionsNormales.add(place, qEtat);
                questionsNormales.add(place + qEtat.getDuree(), qEtat.getQuestionFinEtat());
            }
        }

        return questionsNormales;
    }

    /**
     * Parse chaque question du set final.
     * @param setFinal Le SetQuestion final à parser.
     * @param joueurs La liste des joueurs.
     */
    private void parserQuestions(SetQuestions setFinal, List<Joueur> joueurs) throws ParsingNomJoueurImpossibleException{
        boolean ok;
        // Parsing des questions
        for (Question q : setFinal.getListeQuestions()){
            ok = false;
            // Tant qu'on trouve des tags dans le texte on continue à parser.
            while (!ok){
                if (q.getTexte().contains(TAG_HOMME))
                    parserNom(q, joueurs, TAG_HOMME);
                else if (q.getTexte().contains(TAG_FEMME))
                    parserNom(q, joueurs, TAG_FEMME);
                else if (q.getTexte().contains(TAG_MIXTE))
                    parserNom(q, joueurs, TAG_MIXTE);
                else if (q.getTexte().contains(TAG_GORGEE_ALEATOIRE))
                    parserGorgee(q, setFinal.getDifficulte(), TAG_GORGEE_ALEATOIRE);
                else if (q.getTexte().contains(TAG_GORGEE_ALEATOIRE_MULTIPLE))
                    parserGorgee(q, setFinal.getDifficulte(), TAG_GORGEE_ALEATOIRE_MULTIPLE);
                else if( q.getTexte().contains(TAG_GORGEE_ALEATOIRE_EXTREME))
                    parserGorgee(q, setFinal.getDifficulte(), TAG_GORGEE_ALEATOIRE_EXTREME);
                else
                    ok = true; // Aucun tag détecté -> Le texte a été entièrement traitée
            }

        }
    }

    /**
     * Insère un nombre de gorgée au bon endroit en fonction de la difficulté.
     * @param q La question à parser.
     * @param difficulte La difficulté du set.
     * @param tag Le tag correspondant.
     */
    private void parserGorgee(Question q, int difficulte, String tag) {
        int nbr;
        switch (tag){
            case TAG_GORGEE_ALEATOIRE:
                nbr = 1 + new Random().nextInt(difficulte + MARGE_GORGEE);
                q.setTexte(q.getTexte().replaceFirst(Pattern.quote(tag), String.valueOf(nbr)));
                if (nbr > 1)
                    q.setTexte(q.getTexte().replaceFirst(Pattern.quote("gorgée"), "gorgées"));
                break;
            case TAG_GORGEE_ALEATOIRE_MULTIPLE:
                nbr = 2 + new Random().nextInt(difficulte + MARGE_GORGEE);
                q.setTexte(q.getTexte().replaceFirst(Pattern.quote(tag), String.valueOf(nbr)));
                q.setTexte(q.getTexte().replaceFirst(Pattern.quote("gorgée"), "gorgées"));
                break;
            case TAG_GORGEE_ALEATOIRE_EXTREME:
                nbr = 4 + new Random().nextInt(difficulte + MARGE_GORGEE);
                if (nbr > LIMITE_GORGEE)
                    nbr = LIMITE_GORGEE;
                q.setTexte(q.getTexte().replaceFirst(Pattern.quote(tag), String.valueOf(nbr)));
                q.setTexte(q.getTexte().replaceFirst(Pattern.quote("gorgée"), "gorgées"));
                break;
        }
    }

    /**
     * Insère un nom de joueur au bon endroit et correspondant au genre souhaité.
     * @param q La question à parser.
     * @param joueurs La liste des joueurs.
     * @param tag Le tag correspondant au genre.
     */
    private void parserNom(Question q, List<Joueur> joueurs, String tag) throws ParsingNomJoueurImpossibleException {
        List<Joueur> potentielsJoueurs = new ArrayList<>();
        Sexe sexe;
        switch (tag){
            case TAG_HOMME:
                sexe = Sexe.Homme;
                break;
            case TAG_FEMME:
                sexe = Sexe.Femme;
                break;
            default:
            case TAG_MIXTE:
                sexe = Sexe.Confus;
                break;
        }
        // Récupération de tout les potentiels joueurs
        if (sexe.equals(Sexe.Confus))
            potentielsJoueurs.addAll(joueurs);
        else{
            for (Joueur j : joueurs){
                if (j.getSexe().equals(sexe) && !(q.getTexte().contains(j.getPseudo())) && !potentielsJoueurs.contains(j))
                    potentielsJoueurs.add(j);
            }
        }
        try {
            // Parsing du nom en UNE LIGNE: Remplace le tag par un nom de joueur choisi aléatoirement.
            q.setTexte(
                    q.getTexte().replaceFirst(Pattern.quote(tag),
                            potentielsJoueurs.get(new Random().nextInt(potentielsJoueurs.size())).getPseudo()));
            // Si la question est une question d'état, alors le nom du joueur est aussi remplacée dans la question de fin d'état
        /*if (q instanceof QuestionEtat)
            ((QuestionEtat)q).getQuestionFinEtat().setTexte(
                    q.getTexte().replaceFirst(Pattern.quote(tag),
                            potentielsJoueurs.get(new Random().nextInt(potentielsJoueurs.size())).getPseudo()));*/
        }catch (IllegalArgumentException e){
            throw new ParsingNomJoueurImpossibleException();
        }

    }
}
