package com.example.hello

import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.{ JdbcOperations, PreparedStatementCallback, RowMapper }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.{ Component, Controller }
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{ PathVariable, RequestMapping }

@Controller
class JdbcController{
  @Autowired
  val respository:Respository=null
  val logger=LoggerFactory.getLogger(getClass)
    @RequestMapping(Array("/insert/{username}/{password}"))
  def insert(@PathVariable username:String,@PathVariable password:String,model:Model)={
    model.addAttribute("text", respository.insert(username, password))
    "hello"
  }
    @RequestMapping(Array("/select/{id}"))
  def select(@PathVariable id:String,model:Model)={
    val text=respository.select(id)
    model.addAttribute("text",text)
    "hello"
  }
}

trait Respository{
  def insert(username:String,pass:String):Unit
  def select(id:String):String
}


// //@Component
// class JdbcRespository extends Respository{
//   @Autowired
//   val jdbc:JdbcOperations=null
//   override def insert(username:String,pass:String)=jdbc.update("INSERT INTO mytable (username,pass) VALUES (?,?)",username,pass)
//   override def select(id:Int)=jdbc.query("SELECT username FROM mytable WHERE id=?", (rs,rowNum) => rs.getString("username") ,id.asInstanceOf[Object]).get(0)
// }
