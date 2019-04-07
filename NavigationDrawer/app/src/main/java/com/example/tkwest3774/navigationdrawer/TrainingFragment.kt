package com.example.tkwest3774.navigationdrawer

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.example.tkwest3774.navigationdrawer.Model.Training
import com.example.tkwest3774.navigationdrawer.Model.User


class TrainingFragment : Fragment() {

    companion object {
        fun newInstance() = TrainingFragment()
    }

    private lateinit var viewModel: TrainingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.training_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()

        val button = activity?.findViewById<Button>(R.id.InputCompButton)

        button?.setOnClickListener {
            val traininginfo= Training()
            traininginfo.Username="west"
            traininginfo.Type=activity?.findViewById<Spinner>(R.id.TraningSelected)?.selectedItem.toString()
            traininginfo.Weight=activity?.findViewById<TextView>(R.id.UseWeight)?.text.toString().toInt()
            traininginfo.Weight=activity?.findViewById<TextView>(R.id.UseRep)?.text.toString().toInt()
            traininginfo.inputday=activity?.findViewById<TextView>(R.id.dateSelected)?.text.toString()
            println(traininginfo.Username+"//"+traininginfo.Type+"//"+traininginfo.Weight+"//"+traininginfo.Rep)
            if(insertTrainingInfo(activity!!,traininginfo)){
                AlertDialog.Builder(activity)
                    .setMessage("InputOK!!")
                    .show()
            }else{
                AlertDialog.Builder(activity)
                    .setMessage("Failed!!")
                    .show()
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
