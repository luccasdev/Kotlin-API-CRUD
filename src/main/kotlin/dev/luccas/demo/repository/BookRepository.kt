package dev.luccas.demo.repository

import dev.luccas.demo.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {

}