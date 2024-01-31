package DAO;

import org.bson.Document;

public class Trottinettes {
    private int id;
    private int etatBatterie;
    private boolean disponibilite;
    private double longitude;
    private double latitude;
    private String dernierCheck;

    public Trottinettes(int id, int etatBatterie, boolean disponibilite, double longitude, double latitude,
            String dernierCheck) {
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

    public String getDernierCheck() {
        return dernierCheck;
    }

    public void setDernierCheck(String dernierCheck) {
        this.dernierCheck = dernierCheck;
    }

    public Document toDocument() {
        return new Document("_id", id)
                .append("EtatBatterie", etatBatterie)
                .append("Disponibilite", disponibilite)
                .append("Localisation", new Document("longitude", longitude).append("latitude", latitude))
                .append("DernierCheck", dernierCheck);
    }

    // Ajoutez des getters et des setters si nécessaire
}
