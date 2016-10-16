package full.fullfun.modeles;


/**
 * Classe permettant de gérer les questions ajoutant un état à un joueur.
 * Les QuestionEtats sont en réalité constituées de 2 questions: une pour l'attribution de l'état
 * au joueur affichée en première et une autre pour désactiver l'état, affiché <code>duree</code>
 * tours après la 1ère.
 * @see Question
 */
public class QuestionEtat extends Question {

    /***** Attributs *****/

    /**
     * Nom de l'état attribué au joueur.
     */
    protected String etat;

    /**
     * Durée d'attribution de l'état.
     */
    protected int duree;

    /**
     * Question affichée à la fin de l'état.
     */
    protected Question questionFinEtat;

    /***** Constructeurs *****/

    /**
     * Constructeur par défaut. Appelle le constructeur par défaut de la classe Question.
     */
    public QuestionEtat(){
        super();
    }

    /**
     * Constructeur complet de la classe. Appelle le constructeur complet de la classe Question.
     * @param id Id de la question.
     * @param categorie Catégorie de la question.
     * @param texte Texte de la question.
     * @param etat No mde l'état attribué.
     */
    public QuestionEtat(int id, String categorie, String texte, String etat){
        super(id, categorie, texte);
        this.etat = etat;
    }


    /***** Accesseurs *****/

    /**
     * Setter du nom de l'état.
     * @param etat Nouveau nom de l'état.
     */
    public void setEtat(String etat){
        this.etat = etat;
    }

    /**
     * Getter du nom de l'état.
     * @return Nom de l'état.
     */
    public String getEtat() {
        return etat;
    }

    /**
     * Getter de la durée d'attribution.
     * @return Durée de l'état.
     */
    public int getDuree() {
        return duree;
    }

    /**
     * Setter de la durée d'attribution de l'état.
     * @param duree Nouvelle durée de l'état.
     */
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * Getter de la question de fin d'état.
     * @return Question de fin d'état.
     * @see Question
     */
    public Question getQuestionFinEtat() {
        return questionFinEtat;
    }

    /**
     * Setter de la question de fin d'état.
     * @param questionFinEtat Nouvelle question de fin d'état.
     * @see Question
     */
    public void setQuestionFinEtat(Question questionFinEtat) {
        this.questionFinEtat = questionFinEtat;
    }

    /***** Methodes *****/
}
