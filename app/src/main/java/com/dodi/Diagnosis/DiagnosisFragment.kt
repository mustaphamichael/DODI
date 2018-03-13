package com.dodi.Diagnosis

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dodi.Diagnosis.adapter.DiagnosisAdapter
import com.dodi.Model.Diagnosis
import com.dodi.R
import kotlinx.android.synthetic.main.fragment_diagnosis.view.*
import kotlin.collections.ArrayList

/**
 * A placeholder fragment containing a simple view.
 */
class DiagnosisFragment : Fragment() {

    private var mListener: DiagnosisFragmentCallback? = null
    private var diagnosis: ArrayList<Diagnosis> = ArrayList()
    private var prescribedDiagnosis: ArrayList<Diagnosis> = ArrayList()
    private var symptoms: ArrayList<String> = ArrayList()
    private lateinit var adapter: DiagnosisAdapter
    private lateinit var listView: RecyclerView
    private lateinit var noDataLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            symptoms = arguments.getStringArrayList(SYMPTOMS)
        }
        (activity as DiagnosisActivity).setActionBarTitle(getString(R.string.title_activity_diagnosis))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_diagnosis, container, false)
        listView = view.recyclerView
        noDataLayout = view.no_data_layout
        listView.layoutManager = LinearLayoutManager(context)
        adapter = DiagnosisAdapter(context, prescribedDiagnosis)
        listView.adapter = adapter
        this.getAllDiagnosis()
        this.getPrescribedDiagnosis(symptoms)
        if (diagnosis.isNotEmpty()) view.progressBar.visibility = View.GONE
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is DiagnosisFragmentCallback) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement DiagnosisFragmentCallback")
        }
    }

    /**
     * Get all Diagnosis
     */
    private fun getAllDiagnosis() {
        val res = context.resources
        val diseases = res.getStringArray(R.array.diseases)
        var count = 0
        for (d in diseases) {
            val model = Diagnosis()
            model.disease = diseases[count]
            when (model.disease) {
                "distemper" -> {
                    model.symptom = res.getStringArray(R.array.distemper)
                    model.treatment = res.getString(R.string.distemper)
                }
                "hepatitis" -> {
                    model.symptom = res.getStringArray(R.array.distemper)
                    model.treatment = res.getString(R.string.hepatitis)
                }
                "leptospirosis" -> {
                    model.symptom = res.getStringArray(R.array.leptospirosis)
                    model.treatment = res.getString(R.string.leptospirosis)
                }
                "para_influenza" -> {
                    model.symptom = res.getStringArray(R.array.para_influenza)
                    model.treatment = res.getString(R.string.para_influenza)
                }
                "parvovirus" -> {
                    model.symptom = res.getStringArray(R.array.parvovirus)
                    model.treatment = res.getString(R.string.parvovirus)
                }
                "rabies" -> {
                    model.symptom = res.getStringArray(R.array.rabies)
                    model.treatment = res.getString(R.string.rabies)
                }
                "bordatella" -> {
                    model.symptom = res.getStringArray(R.array.bordatella)
                    model.treatment = res.getString(R.string.bordatella)
                }
                "corona_virus" -> {
                    model.symptom = res.getStringArray(R.array.corona_virus)
                    model.treatment = res.getString(R.string.corona_virus)
                }
                "lyme_disease" -> {
                    model.symptom = res.getStringArray(R.array.lyme_disease)
                    model.treatment = res.getString(R.string.lyme_disease)
                }
                "coccidiosis" -> {
                    model.symptom = res.getStringArray(R.array.coccidiosis)
                    model.treatment = res.getString(R.string.coccidiosis)
                }
                "giardiosis" -> {
                    model.symptom = res.getStringArray(R.array.giardiosis)
                    model.treatment = res.getString(R.string.giardiosis)
                }
            }
            diagnosis.add(model)
            count++
        }
    }

    /**
     * Get prescribed diagnosis :- to be displayed on the Diagnosis Result
     */
    private fun getPrescribedDiagnosis(selectedSymptom: ArrayList<String>) {
        prescribedDiagnosis.clear()
        for (i in diagnosis) {
            if (sortSymptomsArray(selectedSymptom, i.symptom)) {
                System.out.println(i.disease)
                prescribedDiagnosis.add(i)
            }
        }
        if (prescribedDiagnosis.isEmpty()) noDataLayout.visibility = View.VISIBLE
        adapter.notifyDataSetChanged()
    }

    /**
     * Sort the selected symptoms from all symptoms
     */
    private fun sortSymptomsArray(selectedSymptoms: ArrayList<String>, mainSymptoms: Array<String>): Boolean {
        var count = 0
        for (i in selectedSymptoms) {
            for (j in mainSymptoms) {
                if (i == j) {
                    count++
                    break
                }
            }
        }
        return (count > 1)
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        (activity as DiagnosisActivity).setActionBarTitle(getString(R.string.title_activity_diagnosis))
    }

    /**
     * Callback Interface
     */
    interface DiagnosisFragmentCallback {
    }

    companion object {
        private val SYMPTOMS = "SYMPTOMS"
        fun newInstance(symptoms: ArrayList<String>): DiagnosisFragment {
            val fragment = DiagnosisFragment()
            val args = Bundle()
            args.putStringArrayList(SYMPTOMS, symptoms)
            fragment.arguments = args
            return fragment
        }
    }
}