package full.fullfun.vues.adapteurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import full.fullfun.modeles.Partie;
import full.fullfun.modeles.Question;
import full.fullfun.vues.VuePartie;
import full.fullfun.vues.fragments.FragmentQuestion;


public class ListeQuestionsAdapteur extends FragmentStatePagerAdapter {


    /***** Attrbuts *****/

    List<Question> listeQuestions;

    Question questionActuel;

    VuePartie vue;

    List<FragmentQuestion> fragmentQuestions;

    SparseBooleanArray fragmentsGrisement;

    /***** Constructeurs *****/

    public ListeQuestionsAdapteur(FragmentManager fm, Partie partie, VuePartie vue) {
        super(fm);
        this.listeQuestions = partie.getSetQuestions().getListeQuestions();
        this.vue = vue;
        fragmentQuestions = new ArrayList<>();
        fragmentsGrisement = new SparseBooleanArray();
    }


    /***** Méthodes *****/

    @Override
    public Fragment getItem(int position) {
        questionActuel = listeQuestions.get(position);
        FragmentQuestion frag = new FragmentQuestion();
        Bundle arg = new Bundle();
        arg.putString("texteQuestion", questionActuel.getTexte());
        frag.setArguments(arg);
        fragmentQuestions.add(frag);
        if (listeQuestions.size() == fragmentQuestions.size())
            vue.finPartie();

        return frag;
    }

    @Override
    public int getCount() {
        return listeQuestions.size();
    }

}
