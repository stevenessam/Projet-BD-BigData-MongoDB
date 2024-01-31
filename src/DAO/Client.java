package DAO;

import org.bson.Document;

public class Client {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private double soldeCompte;
    private double longitude;
    private double latitude;

    // Constructeur
    public Client(int id, String nom, String prenom, String email, String telephone, double soldeCompte,
            double longitude, double latitude) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.soldeCompte = soldeCompte;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // Méthode pour convertir l'objet Client en document MongoDB
    public Document toDocument() {
        return new Document()
                .append("_id", id)
                .append("Nom", nom)
                .append("Prenom", prenom)
                .append("Email", email)
                .append("Telephone", telephone)
                .append("SoldeCompte", soldeCompte)
                .append("DerniereLocalisation", new Document("longitude", longitude).append("latitude", latitude));
    }

    // Getters et setters si nécessaire
}
