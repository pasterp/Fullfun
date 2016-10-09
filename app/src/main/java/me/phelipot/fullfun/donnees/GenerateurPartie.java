package me.phelipot.fullfun.donnees;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import me.phelipot.fullfun.modeles.Joueur;
import me.phelipot.fullfun.modeles.Question;
import me.phelipot.fullfun.modeles.SetQuestions;
import me.phelipot.fullfun.modeles.Sexe;

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

    /***** Constantes *****/

    private final String TAG_HOMME =            "#{h}";

    private final String TAG_FEMME =            "#{f}";

    private final String TAG_MIXTE =            "#{m}";

    private final String TAG_GORGEE_ALEATOIRE = "#{g}";

    /***** Attributs *****/

    /***** Constructeur *****/

    public GenerateurPartie(){}


    /***** Accesseurs *****/


    /***** Methodes *****/

    public SetQuestions genererSet(List<SetQuestions> sets, List<Joueur> joueurs){
        SetQuestions setFinal = new SetQuestions();
        int totalDifficulte = 0;
        // Regroupe toutes les questions dans un même set.
        for (SetQuestions set : sets){
            setFinal.ajouterSetQuestions(set);
            totalDifficulte += set.getDifficulte();
        }
        setFinal.setDifficulte(totalDifficulte / sets.size());
        Log.d("Difficulte", "yo");
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
                else
                    ok = true;
            }

        }
        return setFinal;
    }

    /**
     * Insère un nombre de gorgée au bon endroit en fonction de la difficulté.
     * @param q La question à parser.
     * @param difficulte La difficulté du set.
     * @param tag Le tag correspondant.
     */
    private void parserGorgee(Question q, int difficulte, String tag) {
        switch (tag){
            case TAG_GORGEE_ALEATOIRE:
                int nbr = 1 + new Random().nextInt(difficulte + MARGE_GORGEE);
                q.setTexte(q.getTexte().replaceFirst(Pattern.quote(tag), String.valueOf(nbr)));
                if (nbr > 1)
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
    private void parserNom(Question q, List<Joueur> joueurs, String tag) {
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
        // Parsing du nom en UNE LIGNE
        q.setTexte(q.getTexte().replaceFirst(Pattern.quote(tag),potentielsJoueurs.get(new Random().nextInt(potentielsJoueurs.size())).getPseudo()));
    }
}
