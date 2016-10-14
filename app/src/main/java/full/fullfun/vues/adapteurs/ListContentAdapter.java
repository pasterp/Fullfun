package full.fullfun.vues.adapteurs;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import full.fullfun.R;
import full.fullfun.modeles.Joueur;
import full.fullfun.modeles.Sexe;
import full.fullfun.vues.MainActivity;

/**
 * Created by 1634836 on 04/10/2016.
 */

public class ListContentAdapter extends RecyclerView.Adapter<ListContentAdapter.ViewHolder> {

    /***** Attributs ******/

    private Context mContext;

    private List<Joueur> listeJoueur;

    private MainActivity mainActivity;

    /***** Liste des joueurs selecitonne******/
    public List<Joueur> joueursSelect = new ArrayList<>();

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /***** fonction qui recupere les elements graphiques******/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private SparseBooleanArray selectedItems = new SparseBooleanArray();
        protected TextView textNom;
        protected ImageView imgSexe;

        public ViewHolder(View vue) {
            super(vue);
            textNom =  (TextView) vue.findViewById(R.id.listNom);
            imgSexe = (ImageView) vue.findViewById(R.id.listSexImg);
            //textNom.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //action au clic qu'un element !
        }
    }

    /***** Constructeur ******/
    public ListContentAdapter(Context mContext, List<Joueur> listeJoueur) {
        this.mContext = mContext;
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
            holder.imgSexe.setImageResource(R.drawable.boy);
        }else if(joueurEnCours.getSexe().equals(Sexe.Femme)){
            holder.imgSexe.setImageResource(R.drawable.girl);
        }else{
            holder.imgSexe.setImageResource(R.drawable.girl);
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
}