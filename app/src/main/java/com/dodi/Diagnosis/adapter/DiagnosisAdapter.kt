package com.dodi.Diagnosis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dodi.CaseUtils
import com.dodi.Model.Diagnosis
import com.dodi.R
import java.util.*

class DiagnosisAdapter(val context: Context, private val diagnosis: ArrayList<Diagnosis>) :
        RecyclerView.Adapter<DiagnosisAdapter.DiagnosisViewHolder>() {

    class DiagnosisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contentLayout: LinearLayout = itemView.findViewById(R.id.content)
        var diagnosis: TextView = itemView.findViewById(R.id.diagnosis)
        var expandBtn: ImageView = itemView.findViewById(R.id.expand)
        var symptoms: TextView = itemView.findViewById(R.id.symptoms)
        var treatment: TextView = itemView.findViewById(R.id.treatment)
        var collapseBtn: Button = itemView.findViewById(R.id.collapse_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DiagnosisViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.diagnosis_list, parent, false)
        return DiagnosisViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiagnosisViewHolder, position: Int) {
        val model = diagnosis[position]

        holder.diagnosis.text = CaseUtils.titleCase(model.disease).toString()
        val symptoms = Arrays.toString(model.symptom) // Java Utils to print array as text/String
        holder.symptoms.text = symptoms
        holder.treatment.text = model.treatment

        holder.itemView.setOnClickListener {
            this.toggleCollapseBtn(holder.contentLayout, holder.expandBtn, holder.collapseBtn)
        }

        holder.collapseBtn.setOnClickListener{
            this.toggleCollapseBtn(holder.contentLayout, holder.expandBtn, holder.collapseBtn)
        }
    }

    override fun getItemCount(): Int {
        return diagnosis.size
    }

    private fun toggleCollapseBtn(layout: LinearLayout, img: ImageView, button: Button){
        if (layout.visibility == View.VISIBLE){
            layout.visibility = View.GONE
            button.visibility = View.GONE
            img.visibility = View.VISIBLE
        } else {
            layout.visibility = View.VISIBLE
            button.visibility = View.VISIBLE
            img.visibility = View.GONE
        }
    }
}