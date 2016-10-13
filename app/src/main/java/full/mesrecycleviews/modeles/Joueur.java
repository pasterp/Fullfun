package full.mesrecycleviews.modeles;


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
        switch (sexe) {
            case "homme":
                this.sexe = Sexe.Homme;
                break;
            case "femme":
                this.sexe = Sexe.Femme;
                break;
            default:
                this.sexe = Sexe.Confus;
                break;
        }
    }

    /***** Methodes *****/

    public HashMap<String, String> exporterHashMap(){
        HashMap<String, String> joueur = new HashMap<String, String>();
        joueur.put("pseudo", String.valueOf(this.pseudo));
        joueur.put("sexe", String.valueOf(this.sexe));
        return joueur;
    }
}
