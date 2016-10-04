package full.fullfun.modeles;

public enum Sexe{
    Homme,
    Femme,
    Confus;

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