//잘 시간을 토대로 깰 시간을 추천(RecommendupActivity에서 넘어옴)
package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.databinding.ActivityRecommendup2Binding
import java.util.*
import kotlin.properties.Delegates

class RecommendupActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityRecommendup2Binding
    lateinit var database: SQLiteDatabase
    private var hour by Delegates.notNull<Int>()
    private var minute by Delegates.notNull<Int>()
    private var dbHelper = DBHelper(this, "dysw.db", null, 1)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRecommendup2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initlayout()
        addAlarm() // 현재 인자로 들어가는 hour, minute는 전 액티비티에서 알람 설정해놓은 시간,분
    }


    // 시간이랑 분 인자로 넣으면 그시간에 알림이 온다. 어플 꺼놔도 오도록 설계함.
    // 필요한 *액티비티*에 가져다가 그냥 쓰면 됨.
    @RequiresApi(Build.VERSION_CODES.M)
    fun addAlarm(){
        var alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, Alarm::class.java)
        var pIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour) // 지금 hour -> 전 액티비티 Timer에서 설정한 시간
        cal.set(Calendar.MINUTE, minute) // 지금 hour -> 전 액티비티 Timer에서 설정한 분
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pIntent)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initlayout() {

        val sleepTime = dbHelper.getTimeFallSleep()
        Log.d("sleeptime", sleepTime.toString())

        hour = intent.getIntExtra("hour", 0)
        minute = intent.getIntExtra("minute", 0)

        var hour1 = hour
        var minute1 = minute
        var cycle = 0

        val age = dbHelper.getAge()
        if(age in 0..1) cycle = 9
        else if(age in 2..3) cycle = 8
        else if(age in 4..6) cycle = 7
        else if(age in 7..18) cycle = 6
        else cycle = 5

        val goodSleepTime = GoodSleepTime()
        val calwakeuptime = goodSleepTime.wakeTime(hour1, minute1, 0, cycle, sleepTime)


        val timeString3 = calwakeuptime.hour.toString() + "시 " + calwakeuptime.minute.toString() + "분"
        binding.timeTextView3.text = timeString3 //사이클에 합당한 시간

        val cal1 = calwakeuptime.plusMinutes(180)
        val timeString1 = cal1.hour.toString() + "시 " + cal1.minute.toString() + "분"
        binding.timeTextView1.text = timeString1

        val cal2 = calwakeuptime.plusMinutes(90)
        val timeString2 = cal2.hour.toString() + "시 " + cal2.minute.toString() + "분"
        binding.timeTextView2.text = timeString2

        val cal4 = calwakeuptime.minusMinutes(90)
        val timeString4 = cal4.hour.toString() + "시 " + cal4.minute.toString() + "분"
        binding.timeTextView4.text = timeString4

        val cal5 = calwakeuptime.minusMinutes(180)
        val timeString5 = cal5.hour.toString() + "시 " + cal5.minute.toString() + "분"
        binding.timeTextView5.text = timeString5

    }


}