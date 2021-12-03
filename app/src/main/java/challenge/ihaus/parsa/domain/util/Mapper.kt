package challenge.ihaus.parsa.domain.util

interface Mapper<T, Model> {

    fun fromModel(model: Model): T

    fun toModel(t: T): Model

    fun fromModelList(list: List<Model>): List<T>

    fun toModelList(list: List<T>): List<Model>
}