package com.example.adv160420122week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class GameFragment : Fragment() {
    private var number1 = 0
    private var number2 = 0
    private var score = 0

    private lateinit var txtNum1: TextView
    private lateinit var txtNum2: TextView
    private lateinit var txtAnswer: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        txtNum1 = view.findViewById(R.id.txtNum1)
        txtNum2 = view.findViewById(R.id.txtNum2)
        txtAnswer = view.findViewById(R.id.txtAnswer)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
            txtTurn.text = "$playerName's Turn"

            //random numbers
            randomNumbers()
        }
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            val userAnswer = txtAnswer.text.toString()

            if (userAnswer.isNotEmpty()) {
                val answer = number1 + number2
                if (userAnswer.toInt() == answer) {
                    score ++
                    Snackbar.make(requireView(), "Correct! Your score is $score", Snackbar.LENGTH_SHORT).show()
                    randomNumbers()
                } else {
                    val resultScore = score.toString()
                    val action = GameFragmentDirections.actionResultFragment(resultScore)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    private fun randomNumbers() {
        //Generate two random numbers between 1 and 99
        number1 = Random.nextInt(1,100)
        number2 = Random.nextInt(1,100)

        //Set the text for the two text views
        txtNum1.text = number1.toString()
        txtNum2.text = number2.toString()

        //Clear the input field
        txtAnswer.text = ""
    }
}