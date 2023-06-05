package database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import config.PlatformConfig

actual class DbDriverFactory actual constructor(private val config: PlatformConfig) {
    actual fun createDriver(schema: SqlDriver.Schema, name: String): SqlDriver {
        return NativeSqliteDriver(schema, name)
    }
}  