package challenge.ihaus.parsa.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import challenge.ihaus.parsa.data.local.entity.RemoteKeyEntity

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: RemoteKeyEntity)

    @Query("SELECT * FROM remote_keys WHERE repoId = :id")
    suspend fun getKeyByRepoId(id: Int): RemoteKeyEntity?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAll()

    @Query("SELECT * FROM remote_keys")
    suspend fun getAll(): List<RemoteKeyEntity>
}