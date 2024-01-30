import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class ConnectToDB {

    public static MongoClient connectToMongoDB() {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            System.out.println("Connected to the database successfully");
            return mongoClient;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void listCollections(MongoDatabase database) {
        try {
            MongoIterable<String> collectionNames = database.listCollectionNames();
            System.out.println("Collections in the database:");

            for (String collectionName : collectionNames) {
                System.out.println(collectionName);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
