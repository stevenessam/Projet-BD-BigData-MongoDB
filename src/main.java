import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import CRUDFiles.ClientCRUD;
import CRUDFiles.ReservationsCRUD;
import CRUDFiles.TransactionsCRUD;
import CRUDFiles.TrottinettesCRUD;
import CRUDFiles.AvisCRUD;
import DAO.Avis;
import DAO.Client;
import DAO.Reservations;
import DAO.Transactions;
import DAO.Trottinettes;
import DAO.Avis;

public class main {

    public static void main(String[] args) {
        // Connect to MongoDB
        MongoClient mongoClient = ConnectToDB.connectToMongoDB();

        if (mongoClient != null) {
            // Get the database
            MongoDatabase database = mongoClient.getDatabase("Projet_BD_Groupe6_light");

            // List collections
            // ConnectToDB.listCollections(database);

            /*---------------------------------Client--------------------------------------------------------------------------*/
            // Create a client with a specified _id
            /*
             * Client newClient = new Client(
             * 22,
             * "NomClient",
             * "PrenomClient",
             * "client@example.com",
             * "0600000000",
             * 100.0,
             * 0.0,
             * 0.0);
             */
            // Use ClientCRUD to insert the client
            // ClientCRUD.insertClient(database, newClient);
            /*--------------------------------*/
            /*--------------------------------*/
            // Example: Update a client with ID 1
            /*
             * ClientCRUD.updateClient(database, 1, "test", "Peggy",
             * "benjamin82@example.org", "0667245488",
             * 113.25, -133.02307, -35.5714375);
             */
            /*--------------------------------*/

            // Appeler la fonction readClient avec l'ID du client que vous souhaitez lire
            // ClientCRUD.readClient(database, 1);

            // ClientCRUD.deleteClient(database, 5);
            /*-------------------------------------------Trottinettes----------------------------------------------------------------*/

            // Créez un objet Trottinettes
            // Trottinettes trottinettes = new Trottinettes(21, 80, true, -0.05, 0.05,
            // "2024-01-30T10:00:00Z");

            // Insérez l'objet Trottinettes dans la base de données
            // TrottinettesCRUD.insertTrottinettes(database, trottinettes);
            // Créez un objet Trottinettes avec les nouvelles valeurs
            /*
             * Trottinettes updatedTrottinettes = new Trottinettes(21, 90, false, 0.05,
             * -0.05, "2024-01-30T12:00:00Z");
             * 
             * // Mettez à jour l'objet Trottinettes dans la base de données
             * TrottinettesCRUD.updateTrottinettes(database, updatedTrottinettes);
             */
            // Supprimez l'objet Trottinettes de la base de données
            // TrottinettesCRUD.deleteTrottinettes(database, 21);
            // TrottinettesCRUD.readTrottinettes(database, 1);
            /*--------------------------------------------------AVIS---------------------------------------------------------*/
            // Insérer un nouvel avis
            // AvisCRUD.insertAvis(database, 54, 1, 123, "Un nouveau commentaire sur la
            // trottinette.");

            // Mettre à jour un avis (par exemple, l'avis avec l'ID 1)
            // AvisCRUD.updateAvis(database, 53, 2, 26, "Nouveau commentaire mis à jour
            // wow.");
            // AvisCRUD.deleteAvis(database, 50);
            // AvisCRUD.readAvis(database, 1);
            /*--------------------------------------------------Reservations---------------------------------------------------------*/
            // Création d'un objet Reservation
            /*
             * Reservations reservation = new Reservations(
             * 11, // Remplacez par l'ID approprié
             * 10, // Exemple de ClientID
             * 10, // Exemple de TrottinetteID
             * "2024-01-27T12:00:00", // Exemple de DateHeureDebut
             * "2024-01-27T14:00:00", // Exemple de DateHeureFin
             * 20.50 // Exemple de Tarif
             * );
             * 
             * // Insertion de la réservation dans la base de données
             * ReservationsCRUD.insertReservation(database, reservation);
             */

            // Création d'un objet Reservation avec les nouvelles valeurs
            /*
             * Reservations updatedReservation = new Reservations(
             * 11, // ID de la réservation à mettre à jour
             * 789, // Nouvelle valeur de ClientID
             * 987, // Nouvelle valeur de TrottinetteID
             * "2024-01-27T15:00:00", // Nouvelle valeur de DateHeureDebut
             * "2024-01-27T17:00:00", // Nouvelle valeur de DateHeureFin
             * 25.75 // Nouvelle valeur de Tarif
             * );
             * 
             * // Mise à jour de la réservation dans la base de données
             * ReservationsCRUD.updateReservation(database, updatedReservation);
             */
            // delete reservation
            // ReservationsCRUD.deleteReservation(database, 11);
            // Close the MongoDB connection
            // read
            // ReservationsCRUD.readReservation(database, 2);

            /*--------------------------------------------------Transactions---------------------------------------------------------*/
            // Exemple d'insertion d'une transaction
            // Transactions transaction = new Transactions(20, 14, 25.0,
            // "2024-01-28T10:30:00", "Recharge de solde");
            // TransactionsCRUD.insertTransaction(database, transaction);
            // Exemple de lecture d'une transaction par ID
            // TransactionsCRUD.readTransaction(database, 1);
            // Exemple de mise à jour d'une transaction par ID
            // TransactionsCRUD.updateTransaction(database, 2, 44.0, new Date(), "Recharge
            // de solde");
            // Appel de la fonction de suppression
            // TransactionsCRUD.deleteTransaction(database, 20);
            /*--------------------------------------------------FIN CRUD---------------------------------------------------------*/

            mongoClient.close();
        }
    }
}
