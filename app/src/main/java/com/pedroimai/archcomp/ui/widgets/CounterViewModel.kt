package com.pedroimai.archcomp.ui.widgets

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pedroimai.archcomp.ArchCompApp
import com.pedroimai.archcomp.ContactRepository
import com.pedroimai.archcomp.viewmodel.SharedContactViewModel


class CounterViewModel(countLimit: Int) : ViewModel() {
    var state = CounterState.Data(limit = countLimit)
        set(value) {
            observableState.value = value
            field = value
        }
    val observableState: MutableLiveData<CounterState> = MutableLiveData()

    fun add() {
        if (state.limitReached) {
            observableState.value = CounterState.Completed
        } else {
            state = state.copy(count = state.count + 1)

            if (state.limitReached) {
                observableState.value = CounterState.Completed
            }
        }
    }

    class Factory(private val countLimit: Int) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
                CounterViewModel(countLimit) as T

    }
}

sealed class CounterState {
    data class Data(val count: Int = 0, val limit: Int = 3) : CounterState() {
        val limitReached: Boolean get() = count == limit
    }

    object Completed : CounterState()
}