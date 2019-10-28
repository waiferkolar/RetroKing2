package coder.test.retroking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coder.test.retroking.adapters.PhotoAdapter
import coder.test.retroking.adapters.PostAdapter
import coder.test.retroking.models.Photo
import coder.test.retroking.models.Post
import coder.test.retroking.services.ServiceBuilder
import coder.test.retroking.services.WebService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        postRecycler.layoutManager = LinearLayoutManager(this)
        postRecycler.layoutManager = GridLayoutManager(this,3)

        loadAllPhoto()
    }

    private fun loadAllPhoto(){
        val service : WebService = ServiceBuilder.createService(WebService::class.java)
        val postRequest : Call<List<Photo>> = service.getAllPhoto()

        postRequest.enqueue(object: Callback<List<Photo>>{
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.d("msg",t.message!!)
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val photos = response.body()

                val adapter = PhotoAdapter(this@MainActivity,photos!!)

                postRecycler.adapter = adapter
            }

        })
    }

    private fun getSinglePost(){

        val service : WebService = ServiceBuilder.createService(WebService::class.java)
        val postRequest : Call<Post> = service.getSinglePost(2)

        postRequest.enqueue(object: Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("msg",t.message!!)
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val post = response.body()

                Log.d("msg",post.toString())

            }

        })
    }
    private fun createNewPost(){
        val userId = 1
        val id = 150
        val title = "My Post Title"
        val body = "My Post Body"

        val service : WebService = ServiceBuilder.createService(WebService::class.java)
        val postRequest : Call<Post> = service.createPost(userId,id,title,body)

        postRequest.enqueue(object: Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("msg",t.message!!)
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val post = response.body()

                Log.d("msg",post.toString())

            }

        })
    }

    private fun loadAllPost() {
        val service: WebService = ServiceBuilder.createService(WebService::class.java)

        val postRequest: Call<List<Post>> = service.getAllPost()

        postRequest.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("msg", t.message!!)
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val posts = response.body()

                val adapter = PostAdapter(this@MainActivity,posts!!)

                postRecycler.adapter = adapter

            }

        })

    }
}
