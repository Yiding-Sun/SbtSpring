package com.example.mongo

import com.mongodb.client.MongoCollection
import com.mongodb.{ DBObject, Mongo, MongoClient }
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{ EnableAutoConfiguration, SpringBootApplication }
import org.springframework.context.annotation.{ Bean, ComponentScan, Configuration }
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.query.{ Criteria, Query }
import org.springframework.data.mongodb.core.{ MongoClientFactoryBean, MongoOperations }
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{ EnableWebSecurity, WebSecurityConfigurerAdapter }
import org.springframework.web.bind.annotation.{ GetMapping, PathVariable, RestController }
import scala.beans.BeanProperty

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages=Array("com.example.mongo"))
@Configuration
@EnableMongoRepositories(basePackages=Array("com.example.mongo"))
class Application{
  @Bean def mongo()={
    val mongo=new MongoClientFactoryBean
    mongo.setHost("localhost")
    mongo
  }
}
object Application{
  def main(args:Array[String]):Unit=
    SpringApplication.run(classOf[Application],"")
}

@Configuration
@EnableWebSecurity
class SecureConfig extends WebSecurityConfigurerAdapter{
  val logger=org.slf4j.LoggerFactory.getLogger(getClass)
  override def configure(http:HttpSecurity)={
    http.authorizeRequests().anyRequest().permitAll()
  }
}

@RestController
class Controller{
  @Autowired val mongo:MongoOperations= null
  @GetMapping(Array("hello"))
  def hello="Hello World!"
  @GetMapping(Array("save/{user}/{pass}"))
  def save(@PathVariable user:String, @PathVariable pass:String)={
    mongo.save(Person(user,pass),"person")
  }
  @GetMapping(Array("read/{user}"))
  def read( @PathVariable user:String )={
    val person=mongo.find(Query.query(Criteria.where("username").is(user)), classOf[Person]).get(0)
    person.toString()
  }
}

@Document
case class Person(@BeanProperty username:String,password:String){
  @Id @BeanProperty val id:String=null
}


// class MongoService{
//   def main={
//     def getCollection()={
//       println("started")
//       val mongoClient=new MongoClient("localhost",27017)
//       val database=mongoClient.getDatabase("test")
//       // database.createCollection("person")
//       (database.getCollection("person"),mongoClient)
//     }
//     def createPerson(first:String,last:String)=
//       new Document("first-name",first)
//         .append("last-name", last)
//     def run(method:(MongoCollection[Document]) => Unit) = {
//       val (collection,client)=getCollection()
//       method(collection)
//       client.close
//     }
//     run((collection)=>{
//           // collection.insertOne(createPerson("test1", "last-test"))
//           // collection.insertOne(createPerson("first-1", "last-1"))
//           val cursor=collection.find().iterator()
//           while(cursor.hasNext()){
//             val document=cursor.next()
//             println(document.get("first-name", classOf[String]))
//           }
//         })

//   }
// }
