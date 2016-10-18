package full.fullfun.modeles;

public enum Sexe{
    Homme,
    Femme,
    Confus;



    /**
     * Permet d'obtenir une retranscription littérale du sexe.
     * @return Le String associé à chaque sexe.
     */
    public String toString(){
        switch (this){
            case Homme:
                return "Homme";
            case Femme:
                return "Femme";
            case Confus:
                return "Confus";
            default:
                return "Confus";
        }
    }

    public static Sexe factory(String sexe){
        switch (sexe){
            case "Homme":
                return Sexe.Homme;
            case "Femme":
                return Sexe.Femme;
            case "Confus":
            default:
                return Sexe.Confus;
        }
    }
}