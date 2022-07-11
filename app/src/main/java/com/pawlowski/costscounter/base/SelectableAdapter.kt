package com.pawlowski.costscounter.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

abstract class SelectableAdapter <T: RecyclerView.ViewHolder>: RecyclerView.Adapter<T>() {
    private val positionsSelected_: MutableSet<Int> = mutableSetOf()
    val positionsSelected: Set<Int> get() = positionsSelected_
    val isSomethingSelectedLiveData: LiveData<Boolean> get() = isSomethingSelectedLiveData_
    private val isSomethingSelectedLiveData_: MutableLiveData<Boolean> = MutableLiveData(false)

    protected fun selectOrUnselectPosition(position: Int)
    {
        if(positionsSelected.contains(position))
        {
            positionsSelected_.remove(position)
            if(!isSomethingSelected())
                isSomethingSelectedLiveData_.value = false
        }
        else
        {
            positionsSelected_.add(position)
            if (isSomethingSelectedLiveData_.value == false)
                isSomethingSelectedLiveData_.value = true
        }
        notifyItemChanged(position)
    }

    fun isPositionSelected(position: Int): Boolean
    {
        return positionsSelected.contains(position)
    }

    fun unselectAll()
    {
        val selected = positionsSelected.toList()
        positionsSelected_.clear()
        for(el in selected)
        {
            notifyItemChanged(el)
        }
        isSomethingSelectedLiveData_.value = false
    }

    fun isSomethingSelected() : Boolean
    {
        return positionsSelected.isNotEmpty()
    }


}