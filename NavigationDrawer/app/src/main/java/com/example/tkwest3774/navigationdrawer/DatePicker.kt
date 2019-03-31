package com.example.tkwest3774.navigationdrawer

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.widget.DatePicker
import java.util.*
import com.example.tkwest3774.navigationdrawer.MainActivity
import java.time.Year

//DatePicker実装のためのクラス
class DatePicker :DialogFragment(),DatePickerDialog.OnDateSetListener{


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calender=Calendar.getInstance()
        val year=calender.get(Calendar.YEAR)
        val month=calender.get(Calendar.MONTH)
        val day=calender.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            activity,
            activity as MainActivity?,
            year,
            month,
            day
        )
    }override fun onDateSet(view: android.widget.DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
    }
}