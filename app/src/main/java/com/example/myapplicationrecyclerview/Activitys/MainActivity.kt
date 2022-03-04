package com.example.myapplicationrecyclerview.Activitys

import android.app.AlertDialog
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplicationrecyclerview.Fragments.SecondFragment
import com.example.myapplicationrecyclerview.Fragments.mainFirstFragment
import com.example.myapplicationrecyclerview.Model.State
import com.example.myapplicationrecyclerview.R

class MainActivity : AppCompatActivity(),
    mainFirstFragment.OnFirstFragmentInteractionListener,
    SecondFragment.OnSecondFragmentInteractionListener {
    private var flag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // fregment_container = all the fragments will be on him


        // fregment_container = all the fragments will be on him
        val manager: FragmentManager = supportFragmentManager
        var fragment: Fragment? = manager.findFragmentById(R.id.fregment_container)

        if (fragment == null) // if it the first time to call the first fragment
        {
            fragment = mainFirstFragment()
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.add(R.id.fregment_container, fragment, "0").commit()
        }
    }

    fun LoadSecFragment(s: State?) // replace the first fragment with the second fragment
    {
        val secondFregment = SecondFragment()
        intent.putExtra("StateOb", s)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
        transaction.replace(
            R.id.fregment_container,
            secondFregment,
            (supportFragmentManager.backStackEntryCount - 1).toString() + ""
        ).addToBackStack(null).commit()
        flag = 1
    }

    fun GoBack() { // return to first fragment
        supportFragmentManager.popBackStack()
        val f: Fragment? = supportFragmentManager.findFragmentByTag("0")
        supportFragmentManager.beginTransaction().replace(
            R.id.fregment_container,
            f!!,
            (supportFragmentManager.backStackEntryCount - 1).toString() + ""
        ).commit()
        flag = 0
    }

    override fun onBackPressed() {
        // override the back button on android phones
        if (flag == 0) {
            AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                    DialogInterface.OnClickListener { _, _ -> finish() })
                .setNegativeButton("No", null)
                .show()
        }
    }

    override fun onSecondFragmentInteraction(uri: Uri?) {
        TODO("Not yet implemented")
    }

    override fun onFirstFragmentInteraction(uri: Uri?) {
        TODO("Not yet implemented")
    }
}