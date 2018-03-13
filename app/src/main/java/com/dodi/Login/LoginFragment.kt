package com.dodi.Login

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.dodi.R
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    private var mListener: LoginFragmentCallback? = null
    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var loginBT: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {        }
        (activity as LoginActivity).setActionBarTitle(getString(R.string.login_text))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_login, container, false)
        usernameET = view.username
        passwordET = view.password
        loginBT = view.login_btn
        view.sign_up_text.setOnClickListener {
            mListener?.navigateToSignUp()
        }
        loginBT.setOnClickListener {
            mListener?.navigateToDiagnosis()
        }
        view.about_us_text.setOnClickListener{
            mListener?.navigateToAboutUs()
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is LoginFragmentCallback) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement LoginFragmentCallback")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onResume() {
        super.onResume()
        (activity as LoginActivity).setActionBarTitle(getString(R.string.login_text))
    }

    /**
     * Callback Interface
     */
    interface LoginFragmentCallback {
        fun navigateToSignUp()
        fun navigateToDiagnosis()
        fun navigateToAboutUs()
    }

    companion object {
        fun newInstance(): LoginFragment {
            val fragment = LoginFragment()
            val args = Bundle()
//            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}
