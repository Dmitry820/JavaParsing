import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;

import java.net.InetSocketAddress;
public class CreateTransactionTable
{
    public static final String TABLE_KEYSPACE = "transaction";
    public static final String TABLE_NAME = "transaction_table";

    public static void CreateCurrent() {
        try(CqlSession session = new CqlSessionBuilder()
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .withLocalDatacenter("datacenter1")
                .build()) {
            CreateCurrentTable(session, TABLE_KEYSPACE + "." + TABLE_NAME);
        }
    }
    public static void CreateCurrentTable(CqlSession session, String table_name) { //работает
        String query_create_table = "CREATE TABLE " +
                table_name + "(" +
                "TransactionID bigint PRIMARY KEY," +
                "ExecutionEntityName text," +
                "InstrumentName text," +
                "InstrumentClass text," +
                "Quantity text," +
                "Price text," +
                "Currency text," +
                "Datestamp text," +
                "NetAmount text);";
        session.execute(query_create_table);
    }
}
