package full.fullfun.vues.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import full.fullfun.donnees.JoueurDAO;
import full.fullfun.modeles.GroupeJoueur;
import full.fullfun.modeles.Joueur;
import full.fullfun.vues.AjoutJoueur;
import full.fullfun.vues.MainActivity;
import full.fullfun.vues.adapteurs.CustomListClickEcouteur;
import full.fullfun.vues.adapteurs.ListContentAdapter;
import full.fullfun.R;
import full.fullfun.vues.adapteurs.ToastCustom;


public class FragmentJoueurs extends Fragment {

    /***** Variable *****/

    private BroadcastReceiver receiver;

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
        mAdapter = new ListContentAdapter(listeJoueurs);
        mAdapter.setMainActivity(mainActivity);
        mAdapter.setCustomListener(new CustomListClickEcouteur() {
            @Override
            public void onItemLongClick(View vueItem, final int position) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Gestion de " + listeJoueurs.get(position).getPseudo());
                builder.setMessage("Que voulez-vous faire ?");
                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final JoueurDAO dao = JoueurDAO.getInstance();
                        final Joueur suppression = listeJoueurs.get(position);
                        dao.supprimerJoueur(suppression);
                        try {
                            dao.sauvegarderJoueurs(mainActivity.openFileOutput("joueurs.xml", Context.MODE_PRIVATE));
                            mAdapter.notifyDataSetChanged();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Snackbar notifSuppression = Snackbar.make(
                                mainActivity.findViewById(R.id.viewPagerMain),
                                suppression.getPseudo() + " supprimé !",
                                Snackbar.LENGTH_LONG
                        );
                        notifSuppression.setAction(
                                "Annuler",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dao.ajouterJoueur(suppression);
                                        new ToastCustom(mainActivity, ToastCustom.ANNULER_SUPPRESSION);
                                        try {
                                            dao.sauvegarderJoueurs(mainActivity.openFileOutput("joueurs.xml", Context.MODE_PRIVATE));
                                            mAdapter.notifyDataSetChanged();
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        );
                        notifSuppression.show();
                    }
                });
                builder.setNegativeButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent ouvrirEditeurJoueur = new Intent(getContext(), AjoutJoueur.class);
                        ouvrirEditeurJoueur.putExtra("joueur", listeJoueurs.get(position));
                        startActivity(ouvrirEditeurJoueur);
                    }
                });
                builder.create().show();
            }
        });
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

    @Override
    public void onResume() {
        super.onResume();
        initView(getView());
        initRecyclerView();
    }

}
