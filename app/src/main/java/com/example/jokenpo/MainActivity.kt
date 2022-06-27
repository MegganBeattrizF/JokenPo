package com.example.jokenpo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val imageViewResult by lazy { findViewById<ImageView>(R.id.ivResult) }
    private val buttonStone by lazy { findViewById<ImageView>(R.id.btnStone) }
    private val buttonPaper by lazy { findViewById<ImageView>(R.id.btnPaper) }
    private val buttonScissors by lazy { findViewById<ImageView>(R.id.btnScissors) }
    private val tvAppChoose by lazy { findViewById<TextView>(R.id.tvAppChoose) }
    private val tvUserChoose by lazy { findViewById<TextView>(R.id.tvUserChoose) }
    private var userChoose: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        buttonStone.setOnClickListener {
            buttonStone.isClickable = false
            userChoose = STONE_CODE
            showResult()
            hideButtonOptions(STONE_CODE)
        }
        buttonPaper.setOnClickListener {
            buttonPaper.isClickable = false
            userChoose = PAPER_CODE
            showResult()
            hideButtonOptions(PAPER_CODE)
        }
        buttonScissors.setOnClickListener {
            buttonScissors.isClickable = false
            userChoose = SCISSOR_CODE
            showResult()
            hideButtonOptions(SCISSOR_CODE)
        }
    }

    private fun generateRandomNumber() = Random.nextInt(3)

    private fun showResult() {
        when (generateRandomNumber()) {
            STONE_CODE -> {
                showDrawableResource(R.drawable.pedra)
                showAppChooseResult(STONE_CODE)
                showUserChooseResult(STONE_CODE)
            }
            PAPER_CODE -> {
                showDrawableResource(R.drawable.papel)
                showAppChooseResult(PAPER_CODE)
                showUserChooseResult(PAPER_CODE)
            }
            SCISSOR_CODE -> {
                showDrawableResource(R.drawable.tesoura)
                showAppChooseResult(SCISSOR_CODE)
                showUserChooseResult(SCISSOR_CODE)
            }
        }
    }

    private fun showAppChooseResult(code: Int) {
        val stone = getString(R.string.stone_text)
        val paper = getString(R.string.paper_text)
        val scissor = getString(R.string.scissor_test)
        tvAppChoose.apply {
            if (code == STONE_CODE) text = getString(R.string.app_choose_result_text, stone)
            if (code == PAPER_CODE) text = getString(R.string.app_choose_result_text, paper)
            if (code == SCISSOR_CODE) text = getString(R.string.app_choose_result_text, scissor)
        }
    }

    private fun showUserChooseResult(code: Int) {
        val stone = getString(R.string.stone_text)
        val paper = getString(R.string.paper_text)
        val scissor = getString(R.string.scissor_test)
        val win = getString(R.string.win_text)
        val loser = getString(R.string.lose_text)
        tvUserChoose.apply {
            when {
                code == STONE_CODE && userChoose == STONE_CODE -> text = getString(R.string.user_choose_result_text, stone, loser)
                code == PAPER_CODE && userChoose == PAPER_CODE ->  text = getString(R.string.user_choose_result_text, paper, loser)
                code == SCISSOR_CODE && userChoose == PAPER_CODE -> text = getString(R.string.user_choose_result_text, paper, loser)

                code == STONE_CODE && userChoose == PAPER_CODE -> text = getString(R.string.user_choose_result_text, paper, win)
                code == PAPER_CODE && userChoose == SCISSOR_CODE ->  text = getString(R.string.user_choose_result_text, scissor, win)
                code == SCISSOR_CODE && userChoose == STONE_CODE -> text = getString(R.string.user_choose_result_text, stone, win)

                code == STONE_CODE && userChoose == SCISSOR_CODE -> text = getString(R.string.user_choose_result_text, scissor, loser)
                code == PAPER_CODE && userChoose == STONE_CODE ->  text = getString(R.string.user_choose_result_text, stone, loser)
                code == SCISSOR_CODE && userChoose == PAPER_CODE -> text = getString(R.string.user_choose_result_text, paper, loser)
            }
        }
    }

    private fun hideButtonOptions(code: Int) {
        when (code) {
            STONE_CODE -> {
                buttonPaper.visibility = View.GONE
                buttonScissors.visibility = View.GONE
            }
            PAPER_CODE -> {
                buttonStone.visibility = View.GONE
                buttonScissors.visibility = View.GONE
            }
            SCISSOR_CODE -> {
                buttonPaper.visibility = View.GONE
                buttonStone.visibility = View.GONE
            }
        }
    }

    private fun showDrawableResource(imageResource: Int) {
        imageViewResult.setImageDrawable(AppCompatResources.getDrawable(this, imageResource))
    }

    companion object {
        const val STONE_CODE = 0
        const val PAPER_CODE = 1
        const val SCISSOR_CODE = 2
    }
}