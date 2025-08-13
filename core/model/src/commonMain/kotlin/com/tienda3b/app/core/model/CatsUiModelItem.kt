package com.tienda3b.app.core.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
@Stable
data class CatsUiModelItem(
    val weight: Weight,
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val country_codes: String,
    val country_code: String,
    val description: String,
    val life_span: String,
    val indoor: Int,
    val alt_names: String,
    val adaptability: Int,
    val affection_level: Int,
    val child_friendly: Int,
    val dog_friendly: Int,
    val energy_level: Int,
    val grooming: Int,
    val health_issues: Int,
    val intelligence: Int,
    val shedding_level: Int,
    val social_needs: Int,
    val stranger_friendly: Int,
    val vocalisation: Int,
    val experimental: Int,
    val hairless: Int,
    val natural: Int,
    val rare: Int,
    val rex: Int,
    val suppressed_tail: Int,
    val short_legs: Int,
    val wikipedia_url: String,
    val hypoallergenic: Int,
    val reference_image_id: String,
    val image: Image,
    val cfa_url: String,
    val vcahospitals_url: String,
    val lap: Int,
    val cat_friendly: Int,
    val bidability: Int,
    val vetstreet_url: String
)