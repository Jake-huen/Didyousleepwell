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
    //적정 시간 - 수면 시간의 절댓값으로 수면 등급 나누는 변수
    val check=5
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
        //상태 체크해서 이미지 띄우고, 각 이미지 별로 평가 한줄~

        initData()

        when(check){
            1->{
                binding!!.imageView4.setImageResource(imglist[0])
                binding!!.imageView4.setOnClickListener {
                    Toast.makeText(context, "너무 잘 잤어요!!", Toast.LENGTH_SHORT).show()
                }

            }
            2->{
                binding!!.imageView4.setImageResource(imglist[1])
                binding!!.imageView4.setOnClickListener {
                    Toast.makeText(context, "잘 잤네요~", Toast.LENGTH_SHORT).show()
                }

            }
            3->{
                binding!!.imageView4.setImageResource(imglist[2])
                binding!!.imageView4.setOnClickListener {
                    Toast.makeText(context, "잤네요..", Toast.LENGTH_SHORT).show()
                }

            }
            4->{
                binding!!.imageView4.setImageResource(imglist[3])
                binding!!.imageView4.setOnClickListener {
                    Toast.makeText(context, "잤어요..?", Toast.LENGTH_SHORT).show()
                }

            }
            5->{
                binding!!.imageView4.setImageResource(imglist[4])
                binding!!.imageView4.setOnClickListener {
                    Toast.makeText(context, "XX", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }
    private fun initData() {
        val dbHelper = DBHelper(context, "dysw.db", null, 1)
        //취침 시간
        val listdown = dbHelper.getdown(date)
        var listdownH = listdown.split(":")[0].toInt()
        var listdownM = listdown.split(":")[1].toInt()
        binding!!.textView1.text = getTime(listdownH) + "시 "+ getTime(listdownM)+"분"

        //기상 시간
        val listup = dbHelper.getup(date)
        var listupH = listup.split(":")[0].toInt()
        var listupM = listup.split(":")[1].toInt()
        binding!!.textView2.text = getTime(listupH) + "시 "+ getTime(listupM)+"분"

        //수면 시간
        binding!!.textView3.text = totalTime(listdown, listup)

        //적정 시간

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

        return totalH.toString() + "시간 " + totalM.toString()+"분"
    }
}