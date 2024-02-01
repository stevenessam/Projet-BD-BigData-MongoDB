package CRUDFiles;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import Entites.Client;

import java.util.List;

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

    }

    private static void deleteAssociatedData(MongoDatabase database, String collectionName, String foreignKey,
            int clientId) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.deleteMany(new Document(foreignKey, clientId));
    }

    // -----------------------------------------Fonction-MANY-------------------------------------------------------

    // Méthode pour insérer plusieurs clients dans la collection Clients
    public static void insertManyClients(MongoDatabase database, List<Client> clients) {
        try {
            // Obtenir la collection Clients
            MongoCollection<Document> clientsCollection = database.getCollection("Clients");

            // Convertir la liste d'objets Client en une liste de documents
            List<Document> clientDocuments = Client.toDocuments(clients);

            // Insérer les documents dans la collection
            clientsCollection.insertMany(clientDocuments);

            System.out.println("Clients inserted successfully.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void readAllClients(MongoDatabase database) {
        try {
            // Obtenez la collection Clients
            MongoCollection<Document> clientsCollection = database.getCollection("Clients");

            // Récupérez tous les clients de la collection
            FindIterable<Document> result = clientsCollection.find();

            // Affichez les détails de chaque client
            System.out.println("Liste des clients :");
            for (Document client : result) {
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
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void updateManyClients(MongoDatabase database, List<Client> clients) {
        try {
            // Get the Clients collection
            MongoCollection<Document> clientsCollection = database.getCollection("Clients");

            // Prepare updates for each client
            List<Document> updates = clients.stream().map(client -> {
                Document filter = new Document("_id", client.getId());
                Document update = new Document("$set", new Document()
                        .append("Nom", client.getNom())
                        .append("Prenom", client.getPrenom())
                        .append("Email", client.getEmail())
                        .append("Telephone", client.getTelephone())
                        .append("SoldeCompte", client.getSoldeCompte())
                        .append("DerniereLocalisation", new Document()
                                .append("longitude", client.getLongitude())
                                .append("latitude", client.getLatitude())));
                return new Document("filter", filter).append("update", update);
            }).collect(java.util.stream.Collectors.toList());

            // Update many clients
            updates.forEach(update -> {
                Document filter = (Document) update.get("filter");
                Document updateDoc = (Document) update.get("update");
                clientsCollection.updateMany(filter, updateDoc, new UpdateOptions().upsert(true));
            });

            System.out.println("Clients updated successfully.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void deleteManyClients(MongoDatabase database, List<Integer> clientIds) {
        try {
            // Supprimer les clients de la collection Clients
            MongoCollection<Document> clientsCollection = database.getCollection("Clients");
            clientsCollection.deleteMany(new Document("_id", new Document("$in", clientIds)));

            // Supprimer les données associées (Avis, Reservations, Transactions, etc.)
            for (int clientId : clientIds) {
                deleteAssociatedData(database, "Avis", "ClientID", clientId);
                deleteAssociatedData(database, "Reservations", "ClientID", clientId);
                deleteAssociatedData(database, "Transactions", "ClientID", clientId);

            }

            System.out.println("Clients supprimés avec succès.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
