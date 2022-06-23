package com.example.jokenpo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val imageViewResult by lazy { findViewById<ImageView>(R.id.ivResult) }
    private val buttonStone by lazy { findViewById<ImageView>(R.id.btnStone) }
    private val buttonPaper by lazy { findViewById<ImageView>(R.id.btnPaper) }
    private val buttonScissors by lazy { findViewById<ImageView>(R.id.btnScissors) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        buttonStone.setOnClickListener { showResult() }
        buttonPaper.setOnClickListener { showResult() }
        buttonScissors.setOnClickListener { showResult() }
    }

    private fun generateRandomNumber() = Random.nextInt(3)

    private fun showResult() {
        when (generateRandomNumber()) {
            STONE_CODE -> showDrawableResource(R.drawable.pedra)
            PAPER_CODE -> showDrawableResource(R.drawable.papel)
            SCISSORS_CODE -> showDrawableResource(R.drawable.tesoura)
        }
    }

    private fun showDrawableResource(imageResource: Int) {
        imageViewResult.setImageDrawable(AppCompatResources.getDrawable(this, imageResource))
    }

    companion object {
        const val STONE_CODE = 0
        const val PAPER_CODE = 1
        const val SCISSORS_CODE = 2
    }
}