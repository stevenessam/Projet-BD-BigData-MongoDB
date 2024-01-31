import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import CRUDFiles.ClientCRUD;
import CRUDFiles.TrottinettesCRUD;
import DAO.Client;
import DAO.Trottinettes;

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
            /*-----------------------------------------------------------------------------------------------------------*/
            // Close the MongoDB connection
            mongoClient.close();
        }
    }
}
