import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.ColumnDefinition;
import com.datastax.oss.driver.api.core.cql.ColumnDefinitions;
import com.datastax.oss.driver.api.core.cql.Row;

import java.net.InetSocketAddress;

import java.net.InetSocketAddress;
import java.sql.ResultSet;

public class CassandraConnector {
    public static final String KEY_SPACE_NAME = "transaction";
    public static final String REPLICATION_STRATEGY = "SimpleStrategy";
    public static final int REPLICATION_FACTOR = 1;
    public static void Connect(){
        try(CqlSession session = new CqlSessionBuilder()
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .withLocalDatacenter("datacenter1")
                .build()) {
            createGeneralKeyspace(session, KEY_SPACE_NAME, REPLICATION_STRATEGY, REPLICATION_FACTOR);
        }

        }
    public static void createGeneralKeyspace(CqlSession session, String keyspaceName, String replicationStrategy, int replicationFactor) { //работает
        String query_create_keyspace = "CREATE KEYSPACE IF NOT EXISTS " + keyspaceName + " WITH replication = {" +
                "'class':'" + replicationStrategy
                + "','replication_factor':" + replicationFactor + "};";
        session.execute(query_create_keyspace);
    }
    }


