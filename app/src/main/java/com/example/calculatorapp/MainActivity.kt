package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastnum:Boolean=false
    var lastdot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun digitpressed(view: View) {
        screencal.append((view as Button).text)
        lastnum=true
        // Toast.makeText(this,"digit pressed",Toast.LENGTH_SHORT).show()
    }

    fun clearpressed(view: View) {
        screencal.text=""
        lastnum=false
        lastdot=false
    }

    fun decimalpressed(view: View) {
        if(lastnum==true && lastdot==false){
            screencal.append(".")
            lastnum=false
            lastdot=true
        }
    }

    fun operatorpressed(view: View) {
        if((screencal.text==""||lastnum )&& !isoperatoradded(screencal.text.toString())){
            if(((view as Button).text)=="1/X"){
                val j=(screencal.text).toString()
                screencal.text= "1/"
                screencal.append(j)
                lastnum=true
                lastdot=false
            }
            else if(((view as Button).text)=="^2"){
                screencal.append((view as Button).text)
                lastnum=true
                lastdot=false
            }
           else {
                screencal.append((view as Button).text)
                lastnum=false
                lastdot=false
            }

        }
    }
    fun isoperatoradded(value:String):Boolean
    {
        return if(value.startsWith("-")){
            false
        }
        else {
            return value.contains("/") || value.contains("^") || value.contains("^2") || value.contains("1/X")
        }

    }

    fun equalpressed(view: View) {
        if(lastnum){
            var expr=screencal.text.toString()
            var em=""
            try {
                if(expr.startsWith("-")){
                    em="-"
                    expr=expr.substring(1)
                }
                if(expr.contains("-")){
                    val splitexpr=expr.split("-")
                    var one=splitexpr[0]
                    var two=splitexpr[1]
                    if(em=="-"){
                        one=em+one
                    }
                    screencal.text=(one.toDouble()-two.toDouble()).toString()
                }
                else if(expr.contains("^")){
                    val splitexpr=expr.split("^")
                    var one=splitexpr[0]
                    var two=splitexpr[1]
                    if(em=="-"){
                        one=em+one
                    }
                    screencal.text=(Math.pow(one.toDouble(),two.toDouble())).toString()
                }
                else if(expr.contains("/")){
                    val splitexpr=expr.split("/")
                    var one=splitexpr[0]
                    var two=splitexpr[1]
                    if(em=="-"){
                        one=em+one
                    }
                    screencal.text=(one.toDouble()/two.toDouble()).toString()
                }
              /*  else if(expr.contains("/")){
                    val splitexpr=expr.split("/")
                    var one=splitexpr[0]
                    var two=splitexpr[1]
                    if(em=="-"){
                        one=em+one
                    }
                    screencal.text=(one.toDouble()/two.toDouble()).toString()
                }
              */
            }
            catch (e:ArithmeticException){
                e.printStackTrace()
                Toast.makeText(this, "undefined", Toast.LENGTH_SHORT).show()
            }
        }
    }
}