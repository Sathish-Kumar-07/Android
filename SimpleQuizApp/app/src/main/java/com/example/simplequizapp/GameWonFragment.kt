package com.example.simplequizapp

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.simplequizapp.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentGameWonBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_won,container,false)
        binding.nextMatchButton.setOnClickListener { view->
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        setHasOptionsMenu(true)
        //val args = GameWonFragmentArgs.fromBundle(requireArguments())
        //Toast.makeText(context,"NumCorrect ${args.numCorrect},NumQuestions ${args.numQuestions}",Toast.LENGTH_LONG).show()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu_share,menu)
        if(null==getShareIntent().resolveActivity(requireActivity().packageManager)) {
            menu.findItem(R.id.share).isVisible = false
        }
    }

    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.success_text,args.numCorrect,args.numQuestions))
            .setType("text/plain")
            .intent
    }
    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}