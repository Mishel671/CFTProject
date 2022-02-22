package ru.michaeldzyuba.cftproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.michaeldzyuba.cftproject.R
import ru.michaeldzyuba.cftproject.databinding.ValuteItemLayoutBinding
import ru.michaeldzyuba.cftproject.domain.ValuteItem

class ValuteListAdapter(
    private val context: Context
) : ListAdapter<ValuteItem, ValuteItemViewHolder>(ValuteItemDiffCallback) {

    var onValuteClickListener: OnValuteClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValuteItemViewHolder {
        val binding = ValuteItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ValuteItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ValuteItemViewHolder, position: Int) {
        val valuteItem = getItem(position)
        val binding = holder.binding
        with(binding) {
            with(valuteItem) {
                val titleText = context.resources.getString(R.string.valuteName)
                val costText = context.resources.getString(R.string.valute_value)
                val nominalText = context.resources.getString(R.string.valute_nominal)

                valuteName.text = String.format(titleText, name, charCode)
                valuteValue.text = String.format(costText, value)
                valuteNominal.text = String.format(nominalText, nominal)

                binding.buttonConvert.setOnClickListener {
                    onValuteClickListener?.onValuteClick(this)
                }
            }
        }
    }

    interface OnValuteClickListener{
        fun onValuteClick(valuteItem: ValuteItem)
    }
}