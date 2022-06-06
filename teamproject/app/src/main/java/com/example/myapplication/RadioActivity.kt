package com.example.myapplication

import android.media.MediaPlayer
import android.os.*
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication.databinding.ActivityRadioBinding
import java.time.LocalDateTime
import java.util.*
import kotlin.concurrent.thread
import kotlin.system.exitProcess


class RadioActivity : AppCompatActivity() {

    lateinit var binding: ActivityRadioBinding
    lateinit var mediaPlayer: MediaPlayer
    private var dbHelper = DBHelper(this, "dysw.db", null, 1)
    private var milSecond: Long = 500000

    private var mTimer: Timer? = null
    private var timeText: TextView? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val flag = intent.getIntExtra("알람", 0)
        if(flag == 1){
            binding.alarmLayout.setBackgroundResource(R.drawable.green_forest)
            binding.radioTimeText.text = "잘..잤어요?"
        }


        // 기상송 오른쪽 R.raw.song 바꿔주세요 : 현재는 가지고 있던 캐롤송으로 붙여놨습니다.
        mediaPlayer = MediaPlayer.create(this, R.raw.song)

        timeText = binding.radioTimeText
        timeText!!.text = alarmTimer().toString()
        updateRemainTime(alarmTimer())
        initLayout()

        thread(start = true){

            while(!isStopCondition()){

                Thread.sleep(1000)
                if(milSecond<2000){
                    runOnUiThread{
                        binding.radioTimeText.text = "잘..잤어요?"
                        mediaPlayer.start()
                    }
                }
                else{
                    runOnUiThread{
                        milSecond = updateRemainTime(alarmTimer())
                    }
                }
                // 5분 남기고
                if(milSecond<320000){
                    runOnUiThread{
                        binding.alarmLayout.setBackgroundResource(R.drawable.green_forest)
                    }
                }
            }

        }
    }

    private fun updateRemainTime(remainMillis: Long):Long {
        val remainSeconds = remainMillis / 1000
        var second = remainSeconds
        val hour = remainSeconds/3600
        second = (second - (hour*3600)).toInt().toLong()
        val minute = second/60

        binding.radioTimeText.text = "%02d".format(hour) + " : " + "%02d".format(minute) + " : " + "%02d".format(remainSeconds % 60) + " 후에\n알람이 울립니다."
        return remainMillis
    }

    private fun isStopCondition(): Boolean {
        return milSecond<0
    }

    override fun onDestroy() {
        mediaPlayer.pause()
        mTimer?.cancel()
        super.onDestroy()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun alarmTimer(): Long {
        val list = dbHelper.selectSleepTime()
        val dateTime = LocalDateTime.now()

        // up 시간
        val date = list[list.size - 1].date
        val year = date.split("-")[0]
        var month = date.split("-")[1]
        if (month[0].toString() == "0") {
            month = month.replace("0", "")
        }
        var day = date.split("-")[2]
        if (day[0].toString() == "0") {
            day = day.replace("0", "")
        }
//        // 로컬 시간이 새벽 6시 이전 일때? 잘모르겠음 이게뭔지.. 예외처리 하나 필요한데 기준을 모르겠어서 일단 주석
//        if(dateTime.hour < 6){
//
//        }
//        val downtime = list[list.size-1].down
//        val downhour = downtime.split(":")

        val time = list[list.size - 1].up
        val hour = time.split(":")[0]
        val minute = time.split(":")[1]



        // 현재 로컬시간.
        val beginDay = Calendar.getInstance().apply {
            set(Calendar.YEAR, dateTime.year)
            set(Calendar.MONTH, dateTime.monthValue)
            set(Calendar.DAY_OF_MONTH, dateTime.dayOfMonth)
            set(Calendar.HOUR_OF_DAY, dateTime.hour)
            set(Calendar.MINUTE, dateTime.minute)
            set(Calendar.SECOND, dateTime.second)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        // up 시간
        val lastDay = Calendar.getInstance().apply {
            set(Calendar.YEAR, year.toInt())
            set(Calendar.MONTH, month.toInt())
            set(Calendar.DAY_OF_MONTH, day.toInt()+1)
            set(Calendar.HOUR_OF_DAY, hour.toInt())
            set(Calendar.MINUTE, minute.toInt())
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        return lastDay - beginDay

    }

    private fun initLayout(){

        binding.radioBtn.setOnClickListener {

//            앱 메인으로 이동.
//            val intent = Intent(this, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//            startActivity(intent)

//            앱 종료해버리기
            ActivityCompat.finishAffinity(this)
            exitProcess(0)

        }

    }


}