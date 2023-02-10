package com.example.calculatormastertutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_adapter_calculate.*
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var tambahData: Button
    private lateinit var soal: TextView
    private lateinit var jawaban: TextView
    private lateinit var recyclerView : RecyclerView
    private lateinit var recyclerAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soal = findViewById(R.id.workingsTV)
        jawaban = findViewById(R.id.outputTV)
        tambahData = findViewById(R.id.btnSimpan)
        recyclerView = findViewById(R.id.listData)

        val data = ArrayList<DataCalculate>()
        viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerAdapter =  AdapterCalculate(data)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = viewManager

        tambahData.setOnClickListener{
            val soal = soal.text.toString()
            val jawaban = jawaban.text.toString()

            val dataSiswa = DataCalculate(soal,jawaban)
            data.add(dataSiswa)
            recyclerAdapter.notifyDataSetChanged()
        }

        btnClear.setOnClickListener {
            workingsTV.text = ""
            outputTV.text = ""
        }

        btn0.setOnClickListener {
            workingsTV.text = addToInputText("0")
        }

        btn1.setOnClickListener {
            workingsTV.text = addToInputText("1")
        }

        btn2.setOnClickListener {
            workingsTV.text = addToInputText("2")
        }

        btn3.setOnClickListener {
            workingsTV.text = addToInputText("3")
        }

        btn4.setOnClickListener {
            workingsTV.text = addToInputText("4")
        }

        btn5.setOnClickListener {
            workingsTV.text = addToInputText("5")
        }

        btn6.setOnClickListener {
            workingsTV.text = addToInputText("6")
        }

        btn7.setOnClickListener {
            workingsTV.text = addToInputText("7")
        }

        btn8.setOnClickListener {
            workingsTV.text = addToInputText("8")
        }

        btn9.setOnClickListener {
            workingsTV.text = addToInputText("9")
        }

        btnTambah.setOnClickListener {
            workingsTV.text = addToInputText("+")
        }

        btnKurang.setOnClickListener {
            workingsTV.text = addToInputText("-")
        }

        btnKali.setOnClickListener {
            workingsTV.text = addToInputText("×")
        }

        btnBagi.setOnClickListener {
            workingsTV.text = addToInputText("÷")
        }

        btnEquals.setOnClickListener {
            showResult()
        }
    }

    private fun addToInputText(buttonValue : String): String{
        return "${workingsTV.text}$buttonValue"
    }

    private fun getInputExpression(): String{
        var expression = workingsTV.text.replace(Regex("÷"),"/")
        expression = expression.replace(Regex("×"),"*")
        return expression
    }

    private fun showResult(){
        try{
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()){
                //show error message
                outputTV.text = "Error"
                outputTV.setTextColor(ContextCompat.getColor(this,R.color.red))
            } else {
                //show result
                outputTV.text = DecimalFormat("= 0.######").format(result).toString()
                outputTV.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        } catch (e: Exception){
            outputTV.text = "Error"
            outputTV.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }
}