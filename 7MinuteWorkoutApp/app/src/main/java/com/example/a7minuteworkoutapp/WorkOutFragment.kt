package com.example.a7minuteworkoutapp

import android.app.Dialog
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.HandlerThread
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.SharedElementCallback
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkoutapp.databinding.FragmentWorkOutBinding
import com.example.a7minuteworkoutapp.databinding.OnBackDialogLayoutBinding
import org.intellij.lang.annotations.JdkConstants
import java.util.*
import kotlin.collections.ArrayList

class WorkOutFragment : Fragment(),TextToSpeech.OnInitListener{
    lateinit var binding:FragmentWorkOutBinding
    private var countDownTimer : CountDownTimer? = null
    private var textToSpeech:TextToSpeech? = null
    private var audioBackgroundPlayer : MediaPlayer ?= null
    private var timer:Boolean = false
    private var coolDownTime :Long = 10
    get() = field*1000
    private var exerciseTime : Long = 30
    get() = field*1000
    private var exerciseListIndex:Int = 0
    private var exerciseList:MutableList<ExerciseModel> = ExerciseModelList().exerciseList
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       exerciseList = ExerciseModelList().exerciseList
       exerciseList.map {
           it.isSelected = false
           it.isCompleted = false
       }
       binding = FragmentWorkOutBinding.inflate(inflater,container,false)
       binding.progressView.setOnClickListener {
           it.isClickable = false
           startExercise()
       }
       audioBackgroundPlayer = MediaPlayer.create(requireContext(),R.raw.starboyringtone)
       audioBackgroundPlayer?.isLooping = true
       audioBackgroundPlayer?.start()
       audioBackgroundPlayer?.setVolume(0.02f,0.02f)
       textToSpeech = TextToSpeech(requireContext(),this)
       binding.itemsIconRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
       binding.itemsIconRecyclerView.adapter = ItemIconRecyclerAdapter(exerciseList as ArrayList<ExerciseModel>)
       return binding.root
    }

    private fun startCount(maximumTime:Long,
                           progressBarMax:Int = (maximumTime/100).toInt()
    ){
        var progressCount = 0
        binding.progressBar.progress = progressCount
        binding.progressBar.max = progressBarMax
        countDownTimer = object : CountDownTimer(maximumTime,100){

            override fun onTick(millisUntilFinished: Long) {
                binding.progressCount.text = (millisUntilFinished/1000).toInt().toString()
                binding.progressBar.progress = ++progressCount
            }
            override fun onFinish() {
                if (!timer){
                    if (exerciseListIndex != 10){
                        exerciseTimer()
                    }
                } else{
                    if (exerciseListIndex != 9){
                        exerciseList[exerciseListIndex].isSelected = false
                        exerciseList[exerciseListIndex].isCompleted =true
                        //markAsCompleted(completedExerciseList.last().exerciseNo)
                        exerciseListIndex++
                        binding.itemsIconRecyclerView.adapter?.notifyDataSetChanged()
                        coolDownTimer()
                    }
                    else{
                        view?.findNavController()?.navigate(WorkOutFragmentDirections.actionWorkOutFragmentToExerciseCompleteFragment())
                    }
                }
                timer = !timer
            }
        }.start()
    }
    private fun startExercise(){
        if (!timer && exerciseList.isNotEmpty()){
            coolDownTimer()
        }
    }
    private fun coolDownTimer(){
        startCount(coolDownTime)
        binding.myExerciseData = exerciseList[exerciseListIndex]
        binding.exerciseImageView.visibility = View.GONE
//        binding.exceriseNumberTabLayout.visibility = View.GONE
        binding.itemsIconRecyclerView.visibility = View.GONE
        binding.exerciseTitleTextView.visibility = View.VISIBLE
        binding.upcomingTextView.visibility = View.VISIBLE
    }
    private fun exerciseTimer(){
        binding.exerciseImageView.visibility = View.VISIBLE
        binding.exerciseImageView.setImageResource(exerciseList.first().imageId)
//        binding.exceriseNumberTabLayout.visibility = View.VISIBLE
        binding.itemsIconRecyclerView.visibility = View.VISIBLE
        binding.exerciseTitleTextView.visibility = View.GONE
        binding.upcomingTextView.visibility = View.GONE
        startCount(exerciseTime)
        textToSpeech!!.speak(exerciseList[exerciseListIndex].name,TextToSpeech.QUEUE_ADD,null,"")
        exerciseList[exerciseListIndex].isSelected = true
    }
    private fun markAsCompleted(id:Int){
        when (id){
//            1 -> binding.exerciseNo1.setBackgroundResource(R.drawable.item_circular_accent_background)
//            2 -> binding.exerciseNo2.setBackgroundResource(R.drawable.item_circular_accent_background)
//            3 -> binding.exerciseNo3.setBackgroundResource(R.drawable.item_circular_accent_background)
//            4 -> binding.exerciseNo4.setBackgroundResource(R.drawable.item_circular_accent_background)
//            5 -> binding.exerciseNo5.setBackgroundResource(R.drawable.item_circular_accent_background)
//            6 -> binding.exerciseNo6.setBackgroundResource(R.drawable.item_circular_accent_background)
//            7 -> binding.exerciseNo7.setBackgroundResource(R.drawable.item_circular_accent_background)
//            8 -> binding.exerciseNo8.setBackgroundResource(R.drawable.item_circular_accent_background)
//            9 -> binding.exerciseNo9.setBackgroundResource(R.drawable.item_circular_accent_background)
//            10 -> binding.exerciseNo10.setBackgroundResource(R.drawable.item_circular_accent_background)
        }
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = textToSpeech!!.setLanguage(Locale.getDefault())
            if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(requireContext(),"Text To Speech Failed",Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(requireContext(),"Text To Speech Failed",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onPause() {
        super.onPause()
        audioBackgroundPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        audioBackgroundPlayer?.seekTo(audioBackgroundPlayer?.currentPosition?:0)
        audioBackgroundPlayer?.start()
    }

    override fun onDestroyView() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        audioBackgroundPlayer?.stop()
        audioBackgroundPlayer = null
        countDownTimer?.cancel()
        super.onDestroyView()
    }


}
