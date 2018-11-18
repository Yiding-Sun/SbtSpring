package com.example.mongo

import javax.persistence.Id
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.{ Criteria, Query }
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import scala.beans.BeanProperty



trait Repository{
  def read(username:String):Person
  def save(person:Person):Person
}

@Document
case class Person(@BeanProperty username:String, @BeanProperty password:String){
  @Id @BeanProperty val id:String=null
}

@Component
class DataRepoistory(personRepoistory:PersonRepoistory) extends Repository{
  def read(username:String)=personRepoistory.findPersonByUsername(username)
  def save(person:Person)=personRepoistory.save(person)
}
trait PersonRepoistory extends MongoRepository[Person,String]{
  def findPersonByUsername(username:String):Person
}


@Component
class OperationRepsoistory extends Repository{
  @Autowired val mongo:MongoOperations=null
  def read(username:String)=mongo.find(Query.query(Criteria.where("username").is(username)),classOf[Person],"person_op").get(0)
  def save(person:Person)={
    mongo.save(person, "person_op")
    person
  }
}
