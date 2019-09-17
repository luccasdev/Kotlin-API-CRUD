# Kotlin Spring Boot API + MySQL
Demonstration of CRUD API Rest in Kotlin with Spring Boot


<h2>Create</h2>
<p>POST <i>http://localhost:8080/save/book</i></p>
<p>Body: {
	        "name":"Kotlin Book",
	        "category":"Programming",
	        "amount": 4
          }</p>

<h2>Read by id</h2>
<p>GET <i>http://localhost:8080/book?id=XX</i></p>


<h2>Read All</h2>
<p>GET <i>http://localhost:8080/books</i></p>

<h2>Update</h2>
<p>POST <i>http://localhost:8080/save/book</i></p>
<p>Body: {
          <b>"id": XX</b>
	        "name":"Kotlin Book",
	        "category":"Programming",
	        "amount": 4
          }</p>     
          
<h2>Delete</h2>
<p>GET <i>http://localhost:8080/delete/book?id=XX</i></p>




