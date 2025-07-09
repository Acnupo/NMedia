package ru.netology.nmedia

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.content.Context
import android.content.res.Configuration
import java.util.Locale
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var liked = false
    private var likes = 999 // Изменил начальное значение для демонстрации
    private var shares = 0

    override fun attachBaseContext(newBase: Context) {
        val locale = Locale("ru")
        Locale.setDefault(locale)
        val config = Configuration(newBase.resources.configuration)
        config.setLocale(locale)
        val context = newBase.createConfigurationContext(config)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rootLayout: ConstraintLayout = findViewById(R.id.main)
        val avatar: ImageView = findViewById(R.id.avatar)
        val author: TextView = findViewById(R.id.author)
        val published: TextView = findViewById(R.id.published)
        val content: TextView = findViewById(R.id.content)
        val likeButton: ImageButton = findViewById(R.id.like_button)
        val shareButton: ImageButton = findViewById(R.id.share_button)
        val likesCount: TextView = findViewById(R.id.likes_count)
        val sharesCount: TextView = findViewById(R.id.shares_count)

        avatar.setImageResource(R.drawable.ic_netology_48dp)
        author.text = getString(R.string.post_author)
        published.text = getString(R.string.post_published)
        content.text = getString(R.string.post_content)

        likesCount.text = likes.shortCount()
        sharesCount.text = shares.shortCount()

        rootLayout.setOnClickListener {
            Log.d("EVENT_TEST", "Клик по корневому layout") // Изменил текст лога
        }

        likeButton.setOnClickListener {
            Log.d("EVENT_TEST", "Клик по кнопке лайка") // Изменил текст лога
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
            Log.d("EVENT_TEST", "Клик по кнопке поделиться") // Изменил текст лога
            shares++
            sharesCount.text = shares.shortCount()
        }

        avatar.setOnClickListener {
            Log.d("EVENT_TEST", "Клик по аватару") // Упростил текст лога
        }
    }
}

// Добавил комментарий к функции
/**
 * Форматирует число в сокращённый вид (K - тысячи, M - миллионы)
 */
fun Int.shortCount(): String = when (this) {
    in 0..999 -> this.toString()
    in 1_000..9_999 -> "${this / 1000}.${(this % 1000) / 100}K"
    in 10_000..999_999 -> "${this / 1000}K"
    in 1_000_000..9_999_999 -> "${this / 1_000_000}.${(this % 1_000_000) / 100_000}M"
    else -> "${this / 1_000_000}M"
}