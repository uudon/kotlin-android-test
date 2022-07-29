package com.github.uudon.kotlinandroidtest.app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.uudon.kotlinandroidtest.app.databinding.ActivityMainBinding
import com.github.uudon.kotlinandroidtest.library.FactorialCalculator
import com.github.uudon.kotlinandroidtest.library.android.NotificationUtil

class MainActivity : AppCompatActivity() {

    private val notificationUtil: NotificationUtil by lazy { NotificationUtil(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCompute.setOnClickListener {
            if (binding.editTextFactorial.text.isNotEmpty()) {
                val input = binding.editTextFactorial.text.toString().toInt()
                val result = FactorialCalculator.computeFactorial(input).toString()

                binding.textResult.text = result
                binding.textResult.visibility = View.VISIBLE

                notificationUtil.showNotification(
                    context = this,
                    title = getString(R.string.notification_title),
                    message = result
                )
            } else {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
