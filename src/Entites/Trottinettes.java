package Entites;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

public class Trottinettes {
    private int id;
    private int etatBatterie;
    private boolean disponibilite;
    private double longitude;
    private double latitude;
    private Date dernierCheck;

    public Trottinettes(int id, int etatBatterie, boolean disponibilite, double longitude, double latitude,
            Date dernierCheck) {
        this.id = id;
        this.etatBatterie = etatBatterie;
        this.disponibilite = disponibilite;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dernierCheck = dernierCheck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtatBatterie() {
        return etatBatterie;
    }

    public void setEtatBatterie(int etatBatterie) {
        this.etatBatterie = etatBatterie;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Date getDernierCheck() {
        return dernierCheck;
    }

    public void setDernierCheck(Date dernierCheck) {
        this.dernierCheck = dernierCheck;
    }

    public Document toDocument() {
        return new Document("_id", id)
                .append("EtatBatterie", etatBatterie)
                .append("Disponibilite", disponibilite)
                .append("Localisation", new Document("longitude", longitude).append("latitude", latitude))
                .append("DernierCheck", dernierCheck);
    }

    // Méthode pour convertir une liste d'objets Trottinettes en une liste de
    // documents MongoDB
    public static List<Document> toDocumentList(List<Trottinettes> trottinettesList) {
        return trottinettesList.stream()
                .map(Trottinettes::toDocument)
                .collect(Collectors.toList());
    }
}
