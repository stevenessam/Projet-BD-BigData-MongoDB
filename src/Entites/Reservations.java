package Entites;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

public class Reservations {

    private int _id;
    private int clientID;
    private int trottinetteID;
    private Date dateHeureDebut;
    private Date dateHeureFin;
    private double tarif;

    // Constructeur
    public Reservations(int _id, int clientID, int trottinetteID, Date dateHeureDebut, Date dateHeureFin,
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

    public Date getDateHeureDebut() {
        return dateHeureDebut;
    }

    public void setDateHeureDebut(Date dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    public Date getDateHeureFin() {
        return dateHeureFin;
    }

    public void setDateHeureFin(Date dateHeureFin) {
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

    // Méthode pour convertir une liste d'objets Reservations en une liste de
    // documents MongoDB
    public static List<Document> toDocumentList(List<Reservations> reservationsList) {
        return reservationsList.stream()
                .map(Reservations::toDocument)
                .collect(Collectors.toList());
    }

}
