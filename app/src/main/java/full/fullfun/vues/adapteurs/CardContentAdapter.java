package full.fullfun.vues.adapteurs;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import full.fullfun.R;
import full.fullfun.modeles.SetQuestions;

public class CardContentAdapter extends RecyclerView.Adapter<CardContentAdapter.ViewHolder> {

    /***** Varibales ******/
    private Context mContext;
    List<SetQuestions> listeSetQuestions;

    /***** Liste des sets selecitonne******/
    public List<SetQuestions> setSelect = new ArrayList<>();



    /***** fonction qui recupere les elements graphiques******/
    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView textNom;

        public ViewHolder(View vue) {
            super(vue);
            textNom =  (TextView) vue.findViewById(R.id.card_Nom);
        }
    }

    /***** Constructeur ******/
    public CardContentAdapter(Context mContext, List<SetQuestions> listeSetQuestions) {
        this.mContext = mContext;
        this.listeSetQuestions = listeSetQuestions;
    }

    /***** Instantie le layout xml dans le vue ******/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemVue = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ViewHolder(itemVue);
    }

    /***** Association des elements de la liste avec les elements graphique ******/
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final SetQuestions setEnCours = listeSetQuestions.get(position);
        holder.textNom.setText(setEnCours.getNom());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetQuestions setEnCours = listeSetQuestions.get(position);
                if(setSelect.contains(setEnCours)){
                    setSelect.remove(setEnCours);
                    v.setBackgroundColor(Color.WHITE);
                }else{
                    setSelect.add(setEnCours);
                    v.setBackgroundColor(Color.GREEN);
                }
            }

        });

    }

    /***** Compte les elements de la liste ******/
    @Override
    public int getItemCount() {
        return listeSetQuestions.size();
    }


}