package database

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import config.PlatformConfig

actual class DbDriverFactory actual constructor(private val config: PlatformConfig) {
    actual fun createDriver(schema: SqlDriver.Schema, name: String): SqlDriver {
        return AndroidSqliteDriver(schema, config.context, name)
    }
}  