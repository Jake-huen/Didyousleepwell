package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class InitFragment3 : Fragment() {

    /*
        Intent로 Fregment2에서 3으로 나이 데이터 전달 받음.
*       TODO -> Fragment 간 데이터 정보 전달  Fregment3 -> 4  : 나이, 잠들기 까지 걸리는 시간 전달.
*
* */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_init3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }
}