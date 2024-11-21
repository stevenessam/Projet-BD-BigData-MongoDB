package CRUDFiles;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Entites.Reservations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.Document;

public class ReservationsCRUD {

    private static MongoCollection<Document> getCollection(MongoDatabase database) {
        return database.getCollection("Reservations");
    }

    public static void insertReservation(MongoDatabase database, Reservations reservation) {
        try {
            MongoCollection<Document> collection = getCollection(database);

            // Convertir l'objet Reservation en Document
            Document reservationDocument = reservation.toDocument();

            // Insérer le document dans la collection
            collection.insertOne(reservationDocument);

            System.out.println("Reservation inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void readReservation(MongoDatabase database, int reservationID) {
        try {
            MongoCollection<Document> collection = getCollection(database);

            // Créer le filtre pour trouver le document correspondant à l'ID
            Document filter = new Document("_id", reservationID);

            // Rechercher le document correspondant à l'ID
            Document reservation = collection.find(filter).first();

            // Vérifier si la réservation existe
            if (reservation != null) {
                System.out.println("Reservation details:");
                System.out.println("Reservation ID: " + reservation.getInteger("_id"));
                System.out.println("Client ID: " + reservation.getInteger("ClientID"));
                System.out.println("Trottinette ID: " + reservation.getInteger("TrottinetteID"));

                // Récupérer les dates et les formater
                Date dateHeureDebut = reservation.getDate("DateHeureDebut");
                Date dateHeureFin = reservation.getDate("DateHeureFin");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                System.out.println("Date Heure Debut: " + dateFormat.format(dateHeureDebut));
                System.out.println("Date Heure Fin: " + dateFormat.format(dateHeureFin));

                System.out.println("Tarif: " + reservation.getDouble("Tarif"));
            } else {
                System.out.println("Reservation not found!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void updateReservation(MongoDatabase database, Reservations reservation) {
        try {
            MongoCollection<Document> collection = getCollection(database);

            // Convertir l'objet Reservation en Document
            Document reservationDocument = reservation.toDocument();

            // Extraire l'ID de la réservation
            int reservationID = reservation.get_id();

            // Créer le filtre pour trouver le document correspondant à l'ID
            Document filter = new Document("_id", reservationID);

            // Effectuer la mise à jour du document
            collection.replaceOne(filter, reservationDocument);

            System.out.println("Reservation updated successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void deleteReservation(MongoDatabase database, int reservationID) {
        try {
            MongoCollection<Document> collection = getCollection(database);

            // Créer le filtre pour trouver le document correspondant à l'ID
            Document filter = new Document("_id", reservationID);

            // Supprimer le document correspondant à l'ID
            collection.deleteOne(filter);

            System.out.println("Reservation deleted successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // -----------------------------------------Fonction-MANY-------------------------------------------------------

    public static void readAllReservations(MongoDatabase database) {
        try {
            // Obtenez la collection Reservations
            MongoCollection<Document> reservationsCollection = database.getCollection("Reservations");

            // Récupérez tous les documents de la collection
            FindIterable<Document> result = reservationsCollection.find();

            // Affichez les détails de chaque réservation
            System.out.println("Liste des réservations :");
            for (Document reservation : result) {
                System.out.println("Reservation ID: " + reservation.getInteger("_id"));
                System.out.println("Client ID: " + reservation.getInteger("ClientID"));
                System.out.println("Trottinette ID: " + reservation.getInteger("TrottinetteID"));
                System.out.println("Date Heure Debut: " + reservation.getDate("DateHeureDebut"));
                System.out.println("Date Heure Fin: " + reservation.getDate("DateHeureFin"));
                System.out.println("Tarif: " + reservation.getDouble("Tarif"));
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // insert Many Reservations
    public static void insertManyReservations(MongoDatabase database, List<Reservations> reservationsList) {
        try {
            // Obtenez la collection Reservations
            MongoCollection<Document> reservationsCollection = database.getCollection("Reservations");

            // Convertissez la liste d'objets Reservations en une liste de documents MongoDB
            List<Document> reservationDocuments = Reservations.toDocumentList(reservationsList);

            // Insérez les documents dans la collection
            reservationsCollection.insertMany(reservationDocuments);

            System.out.println("Multiple reservations inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Méthode pour mettre à jour plusieurs réservations dans la collection
    // Reservations
    public static void updateManyReservations(MongoDatabase database, List<Reservations> reservationsList) {
        try {
            // Obtenez la collection Reservations
            MongoCollection<Document> reservationsCollection = database.getCollection("Reservations");

            // Parcourez la liste des réservations à mettre à jour
            for (Reservations reservation : reservationsList) {
                // Convertissez l'objet Reservation en Document
                Document reservationDocument = reservation.toDocument();

                // Extraire l'ID de la réservation
                int reservationID = reservation.get_id();

                // Créer le filtre pour trouver le document correspondant à l'ID
                Document filter = new Document("_id", reservationID);

                // Effectuer la mise à jour du document
                reservationsCollection.replaceOne(filter, reservationDocument);
            }

            System.out.println("Multiple reservations updated successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Supprimer plusieurs réservations par leurs IDs
    public static void deleteManyReservations(MongoDatabase database, List<Integer> reservationIds) {
        try {
            // Obtenez la collection Reservations
            MongoCollection<Document> reservationsCollection = database.getCollection("Reservations");

            // Créez un filtre pour trouver les documents correspondants aux IDs spécifiés
            Document filter = new Document("_id", new Document("$in", reservationIds));

            // Supprimez les documents correspondants aux IDs spécifiés
            reservationsCollection.deleteMany(filter);

            System.out.println("Multiple reservations deleted successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
