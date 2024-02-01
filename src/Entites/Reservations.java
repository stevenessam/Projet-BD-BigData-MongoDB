package Entites;

import org.bson.Document;

public class Reservations {

    private int _id;
    private int clientID;
    private int trottinetteID;
    private String dateHeureDebut;
    private String dateHeureFin;
    private double tarif;

    // Constructeur
    public Reservations(int _id, int clientID, int trottinetteID, String dateHeureDebut, String dateHeureFin,
            double tarif) {
        this._id = _id;
        this.clientID = clientID;
        this.trottinetteID = trottinetteID;
        this.dateHeureDebut = dateHeureDebut;
        this.dateHeureFin = dateHeureFin;
        this.tarif = tarif;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getTrottinetteID() {
        return trottinetteID;
    }

    public void setTrottinetteID(int trottinetteID) {
        this.trottinetteID = trottinetteID;
    }

    public String getDateHeureDebut() {
        return dateHeureDebut;
    }

    public void setDateHeureDebut(String dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    public String getDateHeureFin() {
        return dateHeureFin;
    }

    public void setDateHeureFin(String dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    // Méthode pour convertir l'objet Reservation en Document
    public Document toDocument() {
        return new Document("_id", _id)
                .append("ClientID", clientID)
                .append("TrottinetteID", trottinetteID)
                .append("DateHeureDebut", dateHeureDebut)
                .append("DateHeureFin", dateHeureFin)
                .append("Tarif", tarif);
    }

    // Autres getters et setters au besoin

}
