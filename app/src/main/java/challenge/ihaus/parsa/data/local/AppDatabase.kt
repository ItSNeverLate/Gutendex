package challenge.ihaus.parsa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import challenge.ihaus.parsa.data.local.dao.BookDao
import challenge.ihaus.parsa.data.local.dao.FavoriteDao
import challenge.ihaus.parsa.data.local.dao.RemoteKeyDao
import challenge.ihaus.parsa.data.local.entity.BookEntity
import challenge.ihaus.parsa.data.local.entity.FavoriteEntity
import challenge.ihaus.parsa.data.local.entity.RemoteKeyEntity

@Database(entities = [BookEntity::class, FavoriteEntity::class, RemoteKeyEntity::class], version = 1)
@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "app_db"
    }

    abstract fun bookDao(): BookDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}