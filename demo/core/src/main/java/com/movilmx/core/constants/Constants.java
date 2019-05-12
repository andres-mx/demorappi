package com.movilmx.core.constants;

import android.content.res.Resources;

import com.movilmx.core.BuildConfig;

public class Constants {
    public static String getUrlImage(String endPoint){
        return BuildConfig.URLIMAGES+endPoint;
    }

    /**
     * Convert dp to px, useful on LayoutParams
     *
     * @param dp units to convert
     * @return px equivalence
     */
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
