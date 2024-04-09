package Model;

public class Docteur {

    private final String nom;

    private final String prenom;

    private final String lieu;

    private final String specialite;

    public Docteur(String nom, String prenom, String lieu, String specialite) {
        this.nom = nom;
        this.prenom = prenom;
        this.lieu = lieu;
        this.specialite = specialite;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getLieu() {
        return lieu;
    }

    public String getSpecialite(){
        return specialite;
    }

    public String toString(){
        return nom + " " + prenom + " " + lieu + " " + specialite;
    }
}
