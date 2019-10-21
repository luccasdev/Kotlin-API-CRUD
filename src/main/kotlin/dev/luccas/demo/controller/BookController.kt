package dev.luccas.demo.controller

import dev.luccas.demo.exception.CustomException
import dev.luccas.demo.model.Book
import dev.luccas.demo.model.Response
import dev.luccas.demo.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.async.DeferredResult
import javax.validation.Valid

@RestController
@RequestMapping("/books")
@Controller
class BookController {

    @Autowired
    lateinit var bookService: BookService

    @GetMapping
    fun getAllBooks(): DeferredResult<ResponseEntity<Response>> {
        val dr = DeferredResult<ResponseEntity<Response>>()

        try {
            dr.setResult(ResponseEntity.status(HttpStatus.OK).body(Response.ok().addData("Books", bookService.findAllBooks())))
        } catch (e: CustomException){
            dr.setResult(ResponseEntity.status(e.httpStatus).body(Response.ok(e.message)))
        }
        return dr
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") id: Long):DeferredResult<ResponseEntity<Response>> {
        val dr = DeferredResult<ResponseEntity<Response>>()

        try {
            dr.setResult(ResponseEntity.status(HttpStatus.OK).body(Response.ok().addData("Book", bookService.findBookById(id))))
        } catch (e: CustomException){
            dr.setResult(ResponseEntity.status(e.httpStatus).body(Response.ok(e.message)))
        }
        return dr
    }

    @PutMapping
    fun editBook(@Valid @RequestBody book: Book): DeferredResult<ResponseEntity<Response>> {
        val dr = DeferredResult<ResponseEntity<Response>>()

        try {
            dr.setResult(ResponseEntity.status(HttpStatus.OK).body(Response.ok().addData("Book", bookService.editBook(book))))
        } catch (e: CustomException){
            dr.setResult(ResponseEntity.status(e.httpStatus).body(Response.ok(e.message)))
        }
        return dr
    }

    @PostMapping
    fun saveBook(@Valid @RequestBody book: Book): DeferredResult<ResponseEntity<Response>> {
        val dr = DeferredResult<ResponseEntity<Response>>()

        try {
            dr.setResult(ResponseEntity.status(HttpStatus.CREATED).body(Response.ok().addData("Book", bookService.saveBook(book))))
        } catch (e: CustomException){
            dr.setResult(ResponseEntity.status(e.httpStatus).body(Response.ok(e.message)))
        }
        return dr
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable("id") id: Long): DeferredResult<ResponseEntity<Response>> {
        val dr = DeferredResult<ResponseEntity<Response>>()

        try {
            val bookDeleted = bookService.deleteBookById(id)
            dr.setResult(ResponseEntity.status(HttpStatus.OK).body(Response.ok().addData("bookDeleted", bookDeleted)))
        } catch (e: CustomException){
            dr.setResult(ResponseEntity.status(e.httpStatus).body(Response.ok(e.message)))
        }
        return dr
    }

}