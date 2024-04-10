package Model;

public class Docteur {

    private final String nom;

    private final String prenom;

    private final String lieu;

    private final String specialite;

    private final String email;

    public Docteur(String nom, String prenom,String specialite,  String email, String lieu) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.email = email;
        this.lieu = lieu;
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

    public String getEmail() {
        return email;
    }

    public String toString(){
        return nom + " " + prenom + " " + lieu + " " + specialite;
    }
}
