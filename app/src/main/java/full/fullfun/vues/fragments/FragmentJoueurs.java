package full.fullfun.vues.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import full.fullfun.donnees.JoueurDAO;
import full.fullfun.modeles.GroupeJoueur;
import full.fullfun.modeles.Joueur;
import full.fullfun.vues.MainActivity;
import full.fullfun.vues.adapteurs.ListContentAdapter;
import full.fullfun.R;


public class FragmentJoueurs extends Fragment {

    /***** Variable *****/

    /***** Adapteur *****/

    private RecyclerView mRecyclerView;

    private ListContentAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private MainActivity mainActivity;

    /***** Acces aux joueurs *****/
    private JoueurDAO accesseurDAO = JoueurDAO.getInstance();

    private List<Joueur> listeJoueurs;

    /***** Instantie le recyclerView xml dans le vue ******/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View vue = inflater.inflate(R.layout.recycler_view, container, false);
        initView(vue);
        initRecyclerView();
        mAdapter.setMainActivity(mainActivity);
        return vue;
    }


    /***** Recuperation du recycleView *****/
    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
    }

    /***** Instanciation des items et du layout*****/
    private void initRecyclerView() {
        listeJoueurs = accesseurDAO.getListeJoueurs();
        mAdapter = new ListContentAdapter(getActivity().getApplicationContext(), listeJoueurs);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        mRecyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public List<Joueur> getJoueursSelect(){
        return mAdapter.getJoueursSelect();
    }

    public void setMainActivity(MainActivity m){
        mainActivity = m;
    }

    public void rafraichir() {
        listeJoueurs = accesseurDAO.getListeJoueurs();
    }
}
