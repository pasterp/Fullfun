package full.fullfun.vues.adapteurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import full.fullfun.modeles.Partie;
import full.fullfun.modeles.Question;
import full.fullfun.vues.VuePartie;
import full.fullfun.vues.fragments.FragmentQuestion;


public class ListeQuestionsAdapteur extends FragmentStatePagerAdapter {


    /***** Attrbuts *****/

    Partie partie;

    Question questionActuel;

    VuePartie vue;

    /***** Constructeurs *****/

    public ListeQuestionsAdapteur(FragmentManager fm, Partie partie, VuePartie vue) {
        super(fm);
        this.partie = partie;
        this.vue = vue;
    }


    /***** Méthodes *****/

    @Override
    public Fragment getItem(int position) {
        if (partie.hasQuestions()){
            questionActuel = partie.getQuestionSuivante();
            FragmentQuestion frag = new FragmentQuestion();
            Bundle arg = new Bundle();
            arg.putString("texteQuestion", questionActuel.getTexte());
            frag.setArguments(arg);
            if (partie.isBeforeLast())
                vue.finPartie();
            return frag;
        }

        return null;
    }

    @Override
    public int getCount() {
        return partie.getTaille();
    }

}
