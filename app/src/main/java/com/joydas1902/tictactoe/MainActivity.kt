package com.joydas1902.tictactoe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //    0 = Blank     1 = X       2 = O
    private var gameActive = true
    private var activePlayer = 1
    private var gameState = IntArray(9)
    private var winPositions = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),  // Horizontal
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),  // vertical
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6) )                      // Diagonal

    fun playerTap(view: View) {
        val img: ImageView = view as ImageView
        val tappedImage: Int = img.tag.toString().toInt()
        val status = findViewById<TextView>(R.id.status)

        if (!gameActive)
            gameReset(view)

        if (gameState[tappedImage] == 1 || gameState[tappedImage] == 2)
            gameReset(view)

        if (gameState[tappedImage] == 0) {
            gameState[tappedImage] = activePlayer

            if (activePlayer == 1) {
                img.setImageResource(R.drawable.x)
                activePlayer = 2
                status.text = "O's Turn"
            } else {
                img.setImageResource(R.drawable.o)
                activePlayer = 1
                status.text = "X's Turn"
            }
        }
        // Winner Checking
        for (winPos in winPositions) {
            if (gameState[winPos[0]] == gameState[winPos[1]] && (gameState[winPos[1]]
                        == gameState[winPos[2]]) && gameState[winPos[0]] != 0) {
                gameActive = false

                if (gameState[winPos[0]] == 1)
                    status.text = "X Wins - Tap to play"
                else
                    status.text = "O Wins - Tap to play"
                break
            }
            else {
                var draw = true
                for (it in gameState) if (it == 0) draw = false
                if (draw) status.text = "Draw match - Tap to play"
            }
        }
    }

    private fun gameReset(view: View?) {
        gameActive = true
        activePlayer = 1
        for (i in gameState.indices) gameState[i] = 0
        (findViewById<View>(R.id.imageView0) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView1) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView2) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView3) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView4) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView5) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView6) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView7) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView8) as ImageView).setImageResource(0)
        val status = findViewById<TextView>(R.id.status)
        status.text = "X's Turn - Tap to play"
    }
}