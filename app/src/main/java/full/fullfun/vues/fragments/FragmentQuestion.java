package full.fullfun.vues.fragments;

<<<<<<< HEAD
import android.content.Intent;
=======
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
>>>>>>> fb41ccfd16c02c4cb4e0991e02ea3ffe37ae8d57
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import full.fullfun.Externe.GestionPhoto;
import full.fullfun.R;
import full.fullfun.vues.adapteurs.ToastCustom;


public class FragmentQuestion extends Fragment{

    /***** Attributs *****/

    private TextView texteQuestion;
    private ImageView image;

<<<<<<< HEAD
    private static final int PRENDRE_PHOTO = 1;



=======
>>>>>>> fb41ccfd16c02c4cb4e0991e02ea3ffe37ae8d57
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup vue = (ViewGroup) inflater.inflate(R.layout.fragment_question, container, false);
        texteQuestion = (TextView) vue.findViewById(R.id.texteQuestion);
        texteQuestion.setText(getArguments().getString("texteQuestion"));
        image = (ImageView) vue.findViewById(R.id.image);


        vue.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent actionPrendrePhoto = new Intent(view.getContext(), GestionPhoto.class);

                startActivityForResult(actionPrendrePhoto,PRENDRE_PHOTO);
                return true;
            }
        });

        return vue;
    }

<<<<<<< HEAD
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        new ToastCustom(getActivity(), 1);
=======

    public void griser() {
        if (texteQuestion != null)
            texteQuestion.setBackgroundColor(Color.DKGRAY);
>>>>>>> fb41ccfd16c02c4cb4e0991e02ea3ffe37ae8d57
    }
}
