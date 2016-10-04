package full.fullfun.modeles;


import java.util.HashMap;

public class Joueur {


    /***** Attributs *****/

    protected int id;

    protected String pseudo;

    protected Sexe sexe;



    /***** Constructeurs *****/

    public Joueur(){


    }

    public Joueur(int id, String pseudo, Sexe sexe){
        this.id = id;
        this.pseudo = pseudo;
        this.sexe = sexe;
    }

    /***** Accesseurs *****/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public void setSexe(String sexe){
        if (sexe.equals("homme"))
            this.sexe = Sexe.Homme;
        else if(sexe.equals("femme"))
            this.sexe = Sexe.Femme;
        else
            this.sexe = Sexe.Confus;
    }

    /***** Methodes *****/

    public HashMap<String, String> exporterHashMap(){
        HashMap<String, String> joueur = new HashMap<String, String>();
        joueur.put("pseudo", String.valueOf(this.pseudo));
        joueur.put("sexe", String.valueOf(this.sexe));
        return joueur;
    }
}
