package com.joydas1902.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private var player = 1       // 0 = Blank  1 = X  2 = O
    private var gameActive = true

    private var box = IntArray(9)
    private var winningPos = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),  // Horizontal
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),  // vertical
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)                        // Diagonal
    )

    fun playerTap(view: View) {
        val imgage = view as ImageView
        val tap = imgage.tag.toString().toInt()
        val status = findViewById<TextView>(R.id.status)

        if (!gameActive)
            gameReset()

        if (box[tap] == 1 || box[tap] == 2)
            gameReset()

        if (box[tap] == 0) {
            box[tap] = player

            if (player == 1) {
                imgage.setImageResource(R.drawable.x)
                player = 2
                status.text = "O's Turn"
            }
            else {
                imgage.setImageResource(R.drawable.o)
                player = 1
                status.text = "X's Turn"
            }
        }
        // Winner Checking
        for (i in winningPos) {
            if (box[i[0]] == box[i[1]] && box[i[1]] == box[i[2]] && box[i[0]] != 0) {
                gameActive = false
                if (box[i[0]] == 1) status.text = "X Wins - Tap to play"
                else status.text = "O Wins - Tap to play"
                break
            }
            else {
                var draw = true
                for (it in box)
                    if (it == 0) draw = false
                if (draw) status.text = "Draw match - Tap to play"
            }
        }
    }

    private fun gameReset() {
        gameActive = true
        player = 1
        for (i in box) box[i] = 0
        (findViewById<ImageView>(R.id.imageView0)).setImageResource(0)
        (findViewById<ImageView>(R.id.imageView1)).setImageResource(0)
        (findViewById<ImageView>(R.id.imageView2)).setImageResource(0)
        (findViewById<ImageView>(R.id.imageView3)).setImageResource(0)
        (findViewById<ImageView>(R.id.imageView4)).setImageResource(0)
        (findViewById<ImageView>(R.id.imageView5)).setImageResource(0)
        (findViewById<ImageView>(R.id.imageView6)).setImageResource(0)
        (findViewById<ImageView>(R.id.imageView7)).setImageResource(0)
        (findViewById<ImageView>(R.id.imageView8)).setImageResource(0)
        val status = findViewById<TextView>(R.id.status)
        status.text = "X's Turn - Tap to play"
    }
}