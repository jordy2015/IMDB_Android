package com.example.imbdapp.ui.home

import android.content.Context
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.imbdapp.R
import com.example.imbdapp.models.Movie
import com.example.imbdapp.extensions.getPosterUrl
import com.example.imbdapp.extensions.getRating
import com.example.imbdapp.extensions.loadImage


class HomeRecyclerAdapter(val context: Context, val itemListener: MovieItemListener): RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>()
{
    val movies: MutableList<Movie> = mutableListOf()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.titleTextView)
        val poster = itemView.findViewById<ImageView>(R.id.posterImageView)
        val rating = itemView.findViewById<RatingBar>(R.id.movieRatingBar)
        val menu = itemView.findViewById<Button>(R.id.movieOptions)
    }

    fun newMovies(newData: List<Movie>){
        movies.clear()
        movies.addAll(newData)
    }

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_grid_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        with(holder) {
            title.let {
                it.text = movie.title
            }
            rating.let {
                it.rating = movie.getRating()
            }
            poster.let { imageView ->
                movie.posterPath?.let {
                    imageView.loadImage(movie.getPosterUrl())
                }?: run {
                    imageView.setImageResource(R.drawable.ic_placeholdermovie)
                }
            }

            menu.setOnClickListener {
                val popup = PopupMenu(context, menu)
                popup.inflate(R.menu.menu_movie)
                popup.setOnMenuItemClickListener {
                    Toast.makeText(context,R.string.msj_video_added,Toast.LENGTH_SHORT).show()
                    false
                }
                popup.show()
            }
            itemView.setOnClickListener {
                itemListener.onItemClicked(movie)
            }
        }
    }

    interface MovieItemListener {
        fun onItemClicked(video: Movie)
    }
}