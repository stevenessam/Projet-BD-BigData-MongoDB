package CRUDFiles;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DAO.Transactions;

import java.util.Date;

import org.bson.Document;

public class TransactionsCRUD {

    public static void insertTransaction(MongoDatabase database, Transactions transaction) {
        MongoCollection<Document> collection = getTransactionsCollection(database);

        // Convertir l'objet Transactions en Document MongoDB
        Document transactionDocument = transaction.toDocument();

        // Insérer le document dans la collection Transactions
        collection.insertOne(transactionDocument);

        System.out.println("Transaction inserted successfully!");
    }

    public static void readTransaction(MongoDatabase database, int transactionId) {
        MongoCollection<Document> collection = getTransactionsCollection(database);

        // Rechercher la transaction par ID
        Document query = new Document("_id", transactionId);
        FindIterable<Document> result = collection.find(query);

        // Afficher les détails de la transaction
        System.out.println("Transaction details:");
        for (Document document : result) {
            System.out.println("Transaction ID: " + document.get("_id"));
            System.out.println("Client ID: " + document.get("ClientID"));
            System.out.println("Montant: " + document.get("Montant"));
            System.out.println("Date de transaction: " + document.get("DateTransaction"));
            System.out.println("Type de transaction: " + document.get("TypeTransaction"));
        }
    }

    public static void updateTransaction(MongoDatabase database, int transactionId, double newMontant,
            Date newDateTransaction, String newTypeTransaction) {
        MongoCollection<Document> collection = getTransactionsCollection(database);

        // Construire la mise à jour
        Document updateQuery = new Document("_id", transactionId);
        Document updateValues = new Document("$set",
                new Document("Montant", newMontant)
                        .append("DateTransaction", newDateTransaction)
                        .append("TypeTransaction", newTypeTransaction));

        // Effectuer la mise à jour
        collection.updateOne(updateQuery, updateValues);

        System.out.println("Transaction updated successfully.");
    }

    public static void deleteTransaction(MongoDatabase database, int transactionID) {
        try {
            // Récupérer la collection Transactions
            MongoCollection<Document> collection = database.getCollection("Transactions");

            // Créer un filtre pour trouver la transaction par son ID
            Document filter = new Document("_id", transactionID);

            // Supprimer la transaction
            collection.deleteOne(filter);

            System.out.println("Transaction supprimée avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la transaction : " + e.getMessage());
        }
    }

    private static MongoCollection<Document> getTransactionsCollection(MongoDatabase database) {
        return database.getCollection("Transactions");
    }
}
