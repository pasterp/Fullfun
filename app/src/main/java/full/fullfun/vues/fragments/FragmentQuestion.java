package full.fullfun.vues.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import full.fullfun.R;


public class FragmentQuestion extends Fragment {

    /***** Attributs *****/

    private TextView texteQuestion;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup vue = (ViewGroup) inflater.inflate(R.layout.fragment_question, container, false);
        texteQuestion = (TextView) vue.findViewById(R.id.texteQuestion);
        texteQuestion.setText(getArguments().getString("texteQuestion"));
        return vue;
    }
}
