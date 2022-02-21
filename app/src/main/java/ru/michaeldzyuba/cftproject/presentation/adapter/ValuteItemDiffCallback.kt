package ru.michaeldzyuba.cftproject.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.michaeldzyuba.cftproject.domain.ValuteItem

object ValuteItemDiffCallback: DiffUtil.ItemCallback<ValuteItem>() {
    override fun areItemsTheSame(oldItem: ValuteItem, newItem: ValuteItem): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: ValuteItem, newItem: ValuteItem): Boolean {
        return oldItem == newItem
    }
}