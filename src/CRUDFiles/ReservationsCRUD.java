package CRUDFiles;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DAO.Reservations;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
