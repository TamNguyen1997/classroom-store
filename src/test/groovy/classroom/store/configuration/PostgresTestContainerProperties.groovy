package classroom.store.configuration

import lombok.Getter
import lombok.Setter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Getter
@Setter
@Component
@ConfigurationProperties("test-container.postgresql")
class PostgresTestContainerProperties {
    Integer port
    String host
    String username
    String password
    String databaseName
    String jdbcUrl
}
