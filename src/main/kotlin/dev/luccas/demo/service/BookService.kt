package dev.luccas.demo.service

import dev.luccas.demo.exception.CustomException
import dev.luccas.demo.model.Book
import dev.luccas.demo.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService {

    @Autowired
    lateinit var bookRepository: BookRepository

    fun findAllBooks(): MutableIterable<Book> {
        if(bookRepository.count() == 0L){
            throw CustomException("Library is Empty", HttpStatus.BAD_REQUEST)
        }
        return bookRepository.findAll()
    }

    fun saveBook(book: Book) = bookRepository.save(book)

    fun findBookById(id: Long): Optional<Book> {
        val book = bookRepository.findById(id)

        if(book.isEmpty){
            throw CustomException("Book Id not found", HttpStatus.BAD_REQUEST)
        }
        return book
    }

    fun deleteBookById(id: Long) {
        val book = bookRepository.findById(id)
        if(book.isEmpty){
            throw CustomException("Book Id not found", HttpStatus.BAD_REQUEST)
        }
        return bookRepository.deleteById(id)
    }

}