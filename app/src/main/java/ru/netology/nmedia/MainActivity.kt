package ru.netology.nmedia

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var liked = false
    private var likes = 0
    private var shares = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val likeButton: ImageButton = findViewById(R.id.like_button)
        val shareButton: ImageButton = findViewById(R.id.share_button)
        val likesCount: TextView = findViewById(R.id.likes_count)
        val sharesCount: TextView = findViewById(R.id.shares_count)

        likesCount.text = likes.shortCount()
        sharesCount.text = shares.shortCount()

        likeButton.setOnClickListener {
            liked = !liked
            if (liked) {
                likeButton.setImageResource(R.drawable.ic_like_filled_24)
                likes++
            } else {
                likeButton.setImageResource(R.drawable.ic_like_outline_24)
                if (likes > 0) likes--
            }
            likesCount.text = likes.shortCount()
        }

        shareButton.setOnClickListener {
            shares++
            sharesCount.text = shares.shortCount()
        }
    }
}

fun Int.shortCount(): String = when (this) {
    in 0..999 -> this.toString()
    in 1_000..9_999 -> "${this / 1000}.${(this % 1000) / 100}K"
    in 10_000..999_999 -> "${this / 1000}K"
    in 1_000_000..9_999_999 -> "${this / 1_000_000}.${(this % 1_000_000) / 100_000}M"
    else -> "${this / 1_000_000}M"
}
