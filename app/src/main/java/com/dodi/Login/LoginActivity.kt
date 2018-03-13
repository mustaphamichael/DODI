package com.dodi.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.dodi.Diagnosis.DiagnosisActivity
import com.dodi.R
import kotlinx.android.synthetic.main.toolbar.*

class LoginActivity : AppCompatActivity(), LoginFragment.LoginFragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        if (findViewById<FrameLayout>(R.id.fragment_container) != null) {
            if (savedInstanceState != null) return // Ensure to always add this

            supportFragmentManager.beginTransaction().add(R.id.fragment_container, LoginFragment.newInstance(),
                    LOGIN_TAG).commit()
        }
    }

    /**
     * Navigate to SignUp Screen
     */
    override fun navigateToSignUp(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SignUpFragment.newInstance(), SIGNUP_TAG)
                .addToBackStack(LOGIN_TAG).commit()
    }

    override fun navigateToAboutUs() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AboutUsFragment.newInstance(), ABOUTUS_TAG)
                .addToBackStack(LOGIN_TAG).commit()
    }

    /**
     * Navigate to Diagnosis Screen
     */
    override fun navigateToDiagnosis() {
        startActivity(Intent(this, DiagnosisActivity::class.java))
    }

    /**
     * Method for setting Action Bar Title
     */
    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    companion object {
        val LOGIN_TAG = "LOGIN_TAG"
        val SIGNUP_TAG = "SIGNUP_TAG"
        val ABOUTUS_TAG = "ABOUTUS_TAG"
    }
}
