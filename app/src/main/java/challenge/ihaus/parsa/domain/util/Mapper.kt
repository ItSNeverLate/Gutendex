package challenge.ihaus.parsa.domain.util

interface Mapper<T, Model> {

    fun fromModel(model: Model): T? = null

    fun toModel(t: T): Model? = null

    fun fromModelList(list: List<Model>): List<T>? = null

    fun toModelList(list: List<T>): List<Model>? = null
}