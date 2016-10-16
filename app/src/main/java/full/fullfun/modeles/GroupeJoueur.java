package full.fullfun.modeles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Désactivée pour le moment.
 * Permettra à l'utilisateur de gérer les joueurs sauvegardés par groupe.
 * @see Joueur
 * @deprecated
 */
public class GroupeJoueur {


    /***** Attributs *****/

    /**
     * ArrayList des joueurs du groupe.
     * @see Joueur
     */
    protected List<Joueur> joueurs;

    /**
     * Id du groupe.
     */
    protected int id;

    /**
     * Nom du groupe.
     */
    protected String nom;

    /***** Constructeurs *****/

    /**
     * Constructeur par défaut de la classe. Initialise l'ArrayList.
     */
    public GroupeJoueur(){
        joueurs = new ArrayList<>();
    }

    /**
     * Constructeur complet de la classe.
     * @param id Id du groupe.
     * @param nom Nom du groupe.
     * @param joueurs ArrayList des joueurs à ajouter au groupe.
     */
    public GroupeJoueur(int id, String nom, List<Joueur> joueurs){
        this();
        this.id = id;
        this.nom = nom;
        this.joueurs.addAll(joueurs);
    }

    /***** Accesseurs *****/

    /**
     * Getter de l'id.
     * @return Id du groupe.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'id.
     * @param id Nouvel id du groupe.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter des joueurs.
     * @return ArrayList contenant les Joueurs du groupe.
     * @see Joueur
     */
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * Setter de la liste des joueurs.
     * @param joueurs ArrayList de Joueur remplaçant l'ancienne liste.
     */
    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * Getter du nom.
     * @return Nom du groupe.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom.
     * @param nom Nouveau nom du groupe.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /***** Methodes *****/

    /**
     * Ajoute le joueur passé en paramètre à la liste du groupe.
     * @param joueur Joueur à ajouter.
     * @see Joueur
     */
    public void ajouterJoueur(Joueur joueur){
        joueurs.add(joueur);
    }

    /**
     * Retire le 1er joueur trouvé ayant le même id que celui passé en paramètre.
     * @param id L'id du Joueur à supprimer.
     */
    public void retirerJoueur(int id){
        Iterator<Joueur> iterator = joueurs.iterator();
        while (iterator.hasNext()){
            // Si l'id correspond à celui du joueur en itération alors on le remove
            if (iterator.next().getId() == id){
                iterator.remove();
                // On break tout si on enlève un joueur.
                break;
            }
        }
    }

    /**
     * Méthode permettant de vérifier l'égalité de 2 groupes.
     * Compare l'id, le nom et tout les joueurs.
     * @param o L'Object à comparer.
     * @return True si l'objet passé en paramètre est égal à ce GroupeJoueur.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GroupeJoueur))
            return false;
        GroupeJoueur g = (GroupeJoueur)o;
        for (Joueur j : g.joueurs){
            if (!joueurs.contains(j))
                return false;
        }
        return g.id == this.id && g.nom.equals(this.nom);
    }
}
