package com.tienda3b.app.core.data.model.cats

import com.tienda3b.app.core.database.model.cats.CatsEntity
import com.tienda3b.app.core.database.model.cats.CatsImageEntity
import com.tienda3b.app.core.database.model.cats.CatsWeightEntity
import com.tienda3b.app.core.network.model.NetworkCatsItemModel
import com.tienda3b.app.core.network.model.NetworkImageCatsModel
import com.tienda3b.app.core.network.model.NetworkWeightCatsModel

fun NetworkCatsItemModel.toEntity(): CatsEntity? {
    return CatsEntity(
        id = id ?: "",
        name = name?.trim().orEmpty(),
        temperament = temperament?.trim().orEmpty(),
        origin = origin?.trim().orEmpty(),
        country_codes = countryCodes?.trim().orEmpty(),
        country_code = countryCode?.trim().orEmpty(),
        description = description?.trim().orEmpty(),
        life_span = lifeSpan?.trim().orEmpty(),
        indoor = indoor ?: 0,

        alt_names = altNames?.trim().orEmpty(),
        adaptability = adaptability ?: 0,
        affection_level = affectionLevel ?: 0,
        child_friendly = childFriendly ?: 0,
        dog_friendly = dogFriendly ?: 0,
        energy_level = energyLevel ?: 0,
        grooming = grooming ?: 0,
        health_issues = healthIssues ?: 0,
        intelligence = intelligence ?: 0,
        shedding_level = sheddingLevel ?: 0,
        social_needs = socialNeeds ?: 0,
        stranger_friendly = strangerFriendly ?: 0,
        vocalisation = vocalisation ?: 0,
        experimental = experimental ?: 0,
        hairless = hairless ?: 0,
        natural = natural ?: 0,
        rare = rare ?: 0,
        rex = rex ?: 0,
        suppressed_tail = suppressedTail ?: 0,
        short_legs = shortLegs ?: 0,

        wikipedia_url = wikipediaUrl?.trim().orEmpty(),
        hypoallergenic = hypoallergenic ?: 0,
        reference_image_id = referenceImageId?.trim().orEmpty(),

        weight = weight.toEmb(),
        image = image.toEmb(),

        cfa_url = cfaUrl?.trim().orEmpty(),
        vcahospitals_url = vcahospitalsUrl?.trim().orEmpty(),
        lap = lap ?: 0,
        cat_friendly = catFriendly ?: 0,
        bidability = bidability ?: 0,
        vetstreet_url = vetstreetUrl?.trim().orEmpty()
    )
}

fun List<NetworkCatsItemModel>.toEntities(): List<CatsEntity> =
    mapNotNull { it.toEntity() }

fun NetworkWeightCatsModel?.toEmb(): CatsWeightEntity =
    CatsWeightEntity(
        imperial = this?.imperial?.trim().orEmpty(),
        metric = this?.metric?.trim().orEmpty()
    )

fun NetworkImageCatsModel?.toEmb(): CatsImageEntity =
    CatsImageEntity(
        id = this?.id?.trim().orEmpty(),
        width = this?.width ?: 0,
        height = this?.height ?: 0,
        url = this?.url?.trim().orEmpty()
    )