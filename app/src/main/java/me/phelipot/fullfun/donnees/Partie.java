package me.phelipot.fullfun.donnees;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.phelipot.fullfun.modeles.Question;
import me.phelipot.fullfun.modeles.SetQuestions;

/**
 * Created by pascal on 16-10-04.
 */

public class Partie {
    private List<Question> questions;
    private List<Question> questionsArchivees;

    public Partie(){
        questions = new ArrayList<>();
        questionsArchivees = new ArrayList<>();
    }

    public void ajouterSet(SetQuestions set){
        Log.d("Partie", "Ajout du set "+set.getNom()+" crée par "+set.getCreateur());
        for (Question q : set.getListeQuestions()) {
            questions.add(q);
        }
    }

    public void melangerQuestion(){
        Collections.shuffle(questions);
    }

    public Question getQuestionSuivante(){
        Question q = questions.get(0);

        questionsArchivees.add(questions.remove(0));

        return q;
    }

    public boolean hasQuestions(){
        return questions.size() > 0;
    }
}
