package full.fullfun.modeles;


import java.io.Serializable;

public class Joueur implements Serializable{


    /***** Attributs *****/

    /**
     * Id du joueur.
     */
    protected int id;

    /**
     * Pseudo du joueur. C'est ce pseudo qui est affiché lors du jeu dans les questions.
     * @see full.fullfun.donnees.GenerateurPartie
     * @see Question
     */
    protected String pseudo;

    /**
     * Sexe du joueur. Utilisé pour les questions ciblant un sexe particulier.
     * @see Sexe
     * @see full.fullfun.donnees.GenerateurPartie
     * @see Question
     */
    protected Sexe sexe;


    /***** Constructeurs *****/

    /**
     * Constructeur par défaut. Ne fait rien.
     */
    public Joueur(){
    }

    /**
     * Constructeur complet.
     * @param id Id du joueur.
     * @param pseudo Pseudonyme du joueur.
     * @param sexe Sexe du joueur.
     * @see Sexe
     */
    public Joueur(int id, String pseudo, Sexe sexe){
        this.id = id;
        this.pseudo = pseudo;
        this.sexe = sexe;
    }

    /***** Accesseurs *****/

    /**
     * Getter de l'id.
     * @return Id du joueur.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'id.
     * @param id Nouvel id à utiliser.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter du pseudo.
     * @return Le pseudo du joueur.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Setter du pseudo.
     * @param pseudo Nouveau pseudo à utiliser.
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Getter du sexe.
     * @return Le sexe du joueur.
     * @see Joueur
     */
    public Sexe getSexe() {
        return sexe;
    }

    /**
     * Setter du sexe utilisant directement l'enum.
     * @param sexe Sexe à utiliser.
     */
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    /**
     * Setter du sexe utilisant un String. Utilisée par le GestionnaireXML.
     * @param sexe String désignant le Sexe à utiliser.
     * @see full.fullfun.donnees.GestionnaireXML
     * @see Sexe
     */
    public void setSexe(String sexe){
        switch (sexe) {
            case "Homme":
                this.sexe = Sexe.Homme;
                break;
            case "Femme":
                this.sexe = Sexe.Femme;
                break;
            default:
                this.sexe = Sexe.Confus;
                break;
        }
    }

    /***** Methodes *****/

    /**
     * Vérifie l'égalité entre l'Object passé en paramètre et le joueur.
     * Compare l'id, le pseudo et et le sexe.
     * @param o L'Object à comparer
     * @return True si o est le même joueur.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Joueur))
            return false;
        Joueur j = (Joueur)o;
        return j.id == this.id && j.pseudo.equals(this.pseudo) && j.sexe.equals(this.sexe);
    }
}
