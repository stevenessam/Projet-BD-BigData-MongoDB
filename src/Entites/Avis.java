package Entites;

import org.bson.Document;

public class Avis {

    private int _id;
    private int clientID;
    private int trottinetteID;
    private String messageAvis;

    public Avis(int _id, int clientID, int trottinetteID, String messageAvis) {
        this._id = _id;
        this.clientID = clientID;
        this.trottinetteID = trottinetteID;
        this.messageAvis = messageAvis;
    }

    public int get_id() {
        return _id;
    }

    public int getClientID() {
        return clientID;
    }

    public int getTrottinetteID() {
        return trottinetteID;
    }

    public String getMessageAvis() {
        return messageAvis;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setTrottinetteID(int trottinetteID) {
        this.trottinetteID = trottinetteID;
    }

    public void setMessageAvis(String messageAvis) {
        this.messageAvis = messageAvis;
    }

    // Convertir l'objet Avis en Document MongoDB
    public Document toDocument() {
        Document document = new Document("_id", _id)
                .append("ClientID", clientID)
                .append("TrottinetteID", trottinetteID)
                .append("MessageAvis", messageAvis);
        return document;
    }
}
