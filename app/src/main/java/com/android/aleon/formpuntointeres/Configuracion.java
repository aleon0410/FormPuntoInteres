package com.android.aleon.formpuntointeres;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by LEONES on 29/09/2015.
 */
public class Configuracion extends PreferenceActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.configuracion);
    }
}
