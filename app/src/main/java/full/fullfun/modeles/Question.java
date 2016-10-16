package full.fullfun.modeles;

public class Question {

    /***** Attributs *****/

    /**
     * Id de la question.
     */
    protected int id;

    /**
     * La catégorie de la question. Si la catégorie n'est pas <i>normale</i> alors l'objet instancié
     * devrait être d'une classe fille à celle-ci.
     */
    protected String categorie;

    /**
     * Le texte affiché lors du jeu.
     */
    protected String texte;

    /***** Constructeurs *****/

    /**
     * Constructeur par défaut. Ne fait rien.
     */
    public Question(){

    }

    /**
     * Constructeur complet.
     * @param id Id de la question.
     * @param categorie Catégorie de la question.
     * @param texte Texte à afficher de la question.
     */
    public Question(int id, String categorie, String texte){
        this();
        this.id = id;
        this.categorie = categorie;
        this.texte = texte;
    }

    /***** Accesseurs *****/

    /**
     * Getter de l'id.
     * @return L'id de la question.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de la question.
     * @param id Nouvel id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter de la catégorie.
     * @return la catégorie de la question.
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Setter de la catégorie.
     * @param categorie Nouvelle catégorie de la question.
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * getter du texte.
     * @return Le texte de la question.
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Setter du texte de la question.
     * @param texte Nouveau texte de la question.
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /***** Methodes *****/

    /**
     * Vérifie l'égalité entre l'Object passé en paramètre et cette question.
     * Compare l'id, la catégorie et le texte.
     * @param o L'objet à comparer.
     * @return True si o est la même Question.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Question))
            return false;
        Question q = (Question)o;
        return q.id == this.id && q.categorie.equals(this.categorie) && q.texte.equals(this.texte);
    }
}
