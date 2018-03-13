package com.dodi.Diagnosis

import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.Toast
import com.dodi.Diagnosis.adapter.SymptomsAdapter
import com.dodi.Model.Symptom
import com.dodi.R
import kotlinx.android.synthetic.main.fragment_symptoms.view.*

class SymptomsFragment : Fragment() {

    private var listener: SymptomsFragmentCallback? = null
    private lateinit var listView: RecyclerView
    private var symptoms: ArrayList<Symptom> = ArrayList()
    private var symptomAdapter: SymptomsAdapter? = null
    private lateinit var diagnoseBtn: Button
    private var selectedSymptoms: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {}
        (activity as DiagnosisActivity).setActionBarTitle(getString(R.string.title_activity_diagnosis))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_symptoms, container, false)
        listView = view.listView
        diagnoseBtn = view.diagnose_btn
        listView.layoutManager = LinearLayoutManager(context)
        symptomAdapter = SymptomsAdapter(context, symptoms,
                // get the selected symptoms from the recycler view adapter
                object : SymptomsAdapter.SymptomsAdapterCallback{
            override fun receiveSymptoms(symptoms: ArrayList<String>) {
                selectedSymptoms = symptoms
            }

        })
        listView.adapter = symptomAdapter
        displaySymptoms()
        diagnoseBtn.setOnClickListener {
            if (selectedSymptoms.size < 2){
                Toast.makeText(context, "Select at least two(2) symptoms", Toast.LENGTH_SHORT).show()
            } else{
                listener?.sendSymptoms(selectedSymptoms)
            }
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SymptomsFragmentCallback) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement SymptomsFragmentCallback")
        }
    }

    /**
     * Get Symptoms
     */
    private fun displaySymptoms(){
        val array = context.resources.getStringArray(R.array.symptoms)
        var count = 0
        symptoms.clear()
        selectedSymptoms.clear()
        for (symptom in array){
            val sym = Symptom(count.toString(), symptom, false)
            symptoms.add(sym)
            count++
        }
        symptomAdapter?.notifyDataSetChanged()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        (activity as DiagnosisActivity).setActionBarTitle(getString(R.string.title_activity_diagnosis))
    }

    /**
     * Callback Interface
     */
    interface SymptomsFragmentCallback {
        fun sendSymptoms(symptoms: ArrayList<String>)
    }

    companion object {
        fun newInstance(): SymptomsFragment {
            val fragment = SymptomsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
