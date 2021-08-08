package com.example.appshotcutssimple

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.appshotcutssimple.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var shortScan: ShortcutInfoCompat

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            shortScan = ShortcutInfoCompat.Builder(this, "shortcut_scan")
                .setShortLabel(getString(R.string.short_scan))
                .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_launcher))
                .setIntent(Intent(Intent.ACTION_MAIN, null, this, MainActivity::class.java))
                .build()
            ShortcutManagerCompat.addDynamicShortcuts(this, mutableListOf(shortScan))

            Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show()
        }
        binding.btnRemove.setOnClickListener {
            ShortcutManagerCompat.removeDynamicShortcuts(
                this@MainActivity,
                Collections.singletonList("shortcut_scan")
            )

            Toast.makeText(this, "移除成功", Toast.LENGTH_SHORT).show()
        }
    }
}