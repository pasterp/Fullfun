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
import full.fullfun.vues.MainActivity;

public class CardContentAdapter extends RecyclerView.Adapter<CardContentAdapter.ViewHolder> {

    /***** Varibales ******/
    private Context mContext;

    List<SetQuestions> listeSetQuestions;
    private MainActivity mainActivity;

    /***** Liste des sets selecitonne******/
    public List<SetQuestions> setSelect = new ArrayList<>();



    /***** fonction qui recupere les elements graphiques******/
    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView texteNom;
        protected TextView texteDifficulte;
        protected TextView texteCreateur;
        protected TextView texteDate;
        protected TextView texteDuree;

        public ViewHolder(View vue) {
            super(vue);
            texteNom =  (TextView) vue.findViewById(R.id.card_Nom);
            texteDifficulte =  (TextView) vue.findViewById(R.id.card_diff);
            texteCreateur =  (TextView) vue.findViewById(R.id.card_createur);
            texteDate =  (TextView) vue.findViewById(R.id.card_date);
            texteDuree=  (TextView) vue.findViewById(R.id.card_duree);
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
        holder.texteNom.setText(setEnCours.getNom());
        holder.texteCreateur.setText(setEnCours.getCreateur());
        holder.texteDifficulte.setText(String.valueOf(setEnCours.getDifficulte()));
        holder.texteDate.setText(setEnCours.getDate());
        holder.texteDuree.setText(String.valueOf(setEnCours.getDuree()));
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
                mainActivity.verifierLancementPartie();
            }

        });

    }

    /***** Compte les elements de la liste ******/
    @Override
    public int getItemCount() {
        return listeSetQuestions.size();
    }

    public List<SetQuestions> getSetSelect(){
        return setSelect;
    }

    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
}