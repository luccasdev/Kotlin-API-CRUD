package dev.luccas.demo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity
@Table
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        var name: String = "",

        var category: String = "",

        var amount: Long = 0L
)
