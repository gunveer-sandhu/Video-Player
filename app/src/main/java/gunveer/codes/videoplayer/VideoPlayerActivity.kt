package gunveer.codes.videoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import gunveer.codes.videoplayer.databinding.ActivityVideoPlayerBinding
import hybridmediaplayer.ExoMediaPlayer
import hybridmediaplayer.HybridMediaPlayer

class VideoPlayerActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "listOfVideos"
    }

    private lateinit var binding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.extras?.get("url").toString()

        val mediaPlayer = ExoMediaPlayer.getInstance(this)
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        mediaPlayer.setPlayerView(this, binding.videoView)

        mediaPlayer.setOnCompletionListener {
            Snackbar.make(binding.root, "Completed", Snackbar.LENGTH_SHORT).show()
        }
        mediaPlayer.setOnErrorListener { error, _ ->
            Snackbar.make(binding.root, "Some error has occurred $error", Snackbar.LENGTH_LONG).show()
        }
        mediaPlayer.setOnPreparedListener {
            Snackbar.make(binding.root, "Prepared", Snackbar.LENGTH_SHORT).show()
        }

        mediaPlayer.play()
        mediaPlayer.seekTo(1500)
        mediaPlayer.volume = 0.5f
        binding.btnPause.setOnClickListener {
            mediaPlayer.pause()
        }
        mediaPlayer.release()
    }
}