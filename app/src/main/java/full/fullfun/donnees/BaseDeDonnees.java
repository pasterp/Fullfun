package full.fullfun.donnees;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import full.fullfun.modeles.Joueur;
import full.fullfun.modeles.Question;
import full.fullfun.modeles.SetQuestions;


public class BaseDeDonnees extends SQLiteOpenHelper {

    /***** Constantes *****/

    private final String TABLE_JOUEUR = "Joueur";

    private final String TABLE_QUESTION = "Question";

    private final String TABLE_SET = "Set";

    private final String REQ_CREATE_TABLE_QUESTION =
            "Create table Question (id INTEGER PRIMARY KEY, categorie TEXT, texte TEXT, idSet INTEGER)";

    private final String REQ_CREATE_TABLE_SET =
            "Create table Set (id INTEGER PRIMARY KEY, nom TEXT, createur TEXT, difficulte INTEGER, " +
                    "date TEXT, duree INTEGER, score INTEGER)";

    private final String REQ_CREATE_TABLE_JOUEUR =
            "Create table Joueur (id INTEGER PRIMARY KEY, pseudo TEXT, sexe TEXT)";

    private final String REQ_SUPPRIMER_TABLE_QUESTION =
            "Drop table Question";

    private final String REQ_SUPPRIMER_TABLE_SET =
            "Drop table Set";

    private final String REQ_SUPPRIMER_TABLE_JOUEUR =
            "Drop table Joueur";


    private final String REQ_SELECT_QUESTIONS =
            "Select * From Question";

    private final String REQ_SELECT_SETS =
            "Select * From Set";

    private final String REQ_SELECT_JOUEURS =
            "Select * From Joueur";

    /***** Attributs *****/

    private static BaseDeDonnees instance;

    /***** Constructeurs *****/

    private BaseDeDonnees(Context context) {
        super(context, "FullFun", null, 1);
    }


    public static BaseDeDonnees getInstance(Context context){
        instance = new BaseDeDonnees(context);
        return instance;
    }

    public static BaseDeDonnees getInstance(){
        return instance;
    }

    /***** Accesseurs *****/


    /***** Méthodes *****/

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQ_CREATE_TABLE_QUESTION);
        db.execSQL(REQ_CREATE_TABLE_SET);
        db.execSQL(REQ_CREATE_TABLE_JOUEUR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(REQ_SUPPRIMER_TABLE_JOUEUR);
        db.execSQL(REQ_SUPPRIMER_TABLE_SET);
        db.execSQL(REQ_SUPPRIMER_TABLE_QUESTION);
        onCreate(db);
    }


    /**
     * Ajoute le joueur passé en paramètre à la BDD.
     * @param joueur Le Joueur à ajouter.
     * @see Joueur
     */
    public void ajouterJoueur(Joueur joueur){
        ContentValues valeurs = new ContentValues();
        valeurs.put("id", joueur.getId());
        valeurs.put("pseudo", joueur.getPseudo());
        valeurs.put("sexe", joueur.getSexe().toString());
        getWritableDatabase().insertWithOnConflict(TABLE_JOUEUR, null, valeurs, SQLiteDatabase.CONFLICT_IGNORE);
    }

    /**
     * Ajoute le SetQuestion à la BDD ainsi que toutes ses questions.
     * @param setQuestions Le SetQuestion à ajouter
     * @see SetQuestions
     */
    public void ajouterSet(SetQuestions setQuestions){
        ContentValues valeurs = new ContentValues();
        valeurs.put("id", setQuestions.getId());
        valeurs.put("nom", setQuestions.getNom());
        valeurs.put("createur", setQuestions.getCreateur());
        valeurs.put("difficulte", setQuestions.getDifficulte());
        valeurs.put("date", setQuestions.getDate());
        valeurs.put("duree", setQuestions.getDuree());
        valeurs.put("score", setQuestions.getScore());
        getWritableDatabase().insertWithOnConflict(TABLE_SET, null, valeurs, SQLiteDatabase.CONFLICT_IGNORE);

        // Ajout des questions du set
        int idSet = setQuestions.getId();
        for (Question q : setQuestions.getListeQuestions()){
            valeurs = new ContentValues();
            valeurs.put("id", q.getId());
            valeurs.put("categorie", q.getCategorie());
            valeurs.put("texte", q.getTexte());
            valeurs.put("idSet", idSet);
            getWritableDatabase().insertWithOnConflict(TABLE_QUESTION, null, valeurs, SQLiteDatabase.CONFLICT_IGNORE);
        }
    }

    /**
     * Méthodeu tilisée pour nettoyer la base après une partie.
     */
    public void viderBase(){
        onUpgrade(getWritableDatabase(), 1, 1);
    }
}
