package CRUDFiles;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Entites.Trottinettes;

import java.util.List;

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

            System.out.println("Trottinettes deleted successfully");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // -----------------------------------------Fonction-MANY-------------------------------------------------------

    // insert Many Trottinettes
    public static void insertManyTrottinettes(MongoDatabase database, List<Trottinettes> trottinettesList) {
        try {
            // Obtenez la collection Trottinettes
            MongoCollection<Document> collection = database.getCollection("Trottinettes");

            // Convertissez la liste d'objets Trottinettes en une liste de documents MongoDB
            List<Document> trottinettesDocuments = Trottinettes.toDocumentList(trottinettesList);

            // Insérez les documents dans la collection
            collection.insertMany(trottinettesDocuments);

            System.out.println("Multiple trottinettes inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // read All Trottinettes
    public static void readAllTrottinettes(MongoDatabase database) {
        try {
            // Obtenez la collection Trottinettes
            MongoCollection<Document> collection = database.getCollection("Trottinettes");

            // Récupérez tous les documents de la collection
            FindIterable<Document> result = collection.find();

            // Affichez les détails de chaque trottinette
            System.out.println("Liste des Trottinettes :");
            for (Document trottinettesDoc : result) {
                System.out.println("Trottinettes ID: " + trottinettesDoc.getInteger("_id"));
                System.out.println("EtatBatterie: " + trottinettesDoc.getInteger("EtatBatterie"));
                System.out.println("Disponibilite: " + trottinettesDoc.getBoolean("Disponibilite"));

                Document localisationDoc = (Document) trottinettesDoc.get("Localisation");
                if (localisationDoc != null) {
                    System.out.println("Localisation: " +
                            "longitude=" + localisationDoc.getDouble("longitude") +
                            ", latitude=" + localisationDoc.getDouble("latitude"));
                }

                System.out.println("DernierCheck: " + trottinettesDoc.get("DernierCheck"));
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Mise à jour de plusieurs trottinettes
    public static void updateManyTrottinettes(MongoDatabase database, List<Trottinettes> trottinettesList) {
        try {
            // Obtenez la collection Trottinettes
            MongoCollection<Document> collection = database.getCollection("Trottinettes");

            // Convertissez la liste d'objets Trottinettes en une liste de documents MongoDB
            List<Document> trottinettesDocuments = Trottinettes.toDocumentList(trottinettesList);

            // Parcourez la liste et mettez à jour chaque document dans la collection
            for (Document trottinettesDocument : trottinettesDocuments) {
                // Obtenez l'identifiant (_id) de l'objet Trottinettes
                int trottinettesId = trottinettesDocument.getInteger("_id");

                // Créez le filtre pour trouver le document correspondant à l'ID
                Document filter = new Document("_id", trottinettesId);

                // Effectuez la mise à jour du document
                collection.replaceOne(filter, trottinettesDocument);
            }

            System.out.println("Multiple Trottinettes updated successfully");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // supprimer plusieurs trottinettes
    public static void deleteManyTrottinettes(MongoDatabase database, List<Integer> trottinettesIds) {
        try {
            // Obtenez la collection Trottinettes
            MongoCollection<Document> trottinettesCollection = database.getCollection("Trottinettes");

            // Obtenez la collection Reservations
            MongoCollection<Document> reservationsCollection = database.getCollection("Reservations");

            // Obtenez la collection Avis
            MongoCollection<Document> avisCollection = database.getCollection("Avis");

            // Parcourez la liste des IDs des trottinettes à supprimer
            for (int trottinettesId : trottinettesIds) {
                // Supprimez l'objet Trottinettes de la collection Trottinettes
                Document trottinettesFilter = new Document("_id", trottinettesId);
                trottinettesCollection.deleteOne(trottinettesFilter);

                // Supprimez les références dans la collection Reservations
                Document reservationsFilter = new Document("TrottinetteID", trottinettesId);
                reservationsCollection.deleteMany(reservationsFilter);

                // Supprimez les références dans la collection Avis
                Document avisFilter = new Document("TrottinetteID", trottinettesId);
                avisCollection.deleteMany(avisFilter);

            }

            System.out.println("Multiple Trottinettes deleted successfully");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
