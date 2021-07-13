import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.ColumnDefinition;
import com.datastax.oss.driver.api.core.cql.ColumnDefinitions;
import com.datastax.oss.driver.api.core.cql.Row;

import java.net.InetSocketAddress;

import java.net.InetSocketAddress;
import java.sql.ResultSet;
public class CreatePriceTable
{
    public static final String TABLE_KEYSPACE = "transaction";
    public static final String TABLE_NAME = "transaction_price_table";

    public static void CreatePriceTable() {
        try(CqlSession session = new CqlSessionBuilder()
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .withLocalDatacenter("datacenter1")
                .build()) {
            CreatePrice(session, TABLE_KEYSPACE + "." + TABLE_NAME);
        }
    }
    public static void CreatePrice(CqlSession session, String table_name) {
        String query_create_table =
                "CREATE TABLE " +
                        table_name + "(" +
                        "InstrumentName text," +
                        "Datestamp text," +
                        "Currency text," +
                        "AVGPrice text," +
                        "NetAmountPerDay text," +
                        "PRIMARY KEY ((InstrumentName, Currency), Datestamp, AVGPrice, NetAmountPerDay));";
        session.execute(query_create_table);
    }
}
