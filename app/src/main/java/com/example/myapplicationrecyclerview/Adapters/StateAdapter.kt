package com.example.myapplicationrecyclerview.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplicationrecyclerview.Model.State
import com.example.myapplicationrecyclerview.R
import java.util.*
import kotlin.collections.ArrayList

class StateAdapter(context: Context, values: ArrayList<State>) :
    ArrayAdapter<State>(context, R.layout.rowlayout, values) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View // setting up the objects from the array on the listview
    {
        val theInflater = LayoutInflater.from(context)
        val theView: View = theInflater.inflate(R.layout.rowlayout, parent, false)
        val state: State? = getItem(position)
        val textViewName = theView.findViewById<View>(R.id.textView1) as TextView
        val textViewnativeName = theView.findViewById<View>(R.id.textView2) as TextView
        textViewName.text = state?.getName()
        textViewnativeName.text = state?.getNativeName()

        //    Toast.makeText(getContext(), state.getFlag(), Toast.LENGTH_LONG).show();
        return theView
    }

    fun custumeFilter(
        input: ArrayList<State>,
        word: String?
    ): ArrayList<State> // for search edit text - filter function
    {
        val arr: ArrayList<State> = ArrayList<State>()
        for (s in input) {
            if (word?.let { s.getName()?.lowercase()?.contains(it) } == true || word?.let {
                    s.getNativeName()?.lowercase()?.contains(
                        it
                    )
                } == true) {
                arr.add(s)
            }
        }
        return arr
    }
}