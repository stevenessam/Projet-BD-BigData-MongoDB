package CRUDFiles;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Entites.Transactions;

import java.util.Date;
import java.util.List;

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

    // -----------------------------------------Fonction-MANY-------------------------------------------------------
    // Insert many transactions
    public static void insertManyTransactions(MongoDatabase database, List<Transactions> transactionsList) {
        try {
            // Obtenez la collection Transactions
            MongoCollection<Document> transactionsCollection = getTransactionsCollection(database);

            // Convertissez la liste d'objets Transactions en une liste de documents MongoDB
            List<Document> transactionDocuments = Transactions.toDocumentList(transactionsList);

            // Insérez les documents dans la collection
            transactionsCollection.insertMany(transactionDocuments);

            System.out.println("Multiple transactions inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Read all transactions
    public static void readAllTransactions(MongoDatabase database) {
        try {
            // Obtenez la collection Transactions
            MongoCollection<Document> transactionsCollection = getTransactionsCollection(database);

            // Récupérez tous les documents de la collection
            FindIterable<Document> result = transactionsCollection.find();

            // Affichez les détails de chaque transaction
            System.out.println("Liste des transactions :");
            for (Document transaction : result) {
                System.out.println("Transaction ID: " + transaction.getInteger("_id"));
                System.out.println("Client ID: " + transaction.getInteger("ClientID"));
                System.out.println("Montant: " + transaction.getDouble("Montant"));
                System.out.println("Date de transaction: " + transaction.getDate("DateTransaction"));
                System.out.println("Type de transaction: " + transaction.getString("TypeTransaction"));
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Update many transactions
    public static void updateManyTransactions(MongoDatabase database, List<Transactions> transactionsList) {
        try {
            // Obtenez la collection Transactions
            MongoCollection<Document> transactionsCollection = getTransactionsCollection(database);

            for (Transactions transaction : transactionsList) {
                // Convertir l'objet Transactions en Document MongoDB
                Document transactionDocument = transaction.toDocument();

                // Extraire l'ID de la transaction
                int transactionID = transaction.getTransactionID();

                // Créer le filtre pour trouver le document correspondant à l'ID
                Document filter = new Document("_id", transactionID);

                // Effectuer la mise à jour du document
                transactionsCollection.replaceOne(filter, transactionDocument);
            }

            System.out.println("Multiple transactions updated successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Supprimer plusieurs transactions
    public static void deleteManyTransactions(MongoDatabase database, List<Integer> transactionIds) {
        try {
            // Obtenez la collection Transactions
            MongoCollection<Document> transactionsCollection = database.getCollection("Transactions");

            // Créer le filtre pour trouver les documents correspondant aux IDs de
            // transactions
            Document filter = new Document("_id", new Document("$in", transactionIds));

            // Supprimer tous les documents correspondant aux IDs de transactions
            transactionsCollection.deleteMany(filter);

            System.out.println("Transactions supprimées avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression des transactions : " + e.getMessage());
        }
    }

}