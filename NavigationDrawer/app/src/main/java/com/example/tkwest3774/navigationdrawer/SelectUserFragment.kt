package com.example.tkwest3774.navigationdrawer

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*


class SelectUserFragment : Fragment() {

    companion object {
        fun newInstance() = SelectUserFragment()
    }

    private lateinit var viewModel: SelectUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.select_user_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SelectUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

    //ユーザーリストの取得と画面への反映
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.add(0,0,0,"ユーザー１")
        inflater?.inflate(R.menu.userlist,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){

        }
        return true
    }




}
