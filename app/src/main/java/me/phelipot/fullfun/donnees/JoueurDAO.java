package me.phelipot.fullfun.donnees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.phelipot.fullfun.modeles.Joueur;
import me.phelipot.fullfun.modeles.Sexe;

/**
 * Created by 1634836 on 03/10/2016.
 */

public class JoueurDAO {
    public List<Joueur> getListeJoueur() {
        return listeJoueur;
    }

    protected List<Joueur> listeJoueur;
    private static JoueurDAO instance = null;

    public static JoueurDAO getInstance(){
        if(null == instance){
            instance = new JoueurDAO();
        }
        return instance;
    }
    private JoueurDAO(){
        listeJoueur = new ArrayList<>();
        Joueur joueur;

        joueur = new Joueur(0 , "Quentin", Sexe.Homme);
        listeJoueur.add(joueur);

        joueur = new Joueur(1 , "Pascal", Sexe.Homme);
        listeJoueur.add(joueur);

        joueur = new Joueur(2 , "Jordan", Sexe.Homme);
        listeJoueur.add(joueur);

        joueur = new Joueur(3 , "Samir", Sexe.Homme);
        listeJoueur.add(joueur);

        joueur = new Joueur(3 , "Audrey", Sexe.Femme);
        listeJoueur.add(joueur);

        joueur = new Joueur(4 , "Nico", Sexe.Confus);
        listeJoueur.add(joueur);
    }

    public List<HashMap<String, String>> listerJoueurEnHasmap(){

        List<HashMap<String , String>> listeJoueurHashMap = new ArrayList<HashMap<String , String>>();

        for (Joueur j : this.getListeJoueur()){
            listeJoueurHashMap.add(j.exporterHashMap());
        }
        return listeJoueurHashMap;
    }
}
