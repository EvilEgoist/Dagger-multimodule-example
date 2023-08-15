package ru.multimodule.network_api.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseErrors(
    val errors: List<String>
)