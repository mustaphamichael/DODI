
package com.dodi.Diagnosis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.CheckBox
import android.widget.TextView
import com.dodi.Diagnosis.SymptomsFragment
import com.dodi.Model.Symptom
import com.dodi.R


class SymptomsAdapter(val context: Context, private val symptoms: ArrayList<Symptom>, private val callback: SymptomsAdapterCallback):
        RecyclerView.Adapter<SymptomsAdapter.SymptomsViewHolder>() {

    private var checkedSymptomsArray: ArrayList<String> = ArrayList()

    class SymptomsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var symptom: TextView = itemView.findViewById(R.id.symptom_label)
        var checkBox: CheckBox = itemView.findViewById(R.id.check_box)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SymptomsViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.symptoms_list, parent, false)
        return SymptomsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SymptomsViewHolder, position: Int) {
        val model = symptoms[position]
        holder.symptom.text = model.symptomName
        holder.checkBox.isChecked = false

        // Handle checkbox click events
        holder.checkBox.setOnClickListener {
            this.onClick(model)
        }

        // Handle list click events
        holder.itemView.setOnClickListener {
            this.onClick(model)
            holder.checkBox.isChecked = model.isChecked
        }
    }

    private fun onClick(model: Symptom){
        if (!model.isChecked) {
            model.isChecked = true
            checkedSymptomsArray.add(model.symptomName)
        } else {
            model.isChecked = false
            if (checkedSymptomsArray.contains(model.symptomName)) {
                checkedSymptomsArray.remove(model.symptomName)
            }
        }
        System.out.println(checkedSymptomsArray)
        callback.receiveSymptoms(checkedSymptomsArray)
    }

    override fun getItemCount(): Int {
        return symptoms.size
    }

    /**
     * Adapter callback interface
     */
    interface SymptomsAdapterCallback {
        fun receiveSymptoms(symptoms: ArrayList<String>)
    }
}