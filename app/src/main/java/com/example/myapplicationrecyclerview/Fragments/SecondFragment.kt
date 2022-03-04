package com.example.myapplicationrecyclerview.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplicationrecyclerview.Activitys.MainActivity
import com.example.myapplicationrecyclerview.Adapters.StateAdapter
import com.example.myapplicationrecyclerview.Model.State
import com.example.myapplicationrecyclerview.R
import com.example.myapplicationrecyclerview.Services.DataService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val s: State? = null
    private var theAdapter: StateAdapter? = null
    private var theListView: ListView? = null
    private var mListener: OnSecondFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        val v =  inflater.inflate(R.layout.fragment_second, container, false)

        val b = v.findViewById<View>(R.id.button) as Button

        b.setOnClickListener { Go_back_fragment() }
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        val ds = DataService()


        val i = requireActivity().intent

        val co = i.getSerializableExtra("StateOb") as State?
        val arrR = ArrayList<State>()

        for (s in co!!.getBorders()!!) { // find all the borders for one given state
            ds.getNstateFromBstate(s)?.let { arrR.add(it) }
        }

        if (arrR.size == 0) Toast.makeText(activity, "Soory No Borders", Toast.LENGTH_LONG).show()

        theAdapter = StateAdapter(requireActivity(), arrR)


        theListView = v.findViewById<View>(R.id.ListViewRe) as ListView

        theListView!!.adapter = theAdapter

        return v
    }

    private fun Go_back_fragment() {
        (activity as MainActivity?)?.GoBack()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener =
            if (context is OnSecondFragmentInteractionListener) {
                context
            } else {
                throw RuntimeException(
                    context.toString()
                            + " must implement OnFragmentInteractionListener"
                )
            }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnSecondFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onSecondFragmentInteraction(uri: Uri?)
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment SecondFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SecondFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}