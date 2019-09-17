package dev.luccas.demo.model

import com.fasterxml.jackson.annotation.JsonInclude

import java.util.HashMap


@JsonInclude(JsonInclude.Include.NON_NULL)
class Response {

    var message: String? = null

    private var data: MutableMap<String, Any>? = null

    /**
     * Adiciona um parametro para retorno JSON.
     *
     * @param key .
     * @param value .
     * @return Response
     */
    fun addData(key: String, value: Any): Response {

        if (this.data == null) {
            this.data = HashMap()
        }

        if (this.data!!.containsKey(key)) {
            throw IllegalArgumentException(String.format("Key [%s] exists.", key))
        }

        this.data!![key] = value

        return this
    }

    fun getData(): Map<String, Any>? {
        return data
    }

    fun setData(data: MutableMap<String, Any>) {
        this.data = data
    }

    override fun toString(): String {
        return "Response [message=$message, data=$data]"
    }

    companion object {

        /**
         * Response ok
         * @return [Response]
         */
        fun ok(): Response {
            return Response()
        }

        /**
         * Resposta ok com mensagem.
         *
         * @param message .
         * @return Response
         */
        fun ok(message: String): Response {
            val r = Response.ok()
            r.message = message
            return r
        }

        /**
         * Resposta de erro com mensagem.
         *
         * @param message .
         * @return Response
         */
        fun error(message: String): Response {
            val r = Response()
            r.message = message
            return r
        }

        /**
         * Resposta do tipo internal server error
         * @return [Response]
         */
        fun internalServerError(): Response {
            return Response.error("Internal Server Error")
        }
    }

}
