package com.example.tkwest3774.navigationdrawer

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
            val userinfo=User("111","222","asaa","a")
            if(insertUserInfo(activity!!,userinfo)){
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
