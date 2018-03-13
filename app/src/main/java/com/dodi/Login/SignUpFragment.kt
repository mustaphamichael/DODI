package com.dodi.Login

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dodi.R

class SignUpFragment : Fragment() {

    private var mListener: SignUpFragmentCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {        }
        (activity as LoginActivity).setActionBarTitle(getString(R.string.sign_up_text))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_signup, container, false)
    }

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (context is SignUpFragmentCallback) {
//            mListener = context
//        } else {
//            throw RuntimeException(context!!.toString() + " must implement SignUpFragmentCallback")
//        }
//    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onResume() {
        super.onResume()
        (activity as LoginActivity).setActionBarTitle(getString(R.string.sign_up_text))
    }

    /**
     * Callback Interface
     */
    interface SignUpFragmentCallback {
//        fun onLoginCallback(uri: Uri)
    }

    companion object {
        fun newInstance(): SignUpFragment {
            val fragment = SignUpFragment()
            val args = Bundle()
//            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}