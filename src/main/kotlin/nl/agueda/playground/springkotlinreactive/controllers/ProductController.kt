package nl.agueda.playground.springkotlinreactive.controllers;

import nl.agueda.playground.springkotlinreactive.Product
import nl.agueda.playground.springkotlinreactive.services.ProductService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Controller
public class ProductController(val productService: ProductService) {

    @Bean
    fun route() = router {
        POST("/products") { request -> handleProductCreation(request) }
    }

    fun handleProductCreation(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Product::class.java)
                .map { requestProduct -> validateProduct(requestProduct) }
                .flatMap { validatedProduct -> productService.createProduct(validatedProduct) }
                .flatMap { persistedProduct -> ServerResponse.ok().bodyValue(persistedProduct) }
                .onErrorResume(IllegalArgumentException::class.java) { error -> ServerResponse.badRequest().bodyValue(error.message.toString()) }
                .doOnError() { error -> ServerResponse.status(500).bodyValue(error.message.toString()) }
    }

    fun validateProduct(product: Product): Product {
        if (product.name.isBlank()) {
            throw IllegalArgumentException("Product name cannot be blank")
        }
        if (product.price < 0) {
            throw IllegalArgumentException("Product price cannot be negative")
        }
        return product
    }
}
