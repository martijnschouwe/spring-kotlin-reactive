package nl.agueda.playground.springkotlinreactive.persistence;

import nl.agueda.playground.springkotlinreactive.Product
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : ReactiveCrudRepository<Product, Long> {

}