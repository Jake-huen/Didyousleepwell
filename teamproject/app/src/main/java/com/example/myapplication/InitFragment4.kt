package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.databinding.ActivityInitBinding
import com.example.myapplication.databinding.FragmentInit4Binding
import org.w3c.dom.Text

class InitFragment4 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_init4, container, false)
        val title = view.findViewById<TextView>(R.id.title4)
        val startBtn = view.findViewById<Button>(R.id.startButton)

//      시작하기 버튼 작업 TODO -> 데이터베이스 생성 후 테이블 생성 작업
        startBtn.setOnClickListener {
//           인텐트 작업
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
    }
}