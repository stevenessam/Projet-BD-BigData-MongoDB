import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

import CRUDFiles.ClientCRUD;
import CRUDFiles.ReservationsCRUD;
import CRUDFiles.TransactionsCRUD;
import CRUDFiles.TrottinettesCRUD;
import Entites.Avis;
import Entites.Client;
import Entites.Reservations;
import Entites.Transactions;
import Entites.Trottinettes;
import CRUDFiles.AgregationsCRUD;
import CRUDFiles.AvisCRUD;

public class main {

    public static void main(String[] args) {
        // Connect to MongoDB
        MongoClient mongoClient = ConnectToDB.connectToMongoDB();

        if (mongoClient != null) {
            /* Get the database */
            MongoDatabase database = mongoClient.getDatabase("Projet_BD_Groupe6_light");

            /* List collections */
            // ConnectToDB.listCollections(database);

            /*---------------------------------Client--------------------------------------------------------------------------*/
            /* Create a client with a specified _id */

            // Client newClient = new Client(
            // 22,
            // "NomClient",
            // "PrenomClient",
            // "client@example.com",
            // "0600000000",
            // 100.0,
            // 0.0,
            // 0.0);

            /* Use ClientCRUD to insert the client */
            // ClientCRUD.insertClient(database, newClient);

            /*--------------------------------*/
            /*--------------------------------*/
            /* Example: Update a client with ID 1 */

            // ClientCRUD.updateClient(database, 1, "test", "Peggy",
            // "benjamin82@example.org", "0667245488",
            // 113.25, -133.02307, -35.5714375);

            /*--------------------------------*/

            /* Appeler la fonction readClient avec l'ID du client que vous souhaitez lire */
            // ClientCRUD.readClient(database, 1);

            /* rechercher un client avec son nom */
            // ClientCRUD.searchClientsByName(database, "Duncan");

            /* Supprimer un client avec son ID */
            // ClientCRUD.deleteClient(database, 5);

            // ---------------------------------Fonction-MANY---------------------------------------------
            /* Créer une liste d'objets Client à insérer */
            // List<Client> clientsToInsert = Arrays.asList(
            // new Client(
            // 50,
            // "NomClient",
            // "PrenomClient",
            // "client@example.com",
            // "0600000000",
            // 100.0,
            // 0.0,
            // 0.0),
            // new Client(51,
            // "NomClient",
            // "PrenomClient",
            // "client@example.com",
            // "0600000000",
            // 100.0,
            // 0.0,
            // 0.0));

            /* Appeler la fonction pour insérer plusieurs clients */
            // ClientCRUD.insertManyClients(database, clientsToInsert);

            /* Exemple d'insertion de clients */
            // List<Client> clientsToUpdate = new ArrayList<>();
            // clientsToUpdate.add(new Client(50, "test", "NouveauPrenom1",
            // "nouveau1@example.com", "123456789",
            // 150.0, -73.567, 444));
            // clientsToUpdate.add(new Client(51, "test", "NouveauPrenom2",
            // "nouveau2@example.com", "987654321",
            // 200.0, -74.567, 4444));

            /* Appel de la méthode updateManyClients */
            // ClientCRUD.updateManyClients(database, clientsToUpdate);

            /* Exemple de suppression de plusieurs clients par leurs IDs */
            // List<Integer> clientIdsToDelete = Arrays.asList(7, 6);

            /* Appel de la méthode deleteManyClients */
            // ClientCRUD.deleteManyClients(database, clientIdsToDelete);

            /* Read all clients */
            // ClientCRUD.readAllClients(database);

            /*-------------------------------------------Trottinettes--------------------*/

            /* Créez un objet Trottinettes */
            // Trottinettes trottinettes = new Trottinettes(55, 80, true, -0.05, 0.05,
            // new Date(2024 - 1900, 0, 31, 14, 30));

            /* Insérez l'objet Trottinettes dans la base de données */
            // TrottinettesCRUD.insertTrottinettes(database, trottinettes);

            /* Créez un objet Trottinettes avec les nouvelles valeurs */
            // Trottinettes updatedTrottinettes = new Trottinettes(21, 90, false, 0.05,
            // -0.05, new Date(2024 - 1900, 0, 31, 14, 30));

            /* Mettez à jour l'objet Trottinettes dans la base de données */
            // TrottinettesCRUD.updateTrottinettes(database, updatedTrottinettes);

            /* Supprimez l'objet Trottinettes de la base de données */
            // TrottinettesCRUD.deleteTrottinettes(database, 21);

            /* Read tous les Trottinettes */
            // TrottinettesCRUD.readTrottinettes(database, 1);

            // ---------------------------------Fonction-MANY---------------------------------------------

            /* Exemple d'insertion multiple de trottinettes */
            // List<Trottinettes> trottinettesList = Arrays.asList(
            // new Trottinettes(50, 80, true, -0.05, 0.05,
            // new Date(2024 - 1900, 0, 31, 14, 30)),
            // new Trottinettes(51, 80, true, -0.05, 0.05,
            // new Date(2024 - 1900, 0, 31, 14, 30)));

            // TrottinettesCRUD.insertManyTrottinettes(database, trottinettesList);

            /* Exemple de lecture de toutes les trottinettes */
            // TrottinettesCRUD.readAllTrottinettes(database);

            /* Exemple de liste de Trottinettes à mettre à jour */
            // List<Trottinettes> trottinettesToUpdate = Arrays.asList(
            // new Trottinettes(50, 100, false, 48.8566, 2.3522, new Date(2024 - 1900, 0,
            // 31, 14, 30)),
            // new Trottinettes(51, 100, true, 40.7128, -74.0060, new Date(2024 - 1900, 0,
            // 31, 14, 30)));

            /* Appel de la fonction d'update many */
            // TrottinettesCRUD.updateManyTrottinettes(database, trottinettesToUpdate);

            /* Exemple de liste d'IDs de Trottinettes à supprimer */
            // List<Integer> trottinettesIdsToDelete = Arrays.asList(50, 51);
            // // Appel de la fonction de suppression multiple
            // TrottinettesCRUD.deleteManyTrottinettes(database, trottinettesIdsToDelete);

            /*--------------------------------------------------AVIS---------------------------------------------------------*/
            /* Insérer un nouvel avis */
            // AvisCRUD.insertAvis(database, 55, 1, 123, "Un nouveau commentaire sur la
            // trottinette.");

            /* Mettre à jour un avis (par exemple, l'avis avec l'ID 1) */
            // AvisCRUD.updateAvis(database, 53, 2, 26, "Nouveau commentaire mis à jour.");
            // AvisCRUD.deleteAvis(database, 50);
            // AvisCRUD.readAvis(database, 1);
            // ---------------------------------Fonction-MANY---------------------------------------------
            /* Créer une liste d'avis */
            // List<Document> avisList = new ArrayList<>();
            // avisList.add(new Document("_id", 50).append("ClientID",
            // 50).append("TrottinetteID", 1).append("MessageAvis",
            // "Avis 1"));
            // avisList.add(new Document("_id", 51).append("ClientID",
            // 51).append("TrottinetteID", 2).append("MessageAvis",
            // "Avis 2"));

            /* Appeler la fonction pour insérer plusieurs avis */
            // AvisCRUD.insertManyAvis(database, avisList);

            /* Créer une liste d'avis à mettre à jour */
            // List<Document> avisToUpdate = new ArrayList<>();
            // avisToUpdate.add(new Document("_id", 50).append("ClientID",
            // 2).append("TrottinetteID", 1)
            // .append("MessageAvis", "Nouvel Avis 1 test"));
            // avisToUpdate.add(new Document("_id", 51).append("ClientID",
            // 3).append("TrottinetteID", 2)
            // .append("MessageAvis", "Nouvel Avis 2 test"));

            /* Appeler la fonction pour mettre à jour plusieurs avis */
            // AvisCRUD.updateManyAvis(database, avisToUpdate);

            /* Créer une liste d'IDs d'avis à supprimer */
            // List<Integer> avisIdsToDelete = Arrays.asList(50, 51);

            /* Appeler la fonction pour supprimer plusieurs avis */
            // AvisCRUD.deleteManyAvis(database, avisIdsToDelete);

            /* Appel de la méthode readAllAvis */
            // AvisCRUD.readAllAvis(database);

            /*--------------------------------------------------Reservations---------------------------------------------------------*/
            /* Création d'un objet reservation */
            // Reservations reservation = new Reservations(
            // 56, // Remplacez par l'ID approprié
            // 10, // Exemple de ClientID
            // 10, // Exemple de TrottinetteID
            // new Date(2024 - 1900, 0, 31, 14, 30), // 31 janvier 2024 à 14h30
            // new Date(2024 - 1900, 0, 31, 16, 45),
            // 20.50 // Exemple de Tarif
            // );

            /* Insertion de la réservation dans la base de données */
            // ReservationsCRUD.insertReservation(database, reservation);

            /* Création d'un objet Reservation avec les nouvelles valeurs */
            // Reservations updatedReservation = new Reservations(
            // 11, // ID de la réservation à mettre à jour
            // 789, // Nouvelle valeur de ClientID
            // 987, // Nouvelle valeur de TrottinetteID
            // new Date(2024 - 1900, 0, 31, 14, 30), // Nouvelle valeur de DateHeureDebut
            // new Date(2024 - 1900, 0, 31, 15, 30), // Nouvelle valeur de DateHeureFin
            // 25.75 // Nouvelle valeur de Tarif
            // );

            /* Mise à jour de la réservation dans la base de données */
            // ReservationsCRUD.updateReservation(database, updatedReservation);

            /* delete reservation */
            // ReservationsCRUD.deleteReservation(database, 11);

            /* read Reservation by id */
            // ReservationsCRUD.readReservation(database, 2);

            // ---------------------------------Fonction-MANY---------------------------------------------

            /* Créez une liste d'objets Reservations pour tester l'insertion multiple */
            // List<Reservations> reservationsList = Arrays.asList(
            // new Reservations(59, 10, 20, new Date(2024 - 1900, 0, 31, 14, 30), // 31
            // janvier 2024 à 14h30
            // new Date(2024 - 1900, 0, 31, 16, 45), 10.0),
            // new Reservations(60, 15, 25, new Date(2024 - 1900, 0, 31, 14, 30), // 31
            // janvier 2024 à 14h30
            // new Date(2024 - 1900, 0, 31, 16, 45), 15.0));

            /* Appel de la méthode insertManyReservations */
            // ReservationsCRUD.insertManyReservations(database, reservationsList);

            /* Créez une liste de réservations à mettre à jour */
            // List<Reservations> reservationsToUpdate = new ArrayList<>();

            /*
             * Ajoutez des réservations à la liste (assurez-vous que les ID correspondent
             * aux réservations existantes)
             */
            // reservationsToUpdate.add(
            // new Reservations(59, 100, 100, new Date(2024 - 1900, 0, 31, 14, 30),
            // new Date(2024 - 1900, 0, 31, 14, 30), 222));
            // reservationsToUpdate.add(new Reservations(60, 150, 120, new Date(2024 - 1900,
            // 0, 31, 14, 30),
            // new Date(2024 - 1900, 0, 31, 14, 30), 225));

            /*
             * Appelez la méthode updateManyReservations pour mettre à jour les réservations
             */
            // ReservationsCRUD.updateManyReservations(database, reservationsToUpdate);

            /* Créez une liste d'IDs de réservations à supprimer */
            // List<Integer> reservationsToDelete = Arrays.asList(60, 59, 58);

            /* Appelez la méthode deleteManyReservations pour supprimer les réservations */
            // ReservationsCRUD.deleteManyReservations(database, reservationsToDelete);

            /* Appel de la méthode readAllReservations */
            // ReservationsCRUD.readAllReservations(database);

            /*--------------------------------------------------Transactions---------------------------------------------------------*/
            /* Exemple d'insertion d'une transaction */
            // Transactions transaction = new Transactions(50, 14, 25.0,
            // new Date(2024 - 1900, 0, 31, 14, 30), "Recharge de solde");
            // TransactionsCRUD.insertTransaction(database, transaction);

            /* Exemple de lecture d'une transaction par ID */
            // TransactionsCRUD.readTransaction(database, 1);

            /* Exemple de mise à jour d'une transaction par ID */
            // TransactionsCRUD.updateTransaction(database, 50, 100, new Date(2024 - 1900,
            // 0, 31, 14, 30), "Recharge de solde");

            /* Appel de la fonction de suppression */
            // TransactionsCRUD.deleteTransaction(database, 20);
            // ---------------------------------Fonction-MANY---------------------------------------------
            /* Exemple de liste de transactions à insérer */
            // List<Transactions> transactionsList = new ArrayList<>();
            // transactionsList
            // .add(new Transactions(55, 10, 50.0, new Date(2024 - 1900, 0, 31, 14, 30),
            // "Recharge de solde"));
            // transactionsList
            // .add(new Transactions(56, 20, 30.0, new Date(2024 - 1900, 0, 31, 14, 30),
            // "Recharge de solde"));

            /* Exemple d'appel pour insérer plusieurs transactions */
            // TransactionsCRUD.insertManyTransactions(database, transactionsList);

            /* Exemple d'appel pour lire toutes les transactions */
            // TransactionsCRUD.readAllTransactions(database);

            /* Exemple de liste de nouvelles transactions avec des valeurs mises à jour */
            // List<Transactions> updatedTransactionsList = Arrays.asList(
            // new Transactions(55, 10, 50.0, new Date(2024 - 1900, 0, 31, 14, 30),
            // "Paiement de location"),
            // new Transactions(56, 15, 30.0, new Date(2024 - 1900, 0, 31, 14, 30),
            // "Paiement de location"));

            /*
             * Utilisez la fonction updateManyTransactions pour mettre à jour les
             * transactions
             */
            // TransactionsCRUD.updateManyTransactions(database, updatedTransactionsList);

            /* Exemple de liste d'IDs de transactions à supprimer */
            // List<Integer> transactionsToDelete = Arrays.asList(55, 56);

            /*
             * Utilisez la fonction deleteManyTransactions pour supprimer les transactions
             */
            // TransactionsCRUD.deleteManyTransactions(database, transactionsToDelete);

            /*--------------------------------------------------FIN-CRUD---------------------------------------------------------*/
            /*-----------------------------------------------------------------------------------------------------------*/
            /*--------------------------------------------------AggregationsCRUD---------------------------------------------------------*/

            /* Exemple d'appel de la fonction pour obtenir les avis par ID de client */
            // AgregationsCRUD.getAvisByClientId(database, 2);

            /*
             * Exemple d'appel de la fonction pour compter les transactions par ID de client
             */
            // AgregationsCRUD.getTransactionCountByClientId(database, 1);

            /*
             * Exemple d'appel de la fonction pour obtenir les statistiques de réservation
             * par ID de client
             */
            // AgregationsCRUD.getClientReservationsInfo(database, 15);

            // -----filter, sort, and projection----------------------------------
            /* Example filter, sort, and projection documents */
            // AgregationsCRUD.searchClientsConditionnelle("Clients",
            // new Document(),
            // new Document("_id", 1),
            // new Document("Nom", 1).append("Prenom", 1).append("Email", 1),
            // database);

            /* Example filter, sort, and projection documents using $or */
            // AgregationsCRUD.searchClientsConditionnelle("Clients",
            // new Document("$or", List.of(
            // new Document("Nom", "Martin"),
            // new Document("Nom", "Duncan"))),
            // new Document("Prenom", 1),
            // new Document("Nom", 1).append("Prenom", 1).append("Email", 1),
            // database);

            // ----Fin-filter, sort, and projection----------------------------------

            /* Call the joinCollection1EtCollection2 join Clients et Avis function */
            // AgregationsCRUD.joinCollection1EtCollection2("Clients", "Avis",
            // "_id", "ClientID", new Document("_id", 1), "joined",
            // database);

            /*
             * Call the joinCollection1EtCollection2 join Trottinettes et Reservations
             * function
             */
            // AgregationsCRUD.joinCollection1EtCollection2("Trottinettes", "Reservations",
            // "_id", "TrottinetteID", new Document("_id", 1), "joined",
            // database);

            /*
             * Example group by operation on the "Clients" collection Call the groupBy
             * function
             */
            // AgregationsCRUD.groupBy("Reservations", "$group",
            // new Document("_id", "$ClientID")
            // .append("totalReservations", new Document("$count", new Document()))
            // .append("totalTarif", new Document("$sum", "$Tarif")),
            // database);

            /* Call the groupBy function */
            // AgregationsCRUD.groupBy("Transactions", "$group",
            // new Document("_id", "$ClientID")
            // .append("totalTransactions", new Document("$count", new Document()))
            // .append("totalMontantTousTransactions", new Document("$sum", "$Montant"))
            // .append("moyenMontantTousTransactions", new Document("$avg", "$Montant")),
            // database);

            /* Example: Creating an index for the "Clients" collection */
            // AgregationsCRUD.createClientIndexes("Clients", "idx_Clients_Nom",
            // "Nom", true, false, database);

            /* Example: Get all indexes of the "Clients" collection */
            // AgregationsCRUD.getAllIndexesOfACollection("Clients", database);

            /* rechercher un client avec son nom */
            // ClientCRUD.searchClientsByName(database, "Duncan");

            // Close the connection to MongoDB
            mongoClient.close();
        }

    }
}
