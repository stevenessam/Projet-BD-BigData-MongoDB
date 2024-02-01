package CRUDFiles;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Entites.Client;

import org.bson.Document;

public class ClientCRUD {

    public static void insertClient(MongoDatabase database, Client client) {
        try {
            // Get the Clients collection
            MongoCollection<Document> clientsCollection = database.getCollection("Clients");

            // Insert the client document
            clientsCollection.insertOne(client.toDocument());

            System.out.println("Client inserted successfully.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void readClient(MongoDatabase database, int clientId) {
        MongoCollection<Document> clientsCollection = database.getCollection("Clients");

        // Rechercher le client par son ID
        Document client = clientsCollection.find(new Document("_id", clientId)).first();

        // Afficher les détails du client de manière lisible
        if (client != null) {
            System.out.println("Détails du client :");
            System.out.println("ID : " + client.getInteger("_id"));
            System.out.println("Nom : " + client.getString("Nom"));
            System.out.println("Prénom : " + client.getString("Prenom"));
            System.out.println("Email : " + client.getString("Email"));
            System.out.println("Téléphone : " + client.getString("Telephone"));
            System.out.println("Solde du compte : " + client.getDouble("SoldeCompte"));

            // Extraire les valeurs de longitude et latitude
            Document derniereLocalisation = client.get("DerniereLocalisation", Document.class);
            double longitude = derniereLocalisation.getDouble("longitude");
            double latitude = derniereLocalisation.getDouble("latitude");

            System.out.println("Dernière localisation :");
            System.out.println("  Longitude : " + longitude);
            System.out.println("  Latitude : " + latitude);
        } else {
            System.out.println("Client non trouvé avec l'ID : " + clientId);
        }
    }

    public static void updateClient(MongoDatabase database, int clientId, String nom, String prenom, String email,
            String telephone, double soldeCompte, double longitude, double latitude) {
        try {
            // Get the Clients collection
            MongoCollection<Document> clientsCollection = database.getCollection("Clients");

            // Create a filter for the client to update
            Document filter = new Document("_id", clientId);

            // Create an update document
            Document update = new Document("$set", new Document()
                    .append("Nom", nom)
                    .append("Prenom", prenom)
                    .append("Email", email)
                    .append("Telephone", telephone)
                    .append("SoldeCompte", soldeCompte)
                    .append("DerniereLocalisation", new Document()
                            .append("longitude", longitude)
                            .append("latitude", latitude)));

            // Update the client document
            clientsCollection.updateOne(filter, update);

            System.out.println("Client updated successfully.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void deleteClient(MongoDatabase database, int clientId) {
        // Supprimer le client
        MongoCollection<Document> clientsCollection = database.getCollection("Clients");
        clientsCollection.deleteOne(new Document("_id", clientId));

        // Supprimer les données associées (Avis, Reservations, Transactions, etc.)
        deleteAssociatedData(database, "Avis", "ClientID", clientId);
        deleteAssociatedData(database, "Reservations", "ClientID", clientId);
        deleteAssociatedData(database, "Transactions", "ClientID", clientId);
        // Ajoutez d'autres collections si nécessaire
    }

    private static void deleteAssociatedData(MongoDatabase database, String collectionName, String foreignKey,
            int clientId) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.deleteMany(new Document(foreignKey, clientId));
    }
}
