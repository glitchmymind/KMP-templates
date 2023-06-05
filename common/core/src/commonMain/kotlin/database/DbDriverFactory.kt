package database

import com.squareup.sqldelight.db.SqlDriver
import config.PlatformConfig

expect class DbDriverFactory(config: PlatformConfig) {
    fun createDriver(schema: SqlDriver.Schema, name: String): SqlDriver
}