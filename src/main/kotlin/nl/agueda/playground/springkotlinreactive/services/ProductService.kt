package nl.agueda.playground.springkotlinreactive.services

import nl.agueda.playground.springkotlinreactive.Product
import nl.agueda.playground.springkotlinreactive.persistence.ProductRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class ProductService(val productRepository: ProductRepository) {
    fun createProduct(product: Product): Mono<Product> {
        return productRepository.save(product)
    }
}