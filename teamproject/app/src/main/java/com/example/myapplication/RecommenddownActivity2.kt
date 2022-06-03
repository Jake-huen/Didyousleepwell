//깰 시간을 토대로 잘 시간을 추천(RecommenddownActivity에서 넘어옴)
package com.example.myapplication

import android.app.*
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityRecommenddown2Binding
import java.util.*
import kotlin.properties.Delegates
import android.app.PendingIntent


class RecommenddownActivity2 : AppCompatActivity() {
    lateinit var binding : ActivityRecommenddown2Binding
    private var dbHelper = DBHelper(this, "dysw.db", null, 1)
    private var hour by Delegates.notNull<Int>()
    private var minute by Delegates.notNull<Int>()
    lateinit var goodSleepTime:GoodSleepTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommenddown2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
        addAlarm() // 현재 인자로 들어가는 hour, minute는 전 액티비티에서 알람 설정해놓은 시간,분
    }




    // 시간이랑 분 인자로 넣으면 그시간에 알림이 온다. 어플 꺼놔도 오도록 설계함.
    // 필요한 *액티비티*에 가져다가 그냥 쓰면 됨.
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


    private fun initLayout() {
        binding.buttonPrev.setOnClickListener {
            var intent = Intent(this, RecommenddownActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
            startActivity(intent)
        }

        val age = dbHelper.getAge()
        //어짜피 한시간 반 고정이라 나이값은 딱히 필요없을듯..
        var sleepTime = dbHelper.getTimeFallSleep()





        hour = intent.getIntExtra("hour", 0)
        minute = intent.getIntExtra("minute", 0)

        var hour1 = hour
        var minute1 = minute

        hour1 -= 1
        minute1 -= 30

        while(minute1<0){
            if(minute1<0) {
                hour1 -= 1
                minute1 += 60
            }
            if(hour1<0) {
                hour1 += 24
            }
        }

        minute1 += sleepTime

        while(minute1>59){
            if(minute1>59) {
                hour1 += 1
                minute1 -= 60
            }
            if(hour1>23) {
                hour1 -= 24
            }
        }

        val timeString = hour.toString() + "시 " + minute.toString() + "분"

        binding.timeTextView5.text = timeString //마지막 줄 부터 출력

        var hour2 = hour-1
        var minute2 = minute-30

        while(minute2<0){
            if(minute2<0) {
                hour2 -= 1
                minute2 += 60
            }
            if(hour2<0) {
                hour2 += 24
            }
        }

        val timeString2 = hour2.toString() + "시" + minute2.toString() + "분"
        binding.timeTextView4.text = timeString2

        var hour3 = hour2-1
        var minute3 = minute2-30

        while(minute3<0){
            if(minute3<0) {
                hour3 -= 1
                minute3 += 60
            }
            if(hour3<0) {
                hour3 += 24
            }
        }
        val timeString3 = hour3.toString() + "시" + minute3.toString() + "분"
        binding.timeTextView3.text = timeString3

        var hour4 = hour3-1
        var minute4 = minute3-30

        while(minute4<0){
            if(minute4<0) {
                hour4 -= 1
                minute4 += 60
            }
            if(hour4<0) {
                hour4 += 24
            }
        }
        val timeString4 = hour4.toString() + "시" + minute4.toString() + "분"
        binding.timeTextView2.text = timeString4

        var hour5 = hour4-1
        var minute5 = minute4-30

        while(minute5<0){
            if(minute5<0) {
                hour5 -= 1
                minute5 += 60
            }
            if(hour5<0) {
                hour5 += 24
            }
        }
        val timeString5 = hour5.toString() + "시" + minute5.toString() + "분"
        binding.timeTextView1.text = timeString5
    }
}