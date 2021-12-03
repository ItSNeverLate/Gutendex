package challenge.ihaus.parsa.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity (
    @PrimaryKey(autoGenerate = false)
    val repoId: Int,
    val prevKey: Int?,
    val nextKey: Int?,
    val isEndReached: Boolean
)