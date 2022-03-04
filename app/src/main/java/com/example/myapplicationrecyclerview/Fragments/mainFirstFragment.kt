package com.example.myapplicationrecyclerview.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView.OnItemClickListener
import android.widget.EditText
import android.widget.ListView
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
 * Use the [mainFirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class mainFirstFragment() : Fragment() {
    // first fragment - listview with all states
    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    private var theAdapter: StateAdapter? = null
    private var theListView: ListView? = null
    private var allstates: ArrayList<State>? = null
    private var mListener: OnFirstFragmentInteractionListener? = null


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val v = inflater.inflate(R.layout.fragment_main_first, container, false)
        val ds = DataService()
        allstates = ds.getArrState()


        theAdapter = StateAdapter(requireActivity(), allstates!!)


        theListView = v.findViewById<View>(R.id.ListViewsir) as ListView

        theListView!!.adapter = theAdapter

        //  theListView.setOnTouchListener(sd);

        //  theListView.setOnTouchListener(sd);
        theListView!!.isTextFilterEnabled = true
        val inputSearch = v.findViewById<View>(R.id.inputSearch) as EditText

        // Adding items to listview


        // Adding items to listview
        inputSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                // When user changed the Text
                theAdapter =
                    StateAdapter(activity!!, theAdapter!!.custumeFilter(allstates!!, cs.toString()))
                theListView!!.adapter = theAdapter
            }

            override fun beforeTextChanged(
                arg0: CharSequence, arg1: Int, arg2: Int,
                arg3: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(arg0: Editable) {
                // TODO Auto-generated method stub
            }
        })

        theListView!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                activity, R.anim.shake
            )
            view.startAnimation(hyperspaceJumpAnimation)
            val s = parent.adapter.getItem(position) as State
            (activity as MainActivity?)?.LoadSecFragment(s)
        }

        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener =
            if (context is OnFirstFragmentInteractionListener) {
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

    interface OnFirstFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFirstFragmentInteraction(uri: Uri?)
    }
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment mainFirstFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            mainFirstFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}