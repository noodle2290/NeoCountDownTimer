package takutaku.app.neocountdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.view.isVisible
import takutaku.app.neocountdowntimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  lateinit var timer:CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val hour:Long = 0
        val minute:Long = 5
        val second:Long = 0

        binding.timeTextView.text = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}"

        var secondAll: Long = hour * 3600 + minute * 60 + second

        binding.stopButton.isVisible = false

        binding.startButton.setOnClickListener {
            binding.startButton.isVisible = false
            binding.stopButton.isVisible = true
            timer = object : CountDownTimer(secondAll * 1000, 1000) {
                override fun onFinish() {
                    binding.startButton.isVisible = true
                    secondAll = hour * 3600 + minute * 60 + second
                    binding.timeTextView.text = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}"
                }

                override fun onTick(millisUntilFinished: Long) {
                    secondAll--
                    binding.timeTextView.text = "${(secondAll / 3600).toString().padStart(2, '0')}:${((secondAll % 3600) / 60).toString().padStart(2, '0')}:${((secondAll % 3600) % 60).toString().padStart(2, '0')}"
                }
            }
            timer.start()
        }

        binding.stopButton.setOnClickListener {
            binding.startButton.isVisible = true
            binding.stopButton.isVisible = false
            timer.cancel()
        }

        binding.resetButton.setOnClickListener {
            timer.cancel()
            binding.startButton.isVisible = true
            binding.stopButton.isVisible = false
            binding.timeTextView.text = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}"
            secondAll = hour * 3600 + minute * 60 + second
        }
    }
}
