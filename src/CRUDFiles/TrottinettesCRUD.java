package CRUDFiles;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DAO.Trottinettes;

import org.bson.Document;

public class TrottinettesCRUD {

    public static void insertTrottinettes(MongoDatabase database, Trottinettes trottinettes) {
        try {
            // Obtenez la collection Trottinettes
            MongoCollection<Document> collection = database.getCollection("Trottinettes");

            // Convertissez l'objet Trottinettes en document MongoDB
            Document trottinettesDocument = trottinettes.toDocument();

            // Insérez le document dans la collection
            collection.insertOne(trottinettesDocument);

            System.out.println("Trottinettes inserted successfully");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void readTrottinettes(MongoDatabase database, int trottinettesId) {
        try {
            // Obtenez la collection Trottinettes
            MongoCollection<Document> collection = database.getCollection("Trottinettes");

            // Recherchez l'objet Trottinettes par son identifiant
            Document trottinettesDoc = collection.find(new Document("_id", trottinettesId)).first();

            if (trottinettesDoc != null) {
                // Affichez les détails de l'objet Trottinettes
                System.out.println("Trottinettes Details:");
                System.out.println("ID: " + trottinettesDoc.getInteger("_id"));
                System.out.println("EtatBatterie: " + trottinettesDoc.getInteger("EtatBatterie"));
                System.out.println("Disponibilite: " + trottinettesDoc.getBoolean("Disponibilite"));

                Document localisationDoc = (Document) trottinettesDoc.get("Localisation");
                if (localisationDoc != null) {
                    System.out.println("Localisation: " +
                            "longitude=" + localisationDoc.getDouble("longitude") +
                            ", latitude=" + localisationDoc.getDouble("latitude"));
                }

                System.out.println("DernierCheck: " + trottinettesDoc.get("DernierCheck"));
            } else {
                System.out.println("Trottinettes not found with ID: " + trottinettesId);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void updateTrottinettes(MongoDatabase database, Trottinettes trottinettes) {
        try {
            // Obtenez la collection Trottinettes
            MongoCollection<Document> collection = database.getCollection("Trottinettes");

            // Convertissez l'objet Trottinettes en document MongoDB
            Document trottinettesDocument = trottinettes.toDocument();

            // Obtenez l'identifiant (_id) de l'objet Trottinettes
            int trottinettesId = trottinettes.getId();

            // Mettez à jour le document dans la collection en utilisant l'identifiant
            collection.replaceOne(new Document("_id", trottinettesId), trottinettesDocument);

            System.out.println("Trottinettes updated successfully");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void deleteTrottinettes(MongoDatabase database, int trottinettesId) {
        try {
            // Obtenez la collection Trottinettes
            MongoCollection<Document> collection = database.getCollection("Trottinettes");

            // Supprimez l'objet Trottinettes de la collection en utilisant l'identifiant
            collection.deleteOne(new Document("_id", trottinettesId));

            // Supprimez toutes les références à cet objet dans d'autres collections
            // Par exemple, si d'autres collections contiennent un champ TrottinettesID,
            // supprimez ces documents

            // Supprimez les références dans la collection Avis
            MongoCollection<Document> avisCollection = database.getCollection("Avis");
            avisCollection.deleteMany(new Document("TrottinetteID", trottinettesId));

            // Supprimez les références dans la collection Reservations
            MongoCollection<Document> reservationsCollection = database.getCollection("Reservations");
            reservationsCollection.deleteMany(new Document("TrottinetteID", trottinettesId));

            // ... Ajoutez d'autres collections si nécessaire

            System.out.println("Trottinettes deleted successfully");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
