package Entites;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

public class Transactions {

    private int transactionID;
    private int clientID;
    private double montant;
    private Date dateTransaction;
    private String typeTransaction;

    // Constructeur
    public Transactions(int transactionID, int clientID, double montant, Date dateTransaction, String typeTransaction) {
        this.transactionID = transactionID;
        this.clientID = clientID;
        this.montant = montant;
        this.dateTransaction = dateTransaction;
        this.typeTransaction = typeTransaction;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    // Méthode pour convertir l'objet Transactions en Document MongoDB
    public Document toDocument() {
        return new Document("_id", transactionID)
                .append("ClientID", clientID)
                .append("Montant", montant)
                .append("DateTransaction", dateTransaction)
                .append("TypeTransaction", typeTransaction);
    }

    // Méthode pour convertir une liste d'objets Transactions en une liste de
    // documents MongoDB
    public static List<Document> toDocumentList(List<Transactions> transactionsList) {
        return transactionsList.stream()
                .map(Transactions::toDocument)
                .collect(Collectors.toList());
    }
}
