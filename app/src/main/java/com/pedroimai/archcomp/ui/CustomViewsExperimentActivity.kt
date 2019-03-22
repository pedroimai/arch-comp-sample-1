package com.pedroimai.archcomp.ui

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pedroimai.archcomp.R
import com.pedroimai.archcomp.combineLatest
import com.pedroimai.archcomp.di.DaggerCoreComponent
import com.pedroimai.archcomp.ui.widgets.CounterState
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.custom_views_experiment_activity.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class CustomViewsExperimentActivity : AppCompatActivity(),HasActivityInjector {
    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_views_experiment_activity)


        DaggerCoreComponent.create().inject(application)

        val combinedStates = combineLatest(counter_1.state, counter_2.state, counter_3.state)
        combinedStates.observe(this, Observer { tripleState ->
            if (tripleState.first is CounterState.Completed &&
                    tripleState.second is CounterState.Completed &&
                    tripleState.third is CounterState.Completed) {
                toast("TODAS AS VIEWS COMPLETARAAAAAAAM!")
            }
        })


    }
}

