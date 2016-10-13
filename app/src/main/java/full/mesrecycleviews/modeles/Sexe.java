package full.mesrecycleviews.modeles;

public enum Sexe{
    Homme,
    Femme,
    Confus, Sexe;

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



}