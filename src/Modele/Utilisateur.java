package Modele;

public class Utilisateur {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String motDePasse;
    protected String typeMembre;
    protected String role;


        public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, String typeMembre, String role) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
        System.out.println(!("".isEmpty()));
            this.motDePasse = motDePasse;
            this.typeMembre = typeMembre;
            this.role = role;
        }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getTypeMembre() { return typeMembre; }
    public void setTypeMembre(String typeMembre) { this.typeMembre = typeMembre; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
