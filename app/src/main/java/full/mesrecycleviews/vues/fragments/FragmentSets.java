package full.mesrecycleviews.vues.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import full.mesrecycleviews.donnees.SetQuestionsDAO;
import full.mesrecycleviews.modeles.SetQuestions;
import full.mesrecycleviews.vues.adapteurs.CardContentAdapter;
import full.mesrecycleviews.R;


/**
 * Created by 1634836 on 04/10/2016.
 */

public class FragmentSets extends Fragment {

    /***** Variable *****/

    /***** Adapteur *****/
    private RecyclerView mRecyclerView;
    private CardContentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /***** Acces aux sets *****/
    private SetQuestionsDAO accesseurDAO = SetQuestionsDAO.getInstance();
    private List<SetQuestions> listeSetQuestions;

    /***** Instantie le recyclerView xml dans le vue ******/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View vue = inflater.inflate(R.layout.recycler_view, container, false);
        initView(vue);
        initRecyclerView();
        return vue;
    }

    /***** Recuperation du recycleView *****/
    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
    }

    /***** Instanciation des items et du layout*****/
    private void initRecyclerView() {
        accesseurDAO.initialisationQuestions(getResources().getAssets());
        listeSetQuestions = accesseurDAO.getListeSetQuestion();
        mAdapter = new CardContentAdapter(getActivity().getApplicationContext(),listeSetQuestions );
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        mRecyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

}
