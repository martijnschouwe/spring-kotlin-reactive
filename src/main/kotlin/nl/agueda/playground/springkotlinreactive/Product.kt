package nl.agueda.playground.springkotlinreactive

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("products")
data class Product(@Id val id: Long, val name: String, val price: Double) {
}