package full.fullfun.vues.fragments;

import android.content.Intent;
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

    private static final int PRENDRE_PHOTO = 1;



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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        new ToastCustom(getActivity(), 1);
    }
}
