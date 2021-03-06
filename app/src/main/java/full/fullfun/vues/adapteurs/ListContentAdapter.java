package full.fullfun.vues.adapteurs;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import full.fullfun.R;
import full.fullfun.donnees.JoueurDAO;
import full.fullfun.modeles.Joueur;
import full.fullfun.modeles.Sexe;
import full.fullfun.vues.MainActivity;

/**
 * Created by 1634836 on 04/10/2016.
 */

public class ListContentAdapter extends RecyclerView.Adapter<ListContentAdapter.ViewHolder> {

    /***** Attributs ******/


    private List<Joueur> listeJoueur;

    private MainActivity mainActivity;

    private CustomListClickEcouteur ecouteur;

    public  void setCustomListener(CustomListClickEcouteur ecouteur){
        this.ecouteur = ecouteur;
    }

    /***** Liste des joueurs selecitonne******/
    public List<Joueur> joueursSelect = new ArrayList<>();

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /***** Constructeur ******/
    public ListContentAdapter(List<Joueur> listeJoueur) {
        this.listeJoueur = listeJoueur;
    }

    /***** Instantie le layout xml dans le vue ******/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemVue = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new ViewHolder(itemVue);

    }
    /***** Association des elements de la liste avec les elements graphique ******/
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Joueur joueurEnCours = listeJoueur.get(position);
        holder.textNom.setText(joueurEnCours.getPseudo());
        if(joueurEnCours.getSexe().equals(Sexe.Homme)){
            holder.imgSexe.setImageResource(R.drawable.male);
        }else if(joueurEnCours.getSexe().equals(Sexe.Femme)){
            holder.imgSexe.setImageResource(R.drawable.femme);
        }else{
            holder.imgSexe.setImageResource(R.drawable.trans);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joueur joueurEnCours = listeJoueur.get(position);
                //Toast.makeText(mContext,"Joueurs en cours : "+joueurEnCours.getPseudo(),Toast.LENGTH_SHORT).show();
                if(joueursSelect.contains(joueurEnCours)){
                    joueursSelect.remove(joueurEnCours);
                    v.setBackgroundColor(Color.WHITE);
                }else{
                    joueursSelect.add(joueurEnCours);
                    v.setBackgroundColor(Color.GREEN);
                }
                mainActivity.verifierLancementPartie();
            }
        });
    }

    /***** Compte les elements de la liste ******/
    @Override
    public int getItemCount() {
        return listeJoueur.size();
    }

    public List<Joueur> getJoueursSelect(){
        return joueursSelect;
    }




    /***** Classe qui recupere les elements graphiques******/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private SparseBooleanArray selectedItems = new SparseBooleanArray();
        protected TextView textNom;
        protected ImageView imgSexe;

        public ViewHolder(View vue) {
            super(vue);
            textNom =  (TextView) vue.findViewById(R.id.listNom);
            imgSexe = (ImageView) vue.findViewById(R.id.listSexImg);
            vue.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (ecouteur != null) {
                        ecouteur.onItemLongClick(v, getLayoutPosition());
                    }
                    return true;
                }
            });
        }

        @Override
        public void onClick(View view) {
            //action au clic qu'un element !
        }


    }
}