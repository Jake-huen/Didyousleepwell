package com.example.myapplication

import android.util.Log
import java.time.LocalTime

class GoodSleepTime() {

    private lateinit var time: LocalTime


    /*
    사용법 :
        사용하고자 하는 클래스에서 객체를 생성해주세요.
        객체에서 매서드를 불러와서 사용해 주시면 됩니다. 매서드에 대한 설명은 아래에 주석으로 자세하게 설명해 놓았습니다.
        잠자야 할 시간과 일어나야 할 시간을 알려주는 매서드들이며 인자값에 유의하여 사용하여 주시면 됩니다.

        주의사항: 한 객체당 한번의 시간 설정을 부탁드립니다.
        ex) 5개의 깰 시간이 필요하다 -> 5개의 객체를 각각 생성하여 각각 설정 해주셔야 합니다.
        나중에 시간 재사용에 문제가 생길 수 있어서 그렇습니다.



        +Call requires API level 26 (current min is 21): java.time.LocalTime#minusMinutes 라는 밑에 빨간 오류는 무시해주셔도 됩니다.
        api 레벨 26 이하의 안드로이드버전에서는 작동을 안한다는 오류입니다. 코드 실행에 문제가 없으니 그냥 실행 해 주시면 됩니다.
     */


    /*
     잠자야 할 시간을 알려주는 메서드
     인자로 (일어 나고 싶은 시, 분, 초 , 원하는 사이클 주기, 누워서 잠에 들기까지 몇분 걸리는지 입력)
     return to type(LocalTime)
    */
    fun sleepTime(hour:Int, minute:Int, second:Int, cycle: Int, beforeSleepTime: Int) : LocalTime {

        time = LocalTime.of(hour, minute, second)
        time = time.minusMinutes(beforeSleepTime.toLong())
        for(i in 1..cycle) minusCycle()
        return time
    }



    /*
     현재 시간에 잠든 것을 기준으로 일어나야 할 시간을 알려주는 메서드
     인자로 (원하는 사이클 주기, 누워서 잠에 들기까지 몇분 걸리는지 입력)
     return to type(LocalTime)
    */
    fun wakeTime(cycle: Int, beforeSleepTime: Int) : LocalTime {

        time = LocalTime.now()
        time = time.plusMinutes(beforeSleepTime.toLong())
        for(i in 1..cycle) plusCycle()
        return time
    }

    /*
     일어나야 할 시간을 알려주는 메서드
     인자로 (자려고 하는 시, 분, 초 , 원하는 사이클 주기, 누워서 잠에 들기까지 몇분 걸리는지 입력)
     return to type(LocalTime)
    */
    fun wakeTime(hour:Int, minute:Int, second:Int, cycle: Int, beforeSleepTime: Int) : LocalTime {

        time = LocalTime.of(hour, minute, second)
        time = time.plusMinutes(beforeSleepTime.toLong())
        for(i in 1..cycle) plusCycle()
        return time
    }


    /*
    수면 패턴의 주기를 알려주는 메서드
    인자로 (나이, 더 자고 싶은 사이클)을 입력 받음.
    더자고 싶은 사이클은 선택해서 메서드 사용자가 조절해서 사용 할 것, : 1사이클 당 1시간 30분이라고 생각할 것.
    return to type(Int) 나이에 따른 평균적인 수면 램 사이클(Default), + 더 자고 싶은 사이클
     */
    fun ageCycle(age: Int, plusSleepCycle: Int = 0): Int{

        when(age) {
            in 0 .. 2 -> { // 0세~ 1세 8사이클(12시간) ~  10사이클(15시간)
                return 8 + plusSleepCycle
            }
            in 2 .. 4 -> { // 2세~ 3세 7사이클(10시간30분) ~  9사이클(13시간 30분)
                return 7 + plusSleepCycle
            }
            in 4 .. 7 -> { // 4세~ 6세 6사이클(9시간) ~  8사이클(12시간)
                return 6 + plusSleepCycle
            }
            in 7 .. 15 -> { // 7세~ 14세 6사이클(9시간) ~  7사이클(10시간 30분)
                return 6 + plusSleepCycle
            }
            in 15 .. 19 -> { // 15세~ 18세 6사이클(9시간) ~  7사이클(10시간 30분)
                return 6 + plusSleepCycle
            }
            in 19 .. 66 -> { // 19세~ 65세 5사이클(7시간 30분) ~  6사이클(9시간)
                return 5 + plusSleepCycle
            }
            in 66 .. 199 -> { // other  5사이클(7시간 30분)
                return 5 + plusSleepCycle
            }
        }
        return 0
    }

    private fun plusCycle(){
        time = time.plusHours(1)
        time = time.plusMinutes(30)
    }

    private fun minusCycle(){
        time = time.minusHours(1)
        time = time.minusMinutes(30)
    }

    fun getTimeHour() : Int {
        return time.hour
    }

    fun getTimeMinute() : Int {
        return time.minute
    }

    fun getTimeSecond() : Int {
        return time.second
    }


    /*
    print ->    ??:??:??
    현 객체의 시,분,초
     */
    fun getTimeToStirng() : String{
        return getTimeHour().toString()+":"+getTimeMinute().toString()+":"+getTimeSecond().toString()
    }

}
