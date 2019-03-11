package com.pedroimai.archcomp.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.*
import com.pedroimai.archcomp.R
import kotlinx.android.synthetic.main.counter_view.view.*
import org.jetbrains.anko.toast


class CounterView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), LifecycleOwner {

    private val parentActivity: AppCompatActivity = (context as AppCompatActivity)
    val state get() = viewModel.observableState

    override fun getLifecycle(): Lifecycle = parentActivity.lifecycle

    private val viewModel: CounterViewModel = ViewModelProviders.of(context as AppCompatActivity)
            .get(this.toString(), CounterViewModel::class.java)

    init {
        LayoutInflater.from(context).inflate(R.layout.counter_view, this, true)
        btn_add.setOnClickListener { viewModel.add() }
        viewModel.observableState.observe(this, Observer<CounterState> { state ->
            when (state) {
                is CounterState.Data -> txt_counter.text = state.count.toString()
                is CounterState.Completed -> parentActivity.toast("Completou!")

            }

        })
    }
}
