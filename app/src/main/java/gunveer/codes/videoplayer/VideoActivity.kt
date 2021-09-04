package gunveer.codes.videoplayer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import gunveer.codes.videoplayer.databinding.ActivityVideoBinding
import hybridmediaplayer.HybridMediaPlayer
import kotlinx.coroutines.CoroutineScope
import java.io.Serializable

class VideoActivity : AppCompatActivity(), RecViewAdapter.OnItemClickListener {

    companion object{
        private const val TAG = "listOfVideos"
    }

    private lateinit var binding: ActivityVideoBinding
    private lateinit var mAdapter: RecViewAdapter
    private var listOfVideos: ArrayList<Videos> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkCall()
        binding.recView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@VideoActivity)
            mAdapter = RecViewAdapter(this@VideoActivity)
            adapter = mAdapter
        }
    }

    private fun networkCall(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.mocki.io/v2/6878e56b/my_videos"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
        Response.Listener { response ->  
            val list = response.getJSONArray("videos")
            val size = list.length() - 1
            for (i in 0..size){
                val title = list.getJSONObject(i).get("title")
                val desc = list.getJSONObject(i).get("description")
                val source = list.getJSONObject(i).get("sources")
                listOfVideos.add(Videos(title.toString(), desc.toString(), source.toString()))
            }
            binding.progressBar.visibility = View.GONE
            mAdapter.updateVideos(listOfVideos)
        }, Response.ErrorListener {
                Snackbar.make(binding.root, "Some error has occurred. $it", Snackbar.LENGTH_LONG).show()
            })
        queue.add(jsonObjectRequest)

    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra("url", listOfVideos[position].source)
        startActivity(intent)
    }


}