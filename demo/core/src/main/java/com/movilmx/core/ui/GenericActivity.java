package com.movilmx.core.ui;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad commun entre negocios
 */
public abstract class GenericActivity extends AppCompatActivity {
    private static final String TAG = GenericActivity.class.getSimpleName();

    public abstract <T extends GenericFragment> void addFragment(T fragment);

    public abstract <T extends GenericFragmentFactory> T getFragmentsFactory();

    /**
     * Metodo para ocultar el teclado
     */
    public void hiddenKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
