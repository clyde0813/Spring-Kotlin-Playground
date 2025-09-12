package demo.practice.config

import demo.practice.domain.Todos
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import javax.sql.DataSource
import javax.xml.transform.Source

@TestConfiguration
class TestDatabaseConfig {

    @Bean
    fun datebase(dateSource: DataSource): Database {
        val db = Database.connect(dateSource)
        transaction(db) {
            SchemaUtils.create(Todos)
        }
        return db
    }
}