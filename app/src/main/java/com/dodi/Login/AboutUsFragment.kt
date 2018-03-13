package com.dodi.Login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dodi.Diagnosis.DiagnosisActivity
import com.dodi.R

class AboutUsFragment : Fragment() {

    private var mListener: AboutUsFragmentCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {        }
        if (activity is LoginActivity) (activity as LoginActivity).setActionBarTitle(getString(R.string.about_us_text))
        else if (activity is DiagnosisActivity) (activity as DiagnosisActivity).setActionBarTitle(getString(R.string.about_us_text))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_about_us, container, false)
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onResume() {
        super.onResume()
        if (activity is LoginActivity) (activity as LoginActivity).setActionBarTitle(getString(R.string.about_us_text))
        else if (activity is DiagnosisActivity) (activity as DiagnosisActivity).setActionBarTitle(getString(R.string.about_us_text))
    }

    /**
     * Callback Interface
     */
    interface AboutUsFragmentCallback {}

    companion object {
        fun newInstance(): AboutUsFragment {
            val fragment = AboutUsFragment()
            val args = Bundle()
//            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}