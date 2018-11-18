package com.example.hello
import java.util._
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{ PathVariable, RequestMapping }

@SpringBootApplication
@ComponentScan
class Hello
object Hello{
  def main(args:Array[String]):Unit={
    SpringApplication.run(classOf[Hello],"")
  }
}
@Controller
class HelloController{
  @RequestMapping(Array("/hello/{username}"))
  def hello(@PathVariable username:String,model:Model)={
    model.addAttribute("text", username)
    "hello"
  }
}


// case class LargeInt(string:String){
//   def len=string.length()
//   def array=string.map(_.toInt-'0'.toInt)
//   def run(other:LargeInt,method:(Int,Int,Int)=>(Int,Int))={
//     val a1=array
//     val a2=other.array
//     val _short=if(a1.length>a2.length)a2 else a1
//     val _long=if(a1.length<=a2.length)a2 else a1
//     val short=new Array[Int](_long.length-_short.length+1).map(_ => 0)++_short
//     val long=_long.+:(0)
//     val pair=long.zip(short)
//     var lstInt=0
//     var change=true
//     LargeInt(pair.foldRight(Array[Int]()) { (elum: (Int, Int), lst: Array[Int]) =>
//       {
//         val (ans,next)=method(elum._1,elum._2,lstInt)
//         lstInt=next
//         lst.+:(ans)
//       } }.filter(a => {if(change && a==0)false else {change=false;true}}).mkString(""))
//   }
//   def +(other:LargeInt)=run(other,(a,b,lst)=>((a+b+lst)%10,(a+b+lst)/10))
//   def *(other:LargeInt)=run(other,(a,b,lst)=>((a*b+lst)%10,(a*b+lst)/10))
//   override def toString=string
// }
