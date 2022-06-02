//잘 시간을 토대로 깰 시간을 추천(RecommendupActivity에서 넘어옴)
package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityRecommendup2Binding
import java.util.*
import kotlin.properties.Delegates

class RecommendupActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityRecommendup2Binding
    lateinit var dbHelper: DBHelper
    lateinit var database: SQLiteDatabase
    private var hour by Delegates.notNull<Int>()
    private var minute by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRecommendup2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initlayout()
        addAlarm() // 현재 인자로 들어가는 hour, minute는 전 액티비티에서 알람 설정해놓은 시간,분
    }


    // 시간이랑 분 인자로 넣으면 그시간에 알림이 온다. 어플 꺼놔도 오도록 설계함.
    // 필요한 *액티비티*에 가져다가 그냥 쓰면 됨.
    fun addAlarm(){
        var alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, Alarm::class.java)
        var pIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 6) // 지금 hour -> 전 액티비티 Timer에서 설정한 시간
        cal.set(Calendar.MINUTE, 8) // 지금 hour -> 전 액티비티 Timer에서 설정한 분
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pIntent)
    }


    private fun initlayout() {
        binding.buttonPrev.setOnClickListener {
            var intent = Intent(this, RecommendupActivity::class.java)
            startActivity(intent)
        }

        dbHelper = DBHelper(this, "dysw.db", null, 1)
        val sleepTime = dbHelper.getTimeFallSleep()
        Log.d("sleeptime", sleepTime.toString())

        var hour1 = intent.getIntExtra("hour", 0)
        var minute1 = intent.getIntExtra("minute", 0) + sleepTime//평균 잠드는데 까지 걸리는 시간 더해주면 됨.

        while(minute1>59){
            if(minute1>59) {
                hour1 += 1
                minute1 -= 60
            }
            if(hour1>23) {
                hour1 -= 24
            }
        }


        val timeString = hour1.toString() + "시 " + 1.toString() + "분"

        binding.timeTextView1.text = timeString

        var hour2 = hour1+1
        var minute2 = minute1+30

        while(minute2>59){
            if(minute2>59) {
                hour2 += 1
                minute2 -= 60
            }
            if(hour2>23) {
                hour2 -= 24
            }
        }
        val timeString2 = hour2.toString() + "시" + minute2.toString() + "분"
        binding.timeTextView2.text = timeString2

        var hour3 = hour2+1
        var minute3 = minute2+30

        while(minute3>59){
            if(minute3>59) {
                hour3 += 1
                minute3 -= 60
            }
            if(hour3>23) {
                hour3 -= 24
            }
        }
        val timeString3 = hour3.toString() + "시" + minute3.toString() + "분"
        binding.timeTextView3.text = timeString3

        var hour4 = hour3+1
        var minute4 = minute3+30

        while(minute4>59){
            if(minute4>59) {
                hour4 += 1
                minute4 -= 60
            }
            if(hour4>23) {
                hour4 -= 24
            }
        }
        val timeString4 = hour4.toString() + "시" + minute4.toString() + "분"
        binding.timeTextView4.text = timeString4

        var hour5 = hour4+1
        var minute5 = minute4+30

        while(minute5>59){
            if(minute5>59) {
                hour5 += 1
                minute5 -= 60
            }
            if(hour5>23) {
                hour5 -= 24
            }
        }
        val timeString5 = hour5.toString() + "시" + minute5.toString() + "분"
        binding.timeTextView5.text = timeString5

    }


}