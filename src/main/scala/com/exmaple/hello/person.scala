package com.example.hello
import java.util.Date
import javax.persistence.{ Column, Entity, GeneratedValue, GenerationType, Id }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import scala.beans.BeanProperty
@Entity
case class Person(
  @Id
    @Column(unique=true)
    @BeanProperty username:String,
  @BeanProperty pass:String,
  @BeanProperty birthday:Date,
  @BeanProperty enable:Boolean) extends AbstractPersistable[String](){
}

trait PersonJpa extends CrudRepository[Person,String]

@Component
class PersonRespoistory(@Autowired personJpa:PersonJpa) extends Respository{
  override def select(id:String)=personJpa.findById(id).orElse(Person("nil","nil",new java.util.Date(),false)).pass
  override def insert(username:String,pass:String)=personJpa.save(Person(username,pass,null,true))
}
