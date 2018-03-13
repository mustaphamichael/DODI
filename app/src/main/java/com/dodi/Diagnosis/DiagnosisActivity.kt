package com.dodi.Diagnosis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.dodi.Login.AboutUsFragment
import com.dodi.R

import kotlinx.android.synthetic.main.toolbar.*

class DiagnosisActivity : AppCompatActivity(),
        SymptomsFragment.SymptomsFragmentCallback, DiagnosisFragment.DiagnosisFragmentCallback {

    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis)
        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (findViewById<FrameLayout>(R.id.fragment_container) != null) {
            if (savedInstanceState != null) return // Ensure to always add this

            supportFragmentManager.beginTransaction().add(R.id.fragment_container, SymptomsFragment.newInstance(),
                    SYMPTOMS_TAG).commit()
        }
        if (menuItem != null){
            this.toggleMenuItem(menuItem!!)
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_diagnosis, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        menuItem = item
//        when (item.itemId){
//            R.id.action_about_us -> {
//                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AboutUsFragment.newInstance(),
//                        ABOUTUS_TAG).addToBackStack(null).commit()
//                this.toggleMenuItem(item)
////                item.isEnabled = false
////                item.isCheckable = false
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun sendSymptoms(symptoms: ArrayList<String>) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DiagnosisFragment.newInstance(symptoms),
                DIAGNOSIS_TAG).addToBackStack(SYMPTOMS_TAG).commit()
    }

    /**
     * Method for setting Action Bar Title
     */
    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun toggleMenuItem(item: MenuItem){
        item.isVisible = !item.isVisible
    }

    companion object {
        val SYMPTOMS_TAG = "SYMPTOMS_TAG"
        val DIAGNOSIS_TAG = "DIAGNOSIS_TAG"
        val ABOUTUS_TAG = "ABOUTUS_TAG"
    }
}
