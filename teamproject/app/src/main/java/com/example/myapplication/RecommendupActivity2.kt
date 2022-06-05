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
import android.widget.TextView
import android.widget.Toast
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
        addAlarmDown(hour, minute) // 깨어날 시간에 투두리스트 알람을 준다.
        alarmBtnSet() // 자야 할 시간에 주무십시오 알람을 준다.
    }


    // 시간이랑 분 인자로 넣으면 그시간에 알림이 온다. 어플 꺼놔도 오도록 설계함.
    // 필요한 *액티비티*에 가져다가 그냥 쓰면 됨.
    @RequiresApi(Build.VERSION_CODES.M)
    fun addAlarmUp(hour: Int, minute: Int){
        var alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, Alarm::class.java)
        var pIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val cal = Calendar.getInstance()

        cal.set(Calendar.HOUR_OF_DAY, hour) // hour 인자 설정
        cal.set(Calendar.MINUTE, minute) // minute 인자 설정
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)

        Toast.makeText(this, hour.toString() + " 시 " + minute.toString() + " 분 "+ "알림이 추가되었습니다.", Toast.LENGTH_SHORT).show()
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pIntent)
    }


    fun addAlarmDown(hour: Int, minute: Int){
        var alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, Alarm2::class.java)
        var pIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val cal = Calendar.getInstance()

        cal.set(Calendar.HOUR_OF_DAY, hour) // hour 인자 설정
        cal.set(Calendar.MINUTE, minute) // minute 인자 설정
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)

        Toast.makeText(this, hour.toString() + " 시 " + minute.toString() + " 분 "+ "에 자야 한다고 알려드릴게요.", Toast.LENGTH_SHORT).show()
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pIntent)
    }

    // 버튼 누르면 그 시간에 맞춰진 알람이 추가됨.
    private fun alarmBtnSet(){

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        binding.apply {
            timeTextView1.setOnClickListener {
                addAlarmUp(splitHour(timeTextView1), splitMinute(timeTextView1))
                startActivity(intent)
            }
            timeTextView2.setOnClickListener {
                addAlarmUp(splitHour(timeTextView2), splitMinute(timeTextView2))
                startActivity(intent)
            }
            timeTextView3.setOnClickListener {
                addAlarmUp(splitHour(timeTextView3), splitMinute(timeTextView3))
                startActivity(intent)
            }
            timeTextView4.setOnClickListener {
                addAlarmUp(splitHour(timeTextView4), splitMinute(timeTextView4))
                startActivity(intent)
            }
            timeTextView5.setOnClickListener {
                addAlarmUp(splitHour(timeTextView5), splitMinute(timeTextView5))
                startActivity(intent)
            }
        }
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


        val timeString3 = getTime(calwakeuptime.hour) + "시 " + getTime(calwakeuptime.minute) + "분"
        binding.timeTextView3.text = timeString3 //사이클에 합당한 시간

        val cal1 = calwakeuptime.plusMinutes(180)
        val timeString1 = getTime(cal1.hour) + "시 " + getTime(cal1.minute) + "분"
        binding.timeTextView1.text = timeString1

        val cal2 = calwakeuptime.plusMinutes(90)
        val timeString2 = getTime(cal2.hour) + "시 " + getTime(cal2.minute) + "분"
        binding.timeTextView2.text = timeString2

        val cal4 = calwakeuptime.minusMinutes(90)
        val timeString4 = getTime(cal4.hour) + "시 " + getTime(cal4.minute) + "분"
        binding.timeTextView4.text = timeString4

        val cal5 = calwakeuptime.minusMinutes(180)
        val timeString5 = getTime(cal5.hour) + "시 " + getTime(cal5.minute) + "분"
        binding.timeTextView5.text = timeString5

    }
    fun getTime(num:Int):String{
        if(num>=0 && num<10){
            return "0"+ num.toString();
        }
        else{
            return num.toString();
        }
    }

    private fun splitHour(textview: TextView): Int {
        return textview.text.toString().split("시")[0].toInt() }

    private fun splitMinute(textview: TextView): Int {
        return textview.text.toString().split("시")[1]
            .replace("분", "")
            .replace(" ", "").toInt() }

}