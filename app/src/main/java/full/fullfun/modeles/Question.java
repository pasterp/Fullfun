package full.fullfun.modeles;

import java.util.HashMap;

public class Question {

    /***** Attributs *****/

    protected int id;

    protected String categorie;

    protected String texte;

    /***** Constructeurs *****/

    public Question(){

    }

    public Question(int id, String categorie, String texte){
        this();
        this.id = id;
        this.categorie = categorie;
        this.texte = texte;
    }

    /***** Accesseurs *****/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    /***** Methodes *****/

    public HashMap<String, String> exporterHashmap(){
        HashMap<String, String> donnees = new HashMap<>();
        donnees.put("categorie", categorie);
        donnees.put("texte", texte);
        return donnees;
    }

}
