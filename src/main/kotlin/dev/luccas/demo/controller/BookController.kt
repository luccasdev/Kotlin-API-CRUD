package dev.luccas.demo.controller

import dev.luccas.demo.exception.CustomException
import dev.luccas.demo.model.Book
import dev.luccas.demo.model.Response
import dev.luccas.demo.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.async.DeferredResult
import javax.validation.Valid

@RestController
class BookController {

    @Autowired
    lateinit var bookService: BookService

    @GetMapping("/books" )
    fun getAllBooks(): DeferredResult<ResponseEntity<Response>> {
        val dr = DeferredResult<ResponseEntity<Response>>()

        try {
            dr.setResult(ResponseEntity.ok().body(Response.ok().addData("Book", bookService.findAllBooks())))
        } catch (e: CustomException){
            dr.setResult(ResponseEntity.status(e.httpStatus).body(Response.ok(e.message)))
        }
        return dr
    }

    @GetMapping("/book" )
    fun getBookById(@RequestParam(value = "id") id: Long):DeferredResult<ResponseEntity<Response>> {
        val dr = DeferredResult<ResponseEntity<Response>>()

        try {
            dr.setResult(ResponseEntity.ok().body(Response.ok().addData("Book", bookService.findBookById(id))))
        } catch (e: CustomException){
            dr.setResult(ResponseEntity.status(e.httpStatus).body(Response.ok(e.message)))
        }
        return dr
    }

    @PostMapping("/save/book")

    fun saveBook(@Valid @RequestBody book: Book): DeferredResult<ResponseEntity<Response>> {
        val dr = DeferredResult<ResponseEntity<Response>>()

        try {
            dr.setResult(ResponseEntity.ok().body(Response.ok().addData("Book", bookService.saveBook(book))))
        } catch (e: CustomException){
            dr.setResult(ResponseEntity.status(e.httpStatus).body(Response.ok(e.message)))
        }
        return dr
    }

    @GetMapping("/delete/book")
    fun deleteBook(@RequestParam(value = "id") id: Long): DeferredResult<ResponseEntity<Response>> {
        val dr = DeferredResult<ResponseEntity<Response>>()

        try {
            bookService.deleteBookById(id)
            dr.setResult(ResponseEntity.ok().body(Response.ok().addData("message", "Book deleted")))
        } catch (e: CustomException){
            dr.setResult(ResponseEntity.status(e.httpStatus).body(Response.ok(e.message)))
        }
        return dr
    }

}