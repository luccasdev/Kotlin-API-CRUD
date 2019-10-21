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

        if(bookRepository.count() == 0L)
            throw CustomException("Library is Empty", HttpStatus.BAD_REQUEST)

        return bookRepository.findAll()
    }

    fun saveBook(book: Book) = bookRepository.save(book)

    fun editBook(book: Book): Book {
        bookRepository.findById(book.id).orElse(null) ?: throw CustomException("Book not exists", HttpStatus.BAD_REQUEST)

        return bookRepository.saveAndFlush(book)
    }

    fun findBookById(id: Long): Book {
        return bookRepository.findById(id).orElse(null) ?: throw CustomException("Book Id not found", HttpStatus.BAD_REQUEST)
    }

    fun deleteBookById(id: Long): Book {
        val deletedBook = bookRepository.findById(id).orElse(null) ?: throw CustomException("Book Id not found", HttpStatus.BAD_REQUEST)
        bookRepository.deleteById(id)

        return deletedBook
    }

}