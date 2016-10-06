package me.phelipot.fullfun.donnees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.phelipot.fullfun.modeles.Joueur;
import me.phelipot.fullfun.modeles.Question;
import me.phelipot.fullfun.modeles.SetQuestions;
import me.phelipot.fullfun.modeles.Sexe;

/**
 * Génère le set final à partir des joueurs et des sets, remplace les balises par les valeurs
 * correspondantes.
 */
public class GenerateurPartie {

    /***** Constantes *****/

    private final String TAG_HOMME = "#{h}";

    private final String TAG_FEMME = "#{f}";

    private final String TAG_MIXTE = "#{m}";

    /***** Attributs *****/

    /***** Constructeur *****/

    public GenerateurPartie(){}


    /***** Accesseurs *****/


    /***** Methodes *****/

    public SetQuestions genererSet(List<SetQuestions> sets, List<Joueur> joueurs){
        SetQuestions setFinal = new SetQuestions();
        // Regroupe toutes les questions dans un même set.
        for (SetQuestions set : sets){
            setFinal.ajouterSetQuestions(set);
        }
        // Parsing des questions
        for (Question q : setFinal.getListeQuestions()){
            if (q.getTexte().contains(TAG_HOMME))
                parserNom(q, joueurs, TAG_HOMME);
            else if (q.getTexte().contains(TAG_FEMME))
                parserNom(q, joueurs, TAG_FEMME);
            else if (q.getTexte().contains(TAG_MIXTE))
                parserNom(q, joueurs, TAG_MIXTE);
        }


        return setFinal;
    }

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
                if (j.getSexe().equals(sexe))
                    potentielsJoueurs.add(j);
            }
        }
        // Parsing du nom en UNE LIGNE
        q.setTexte(q.getTexte().replace(tag, potentielsJoueurs.get(new Random().nextInt(potentielsJoueurs.size())).getPseudo()));
    }
}
