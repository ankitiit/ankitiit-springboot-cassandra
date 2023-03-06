package ankitiit.springboot.cassandra.basic.config;

import com.datastax.driver.core.Session;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.ReactiveSession;
import org.springframework.data.cassandra.core.AsyncCassandraTemplate;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;

@Configuration
public class CassandraConfig {
    public @Bean CqlSession session() {
        return CqlSession.builder().withKeyspace("ecommercekeyspace").build();
    }

    @Bean
    AsyncCassandraTemplate asyncCassandraTemplate(Session session) {
        return new AsyncCassandraTemplate(session);
    }

    @Bean
    ReactiveCassandraTemplate reactiveCassandraTemplate(ReactiveSession session) {
        return new ReactiveCassandraTemplate(session);
    }
    @Bean
    CassandraTemplate cassandraTemplate(Session session) {
        return new CassandraTemplate(session);
    }



   /* DriverConfigLoader configLoader = DriverConfigLoader.fromMap(
            ImmutableMap.of(
                    DefaultDriverOption.CONNECTION_POOL_LOCAL_SIZE, 10 // Set the pool size to 10
            )
    );

    CqlSession session = CqlSession.builder()
            .withConfigLoader(configLoader)
            .build();*/


}
