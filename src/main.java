import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import CRUDFiles.ClientCRUD;
import CRUDFiles.ReservationsCRUD;
import CRUDFiles.TransactionsCRUD;
import CRUDFiles.TrottinettesCRUD;
import Entites.Avis;
import Entites.Client;
import Entites.Reservations;
import Entites.Transactions;
import Entites.Trottinettes;
import CRUDFiles.AvisCRUD;

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

            // ---------------------------------Fonction-MANY-------------------
            // Créer une liste d'objets Client à insérer
            /*
             * List<Client> clientsToInsert = Arrays.asList(
             * new Client(
             * 50,
             * "NomClient",
             * "PrenomClient",
             * "client@example.com",
             * "0600000000",
             * 100.0,
             * 0.0,
             * 0.0),
             * new Client(51,
             * "NomClient",
             * "PrenomClient",
             * "client@example.com",
             * "0600000000",
             * 100.0,
             * 0.0,
             * 0.0)
             * // Ajoutez d'autres clients si nécessaire
             * );
             * 
             * // Appeler la fonction pour insérer plusieurs clients
             * ClientCRUD.insertManyClients(database, clientsToInsert);
             */

            // Exemple d'insertion de clients
            /*
             * List<Client> clientsToUpdate = new ArrayList<>();
             * clientsToUpdate.add(new Client(50, "test", "NouveauPrenom1",
             * "nouveau1@example.com", "123456789",
             * 150.0, -73.567, 444));
             * clientsToUpdate.add(new Client(51, "test", "NouveauPrenom2",
             * "nouveau2@example.com", "987654321",
             * 200.0, -74.567, 4444));
             * 
             * // Appel de la méthode updateManyClients
             * ClientCRUD.updateManyClients(database, clientsToUpdate);
             */
            // Exemple de suppression de plusieurs clients par leurs IDs
            /*
             * List<Integer> clientIdsToDelete = Arrays.asList(7, 6);
             * 
             * // Appel de la méthode deleteManyClients
             * ClientCRUD.deleteManyClients(database, clientIdsToDelete);
             */
            // Read all clients
            // ClientCRUD.readAllClients(database);
            /*-------------------------------------------Trottinettes--------------------
            * --------------------------------------------
            */

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
            // AvisCRUD.insertAvis(database, 55, 1, 123, "Un nouveau commentaire sur la
            // trottinette.");

            // Mettre à jour un avis (par exemple, l'avis avec l'ID 1)
            // AvisCRUD.updateAvis(database, 53, 2, 26, "Nouveau commentaire mis à jour
            // wow.");
            // AvisCRUD.deleteAvis(database, 50);
            // AvisCRUD.readAvis(database, 1);
            // ----------Fonction-MANY--------------------------------------------------------------------
            // Créer une liste d'avis
            /*
             * List<Document> avisList = new ArrayList<>();
             * avisList.add(new Document("_id", 50).append("ClientID",
             * 50).append("TrottinetteID", 1).append("MessageAvis",
             * "Avis 1"));
             * avisList.add(new Document("_id", 51).append("ClientID",
             * 51).append("TrottinetteID", 2).append("MessageAvis",
             * "Avis 2"));
             * 
             * // Appeler la fonction pour insérer plusieurs avis
             * AvisCRUD.insertManyAvis(database, avisList);
             */
            // Créer une liste d'avis à mettre à jour
            /*
             * List<Document> avisToUpdate = new ArrayList<>();
             * avisToUpdate.add(new Document("_id", 50).append("ClientID",
             * 2).append("TrottinetteID", 1)
             * .append("MessageAvis", "Nouvel Avis 1 test"));
             * avisToUpdate.add(new Document("_id", 51).append("ClientID",
             * 3).append("TrottinetteID", 2)
             * .append("MessageAvis", "Nouvel Avis 2 test"));
             * 
             * // Appeler la fonction pour mettre à jour plusieurs avis
             * AvisCRUD.updateManyAvis(database, avisToUpdate);
             */
            // Créer une liste d'IDs d'avis à supprimer
            /*
             * List<Integer> avisIdsToDelete = Arrays.asList(50, 51);
             * 
             * // Appeler la fonction pour supprimer plusieurs avis
             * AvisCRUD.deleteManyAvis(database, avisIdsToDelete);
             */
            // Appel de la méthode readAllAvis
            // AvisCRUD.readAllAvis(database);

            /*--------------------------------------------------Reservations---------------------------------------------------------*/
            // Création d'un objet Reservation

            Reservations reservation = new Reservations(
                    56, // Remplacez par l'ID approprié
                    10, // Exemple de ClientID
                    10, // Exemple de TrottinetteID
                    new Date(2024 - 1900, 0, 31, 14, 30), // 31 janvier 2024 à 14h30
                    new Date(2024 - 1900, 0, 31, 16, 45),
                    20.50 // Exemple de Tarif
            );

            // Insertion de la réservation dans la base de données
            ReservationsCRUD.insertReservation(database, reservation);

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
            // --------------------------------------------------Fonction-MANY-------------------------------------

            // Créez une liste d'objets Reservations pour tester l'insertion multiple
            /*
             * List<Reservations> reservationsList = Arrays.asList(
             * new Reservations(59, 10, 20, new Date(2024 - 1900, 0, 31, 14, 30), // 31
             * janvier 2024 à 14h30
             * new Date(2024 - 1900, 0, 31, 16, 45), 10.0),
             * new Reservations(60, 15, 25, new Date(2024 - 1900, 0, 31, 14, 30), // 31
             * janvier 2024 à 14h30
             * new Date(2024 - 1900, 0, 31, 16, 45), 15.0)
             * // Ajoutez d'autres objets Reservations au besoin
             * );
             * 
             * // Appel de la méthode insertManyReservations
             * ReservationsCRUD.insertManyReservations(database, reservationsList);
             */

            // Créez une liste de réservations à mettre à jour
            /*
             * List<Reservations> reservationsToUpdate = new ArrayList<>();
             * 
             * // Ajoutez des réservations à la liste (assurez-vous que les ID correspondent
             * // aux réservations existantes)
             * reservationsToUpdate.add(
             * new Reservations(59, 100, 100, new Date(2024 - 1900, 0, 31, 14, 30),
             * new Date(2024 - 1900, 0, 31, 14, 30), 222));
             * reservationsToUpdate.add(new Reservations(60, 150, 120, new Date(2024 - 1900,
             * 0, 31, 14, 30),
             * new Date(2024 - 1900, 0, 31, 14, 30), 225));
             * 
             * // Appelez la méthode updateManyReservations pour mettre à jour les
             * réservations
             * ReservationsCRUD.updateManyReservations(database, reservationsToUpdate);
             */
            // Créez une liste d'IDs de réservations à supprimer
            /*
             * List<Integer> reservationsToDelete = Arrays.asList(60, 59, 58); // Remplacez
             * par les IDs appropriés
             * 
             * // Appelez la méthode deleteManyReservations pour supprimer les réservations
             * ReservationsCRUD.deleteManyReservations(database, reservationsToDelete);
             */
            // Appel de la méthode readAllReservations
            // ReservationsCRUD.readAllReservations(database);

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
