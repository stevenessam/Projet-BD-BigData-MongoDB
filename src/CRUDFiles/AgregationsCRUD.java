package CRUDFiles;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

public class AgregationsCRUD {

    public static void getAvisByClientId(MongoDatabase database, int clientId) {
        try {
            // Obtenez la collection Avis
            AggregateIterable<Document> result = database.getCollection("Avis").aggregate(Arrays.asList(
                    Aggregates.match(Filters.eq("ClientID", clientId)),
                    Aggregates.lookup("Clients", "ClientID", "_id", "clientInfo"),
                    Aggregates.lookup("Trottinettes", "TrottinetteID", "_id", "trottinetteInfo"),
                    Aggregates.unwind("$clientInfo"),
                    Aggregates.unwind("$trottinetteInfo"),
                    Aggregates.project(
                            Document.parse(
                                    "{_id: 1, MessageAvis: 1, Nom: \"$clientInfo.Nom\", Prenom: \"$clientInfo.Prenom\", TrottinetteID: \"$trottinetteInfo._id\"}"))));

            // Affichez les résultats
            for (Document document : result) {
                System.out.println("Nom du client: " + document.getString("Nom"));
                System.out.println("Prénom du client: " + document.getString("Prenom"));
                System.out.println("ID de la trottinette: " + document.getInteger("TrottinetteID"));
                System.out.println("Message Avis: " + document.getString("MessageAvis"));
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void getTransactionCountByClientId(MongoDatabase database, int clientId) {
        try {
            // Obtenez la collection Transactions
            AggregateIterable<Document> result = database.getCollection("Transactions").aggregate(Arrays.asList(
                    Aggregates.match(Filters.eq("ClientID", clientId)),
                    Aggregates.group("$ClientID", Accumulators.sum("TransactionCount", 1)),
                    Aggregates.lookup("Clients", "_id", "_id", "clientInfo"),
                    Aggregates.project(
                            Document.parse(
                                    "{_id: 1, TransactionCount: 1, Email: { $arrayElemAt: [\"$clientInfo.Email\", 0] }}"))));

            // Affichez les résultats
            for (Document document : result) {
                System.out.println("Email du client: " + document.getString("Email"));
                System.out.println("Nombre de transactions: " + document.getInteger("TransactionCount"));
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void getClientReservationsInfo(MongoDatabase database, int clientId) {
        try {
            // Define the aggregation pipeline stages
            Bson matchStage = Aggregates.match(Filters.eq("ClientID", clientId));
            Bson lookupStage = Aggregates.lookup("Clients", "ClientID", "_id", "client_info");
            Bson unwindStage = Aggregates.unwind("$client_info");
            Bson groupStage = Aggregates.group(
                    "$ClientID",
                    Accumulators.sum("ReservationCount", 1),
                    Accumulators.sum("TotalTarif", "$Tarif"),
                    Accumulators.first("Nom", "$client_info.Nom"),
                    Accumulators.first("Prenom", "$client_info.Prenom"),
                    Accumulators.first("Email", "$client_info.Email"));

            // Execute the aggregation query
            AggregateIterable<Document> result = database.getCollection("Reservations")
                    .aggregate(Arrays.asList(matchStage, lookupStage, unwindStage, groupStage));

            // Print the result
            for (Document document : result) {
                System.out.println("Nom: " + document.getString("Nom"));
                System.out.println("Prenom: " + document.getString("Prenom"));
                System.out.println("Email: " + document.getString("Email"));
                System.out.println("Nombre de réservations : " + document.getInteger("ReservationCount"));
                System.out.println("Tarif total : " + document.getDouble("TotalTarif"));
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}