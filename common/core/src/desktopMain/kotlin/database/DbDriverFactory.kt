package database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import config.PlatformConfig

actual class DbDriverFactory actual constructor(private val config: PlatformConfig) {
    actual fun createDriver(schema: SqlDriver.Schema, name: String): SqlDriver {
        return JdbcSqliteDriver("jdbc:sqlite:$name")
    }
}  