package full.fullfun.vues.adapteurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import full.fullfun.modeles.Question;
import full.fullfun.vues.fragments.FragmentQuestion;


public class ListeQuestionsAdapteur extends FragmentStatePagerAdapter {


    /***** Attrbuts *****/

    List<Question> listeQuestions;

    Question questionActuel;

    /***** Constructeurs *****/

    public ListeQuestionsAdapteur(FragmentManager fm, List<Question> questions) {
        super(fm);
        listeQuestions = new ArrayList<>();
        listeQuestions.addAll(questions);
    }


    /***** Méthodes *****/

    @Override
    public Fragment getItem(int position) {
        if (position >= 0 && position < listeQuestions.size()){
            questionActuel = listeQuestions.get(position);
            FragmentQuestion frag = new FragmentQuestion();
            Bundle arg = new Bundle();
            arg.putString("texteQuestion", questionActuel.getTexte());
            frag.setArguments(arg);
            return frag;
        }
        return null;
    }

    @Override
    public int getCount() {
        return listeQuestions.size();
    }

}
