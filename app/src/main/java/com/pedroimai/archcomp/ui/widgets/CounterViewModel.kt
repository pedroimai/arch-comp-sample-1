package com.pedroimai.archcomp.ui.widgets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    var state = CounterState.Data()
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
}

sealed class CounterState {
    data class Data(val count: Int = 0, val limit: Int = 3) : CounterState() {
        val limitReached: Boolean get() = count == limit
    }

    object Completed : CounterState()
}