package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class Alarm2: BroadcastReceiver(){

    private val CHANNEL_ID = "testChannel02"   // Channel for notification
    private var notificationManager: NotificationManager? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent != null){
//            Toast.makeText(context, "알람", Toast.LENGTH_SHORT).show();    // AVD 확인용
//            Log.e("Alarm","알람입니다.");    // 로그 확인용
            createNotificationChannel(CHANNEL_ID, "mainChannel2", "main channel2에서 알람 사용")
            displayNotification()

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayNotification() {
        val notificationId = 46

        // Local 날짜의 TodoList 로 이동. ex) 오늘은 2022-06-05일 (오전 12시 11분) -> 2022-06-05 TodoList 이동
        val intentTodolist = Intent(MyApplication.ApplicationContext(), DateActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            MyApplication.ApplicationContext(),
            0,
            intentTodolist,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = Notification.Builder(MyApplication.ApplicationContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.one) // 노티 아이콘
            .setContentTitle("알람") // 노티 제목
            .setContentText("주무셔야 할 시간입니다.") // 노티 내용
            .setAutoCancel(true) // 알림을 탭하면 자동으로 알림삭제
            .setContentIntent(pendingIntent) // 노티 클릭시 인텐트 작업
            .build()

        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(channelId: String, name: String, channelDescription: String) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //중요도
            val importance = NotificationManager.IMPORTANCE_DEFAULT // set importance
            //채널 생성
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = channelDescription
            }
            // Register the channel with the system
            notificationManager = MyApplication.ApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
        else{

        }
    }








}