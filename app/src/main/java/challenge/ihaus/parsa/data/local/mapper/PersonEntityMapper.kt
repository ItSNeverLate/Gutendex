package challenge.ihaus.parsa.data.local.mapper

import challenge.ihaus.parsa.data.local.entity.PersonEntity
import challenge.ihaus.parsa.data.remote.dto.PersonDto
import challenge.ihaus.parsa.domain.model.Person
import challenge.ihaus.parsa.domain.util.Mapper

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
object PersonEntityMapper : Mapper<PersonEntity, Person> {
    override fun fromModel(model: Person) = PersonEntity(
        name = model.name,
        birthYear = model.birthYear,
        deathYear = model.deathYear
    )

    override fun toModel(entity: PersonEntity) = Person(
        name = entity.name,
        birthYear = entity.birthYear,
        deathYear = entity.deathYear
    )

    override fun fromModelList(list: List<Person>): List<PersonEntity> =
        list.map {
            fromModel(it)
        }

    override fun toModelList(list: List<PersonEntity>): List<Person> = list.map {
        toModel(it)
    }

    fun fromDto(dto: PersonDto) =
        PersonEntity(
            name = dto.name,
            birthYear = dto.birthYear,
            deathYear = dto.deathYear,
        )

    fun fromDtoList(list: List<PersonDto>): List<PersonEntity> = list.map {
        fromDto(it)
    }
}