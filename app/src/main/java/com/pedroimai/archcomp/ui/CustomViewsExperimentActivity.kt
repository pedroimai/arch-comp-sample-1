package com.pedroimai.archcomp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pedroimai.archcomp.R
import com.pedroimai.archcomp.combineLatest
import com.pedroimai.archcomp.ui.widgets.CounterState
import kotlinx.android.synthetic.main.custom_views_experiment_activity.*
import org.jetbrains.anko.toast

class CustomViewsExperimentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_views_experiment_activity)

        val combinedStates = combineLatest(counter_1.state, counter_2.state, counter_3.state)
        combinedStates.observe(this, Observer { tripleState ->
            if (tripleState.first is CounterState.Completed &&
                    tripleState.second is CounterState.Completed &&
                    tripleState.third is CounterState.Completed) {
                toast("TODAS AS VIEWS COMPLETARAM!")
            }
        })


    }
}

