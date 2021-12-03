package challenge.ihaus.parsa.data.local.entity

/** We don't persist it directly into DB
 * We use Converter to save it as a Json String
 */
data class PersonEntity(
    val birthYear: Int?,
    val deathYear: Int?,
    val name: String
)