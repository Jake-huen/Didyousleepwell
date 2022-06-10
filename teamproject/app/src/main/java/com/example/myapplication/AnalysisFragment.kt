package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.databinding.FragmentAnalysisBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AnalysisFragment : Fragment() {
    var binding:FragmentAnalysisBinding?=null
    val imglist = arrayListOf<Int>(R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five)
    private lateinit var date: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 캘린더에서 데이트 값 가져옴      ex) date 값은 2022-06-01
        date = (context as DateActivity).getDate()
        if(date == "null"){
//            date = LocalDate.now().toS
            var now = LocalDate.now()
            date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAnalysisBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

    }
    private fun initData() {
        val dbHelper = DBHelper(context, "dysw.db", null, 1)
        val listdown = dbHelper.getdown(date)
        val listup = dbHelper.getup(date)
        if(listdown=="데이터 없음"||listup=="데이터 없음"){
            binding!!.textView1.text = listdown
            binding!!.textView2.text = listup
            binding!!.textView3.text = "데이터 없음"
        }
        else {
            //취침 시간
            var listdownH = listdown.split(":")[0].toInt()
            var listdownM = listdown.split(":")[1].toInt()
            binding!!.textView1.text = getTime(listdownH) + "시 " + getTime(listdownM) + "분"

            //기상 시간
            var listupH = listup.split(":")[0].toInt()
            var listupM = listup.split(":")[1].toInt()
            binding!!.textView2.text = getTime(listupH) + "시 " + getTime(listupM) + "분"

            //수면 시간
            binding!!.textView3.text = totalTime(listdown, listup).split(":")[0] + "시간 "+
                    totalTime(listdown, listup).split(":")[1]+"분"
        }
        //적정 시간
        var age = dbHelper.getAge()
        var goodTimeNum:String = "00:00"
        var goodTime:String = "00시간 00분"
        when(age) {
            in 0 .. 2 -> { // 0세~ 1세 8사이클(12시간) ~  10사이클(15시간)
                goodTimeNum = "12:00"
                goodTime = "12시간 00분"
            }
            in 2 .. 4 -> { // 2세~ 3세 7사이클(10시간30분) ~  9사이클(13시간 30분)
                goodTimeNum = "10:30"
                goodTime = "10시간 30분"
            }
            in 4 .. 7 -> { // 4세~ 6세 6사이클(9시간) ~  8사이클(12시간)
                goodTimeNum = "09:00"
                goodTime = "9시간 00분"
            }
            in 7 .. 15 -> { // 7세~ 14세 6사이클(9시간) ~  7사이클(10시간 30분)
                goodTimeNum = "09:00"
                goodTime = "9시간 00분"
            }
            in 15 .. 19 -> { // 15세~ 18세 6사이클(9시간) ~  7사이클(10시간 30분)
                goodTimeNum = "09:00"
                goodTime = "9시간 00분"
            }
            in 19 .. 66 -> { // 19세~ 65세 5사이클(7시간 30분) ~  6사이클(9시간)
                goodTimeNum = "07:30"
                goodTime = "7시간 30분"
            }
            in 66 .. 199 -> { // other  5사이클(7시간 30분)
                goodTimeNum = "07:30"
                goodTime = "7시간 30분"
            }
        }
        binding!!.textView4.text = goodTime

        //상태
        if(listdown=="데이터 없음"||listup=="데이터 없음"){
            binding!!.imageView4.setImageResource(imglist[2])
            binding!!.imageView4.setOnClickListener {
                Toast.makeText(context, "데이터 없음", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            //이미지 띄우기
            var check = status(totalTime(listdown,listup), goodTimeNum)

            when (check) {
                in 0 .. 1 -> {//30분 이내
                    binding!!.imageView4.setImageResource(imglist[0])
                    binding!!.imageView4.setOnClickListener {
                        Toast.makeText(context, "너무 잘 잤어요!!", Toast.LENGTH_SHORT).show()
                    }

                }
                in 1 .. 3 -> {//30분~1시간 30분
                    binding!!.imageView4.setImageResource(imglist[1])
                    binding!!.imageView4.setOnClickListener {
                        Toast.makeText(context, "잘 잤네요~", Toast.LENGTH_SHORT).show()
                    }

                }
                in 3 .. 6 -> {//1시간 30분~3시간
                    binding!!.imageView4.setImageResource(imglist[3])
                    binding!!.imageView4.setOnClickListener {
                        Toast.makeText(context, "잤네요..", Toast.LENGTH_SHORT).show()
                    }

                }
                in 6 .. 24 -> {//3시간~
                    binding!!.imageView4.setImageResource(imglist[4])
                    binding!!.imageView4.setOnClickListener {
                        Toast.makeText(context, "잤어요..?", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }
    fun getTime(num:Int):String{
        if(num>=0 && num<10){
            return "0"+ num.toString();
        }
        else{
            return num.toString();
        }
    }
    fun totalTime(num1:String, num2:String):String{
        var listdownH = num1.split(":")[0].toInt()
        var listdownM = num1.split(":")[1].toInt()
        var listupH = num2.split(":")[0].toInt()
        var listupM = num2.split(":")[1].toInt()
        var totalH = 0
        var totalM = 0
        if(listdownH < listupH){
            if(listdownM < listupM){//ex) 00:00 ~ 08:10
                totalH = listupH-listdownH
                totalM = listupM-listdownM
            }else{//ex) 00:10 ~ 08:00
                totalH = (listupH - listdownH) - 1
                totalM = 60 - (listdownM - listupM)
            }
        }else{
            if(listdownM < listupM){//ex) 20:00 ~ 06:10
                totalH = (24 - listdownH) + listupH
                totalM = listupM-listdownM
            }else{//ex) 20:10 ~ 06:00
                totalH = (24 - listdownH) + listupH - 1
                totalM = 60 - (listdownM - listupM)
            }
        }

        return totalH.toString() + ":" + totalM.toString()
    }
    fun status(num1: String, num2: String):Int{
        var a = (num1.split(":")[0].toInt() * 60) + num1.split(":")[1].toInt()
        var b = (num2.split(":")[0].toInt() * 60) + num2.split(":")[1].toInt()
        return Math.abs(a-b) / 30
    }
}