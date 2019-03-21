package com.pedroimai.archcomp;

import com.facebook.buck.android.support.exopackage.ExopackageApplication;

public class AppShell extends ExopackageApplication {
    public AppShell() {
        super(BuildConfig.EXOPACKAGE_FLAGS == 1);

    }
}