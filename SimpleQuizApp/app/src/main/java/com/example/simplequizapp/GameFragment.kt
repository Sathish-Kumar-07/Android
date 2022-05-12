package com.example.simplequizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.simplequizapp.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    data class Question(val question:String, val options:List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(question = "What is Android Jetpack?",
            options = listOf("all of these", "tools", "documentation", "libraries")),
        Question(question = "Base class for Layout?",
            options = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
        Question(question = "Layout for complex Screens?",
            options = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
        Question(question = "Pushing structured data into a Layout?",
            options = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")),
        Question(question = "Inflate layout in fragments?",
            options = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout")),
        Question(question = "Build system for Android?",
            options = listOf("Gradle", "Graddle", "Grodle", "Groyle")),
        Question(question = "Android vector format?",
            options = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
        Question(question = "Android Navigation Component?",
            options = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
        Question(question = "Registers app with launcher?",
            options = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
        Question(question = "Mark a layout for Data Binding?",
            options = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"))
    )

    lateinit var currentQuestion:Question
    lateinit var currentOptions:List<String>
    private var questionIndex:Int = 0
    private val numOfQuestions = ((questions.size + 1) / 2).coerceAtMost(3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.
                inflate<FragmentGameBinding>(
                    inflater,
                    R.layout.fragment_game_,
                    container,
                    false)
        randomizeQuestions()
        binding.game = this
        binding.submitButton.setOnClickListener { view->
            val checkedId = binding.radioGroup.checkedRadioButtonId
            var answeredIndex = 0
            if (-1 != checkedId){
                answeredIndex = when (checkedId){
                    binding.RadioButtonTwo.id -> 1
                    binding.RadioButtonThree.id -> 2
                    binding.RadioButtonFour.id -> 3
                    else -> 0
                }
            }
            if (currentOptions[answeredIndex] == currentQuestion.options[0]){
                questionIndex++
                if(questionIndex < numOfQuestions){
                    setQuestion()
                    binding.invalidateAll()
                }
                else{
                    view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(numOfQuestions,questionIndex))
                }
            }
            else{
                view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToGameLostFragment())
            }
        }
        return binding.root
    }
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        currentOptions = currentQuestion.options.toMutableList().shuffled()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_quiz_app_question, questionIndex + 1, numOfQuestions)
    }
}