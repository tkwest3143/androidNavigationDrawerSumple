package com.example.tkwest3774.navigationdrawer

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class InputCompFragment : Fragment() {

    companion object {
        fun newInstance() = InputCompFragment()
    }

    private lateinit var viewModel: InputCompViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.input_comp_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(InputCompViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
