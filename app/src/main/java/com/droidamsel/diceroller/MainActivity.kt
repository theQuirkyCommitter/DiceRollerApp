@file:Suppress("DEPRECATION")

package com.droidamsel.diceroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //app name on the toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Dice Roller"

        //INITIALIZATION
        val rollButton: Button = findViewById(R.id.button) //finding the button
        rollButton.setOnClickListener { //work on ONCLICK
            rollDice()//rolling the dice function
        }
        rollDice()
    }

    //ROLLING THE DICE WORKING
    private fun rollDice() {
        val dice = Dice(6)
        val dice2 = Dice2(6)

        val diceRoll = dice.roll()
        val diceRoll2 = dice2.roll()

        //output to show in text view so
        val diceImage: ImageView = findViewById(R.id.imageView) //finding the text view
        val diceImage2: ImageView = findViewById(R.id.imageView2) //finding the text view

        val drawableResource = when (diceRoll) {
            1 -> R.drawable.redice_1
            2 -> R.drawable.redice_2
            3 -> R.drawable.redice_3
            4 -> R.drawable.redice_4
            5 -> R.drawable.redice_5
            else -> R.drawable.redice_6
        }

        val drawableResource2 = when (diceRoll2) {
            1 -> R.drawable.redice_1
            2 -> R.drawable.redice_2
            3 -> R.drawable.redice_3
            4 -> R.drawable.redice_4
            5 -> R.drawable.redice_5
            else -> R.drawable.redice_6
        }

        diceImage.setImageResource(drawableResource)
        diceImage2.setImageResource(drawableResource2)

        diceImage.contentDescription = diceRoll.toString()
        diceImage2.contentDescription = diceRoll.toString()

        //when two dice shows the same result
        if (diceRoll == diceRoll2 ) {
            showToast("MAGICAL MATCH")
        } else {
            showToast("Dice 1 shows $diceRoll\nDice 2 shows $diceRoll2")
        }
    }

    // TOAST DISPLAY
    @SuppressLint("InflateParams")
    private fun showToast(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.toast, null)
        val toastText = layout.findViewById<TextView>(R.id.toast_text)
        toastText.text = message
        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 280)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }

    //getting random nos from 1 to 6
    class Dice(private val numSides: Int) {
        fun roll(): Int {
            return (1..numSides).random()
        }
    }
    class Dice2(private val numSides: Int) {
        fun roll(): Int {
            return (1..numSides).random()
        }
    }

}