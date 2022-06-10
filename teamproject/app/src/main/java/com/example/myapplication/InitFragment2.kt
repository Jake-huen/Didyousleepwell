package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class InitFragment2 : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_init2, container, false)
        val btn_permission = view.findViewById<Button>(R.id.btn_permission)


        // 권한 허용
        btn_permission.setOnClickListener {
            checkSettingOverlayWindow()
        }


        return view
    }

    private fun checkSettingOverlayWindow(){
        if(Settings.canDrawOverlays(activity)){
            Toast.makeText(activity, "권한이 승인되었습니다.", Toast.LENGTH_SHORT).show()
        }else{
            val overlayIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            requestSettingLauncher.launch(overlayIntent)
        }
    }

    val requestSettingLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(Settings.canDrawOverlays(activity)){
            Toast.makeText(activity, "권한이 승인되었습니다.", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activity, "권한승인이 거부되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }




}