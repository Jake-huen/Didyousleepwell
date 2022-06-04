package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.databinding.FragmentAnalysisBinding

class AnalysisFragment : Fragment() {
    var binding:FragmentAnalysisBinding?=null
    val imglist = arrayListOf<Int>(R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five)
    //적정 시간 - 수면 시간의 절댓값으로 수면 등급 나누는 변수
    val check=5

    private lateinit var date: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 캘린더에서 데이트 값 가져옴      ex) date 값은 2022-6-1
        date = (context as DateActivity).getDate()
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
//                    Toast.makeText(context, date, Toast.LENGTH_SHORT).show()
                }

            }
        }

    }
}