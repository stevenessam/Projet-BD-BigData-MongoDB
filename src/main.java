import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class main {

    public static void main(String[] args) {
        // Connect to MongoDB
        MongoClient mongoClient = ConnectToDB.connectToMongoDB();

        if (mongoClient != null) {
            // Get the database
            MongoDatabase database = mongoClient.getDatabase("Projet_BD_Groupe6_light");

            // List collections
            ConnectToDB.listCollections(database);

            // Close the MongoDB connection
            mongoClient.close();
        }
    }
}
