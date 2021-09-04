package gunveer.codes.videoplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import androidx.viewbinding.ViewBinding
import gunveer.codes.videoplayer.databinding.ActivityMainBinding
import gunveer.codes.videoplayer.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVideos.setOnClickListener {
            val intent =  Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }
    }
}