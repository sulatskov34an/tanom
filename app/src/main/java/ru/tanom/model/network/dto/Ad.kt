package ru.tanom.model.network.dto

data class Ad(
    val id: Int,
    val carFactory: String,
    val carModel: String,
    val productionYear: Int,
    val bodyType: String,
    val transmissionType: String,
    val steeringWheelLocation: String,
    val mileage: Long,
    val description: String,
    val inspectionPlace: String,
    val price: Int,
    val phone: String,
    val creationDate: Long
)