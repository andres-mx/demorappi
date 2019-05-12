package com.movilmx.core.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.movilmx.core.R;

public class GenericSnackBar {
    public static final String TAG = GenericSnackBar.class.getSimpleName();
    enum SnackBarDuration {SHORT, LONG, INDEFINITE;}
    public enum IconPosition {LEFT, RIGHT, TOP, BOTTOM;}
    //Duración del snackbar
    private SnackBarDuration mSnackBarDuration = SnackBarDuration.LONG;

    //Posición del titulo
    private IconPosition mIconPositionTitle = IconPosition.LEFT;

    //Posición del icono del snackbar
    private IconPosition mIconPositionAction = IconPosition.RIGHT;

    //private OnActionClickListener mOnActionClickListener;

    private View mView;

    private String mTitleText;

    private String mActionText;

    private int mTitleTextColor = Color.WHITE;

    private int mActionTextColor = Color.WHITE;

    private int mBackgroundColor = Color.BLACK;

    private Typeface mTfTitle = Typeface.DEFAULT;

    private Typeface mTfAction = Typeface.DEFAULT;

    private Drawable mTitleIconDrawable = null;

    private Drawable mActionIconDrawable = null;

    private boolean isNeedClickEvent;

    private int mIconTitlePadding = 0;

    private int mIconActionPadding = 0;

    private int booleanBackground;

    private Context context;


    //Asignación de valor cuando se manda a llamar al snackbar
    public GenericSnackBar view(View view, int booleanBackground) {
        this.mView = view;
        context    = view.getContext();
        this.booleanBackground = booleanBackground;
        if(booleanBackground==-1) {
            this.duration(SnackBarDuration.LONG);
        }
        return this;
    }

    public GenericSnackBar view(View view, int booleanBackground, Context context) {
//        Log.d(TAG, "view() called with: view = [" + view + "], booleanBackground = [" + booleanBackground + "], context = [" + context + "]");
        this.mView    = view;
        this.context  = context;
        this.booleanBackground = booleanBackground;
        if(booleanBackground==-1) {
            this.duration(SnackBarDuration.LONG);
        }
        return this;
    }

    //Se asigna el titulo del texto y de la acción
    public GenericSnackBar text(String titleText, String actionText) {
        this.mTitleText = titleText;
        this.mActionText = actionText;
        return this;
    }

    //Personalización del color del titulo del texto y de la acción

    public GenericSnackBar textColors(int titleTextColor, int actionTextColor) {
        this.mTitleTextColor = titleTextColor;
        this.mActionTextColor = actionTextColor;
        return this;
    }

    //Personalización del backgroun del snackbar
    public GenericSnackBar backgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
        return this;
    }

    //Cambio de la duración del snackbar
    public GenericSnackBar duration(SnackBarDuration snackBarDuration) {
        this.mSnackBarDuration = snackBarDuration;
        return this;
    }

    //Cambiar la fuente del título
    public GenericSnackBar customTitleFont(Typeface tf){
        this.mTfTitle = tf;
        return this;
    }

    //Cambia la fuente de la acción
    public GenericSnackBar customActionFont(Typeface tf){
        this.mTfAction = tf;
        return this;
    }


    public GenericSnackBar setIconForTitle(Drawable titleDrawable, IconPosition iconPostion, int drawablePadding){
        this.mTitleIconDrawable = titleDrawable;
        this.mIconPositionTitle = iconPostion;
        this.mIconTitlePadding = drawablePadding;
        return this;
    }

    public GenericSnackBar setIconForAction(Drawable actionDrawable, IconPosition iconPostion, int drawablePadding){
        this.mActionIconDrawable = actionDrawable;
        this.mIconPositionAction = iconPostion;
        this.mIconActionPadding = drawablePadding;
        return this;
    }

    public Snackbar getSnackBar(boolean showTop) {

        int duration = 0;

        switch (mSnackBarDuration) {

            case SHORT:
                duration = Snackbar.LENGTH_SHORT;
                break;

            case LONG:
                duration = Snackbar.LENGTH_LONG;
                break;

            case INDEFINITE:
                duration = Snackbar.LENGTH_INDEFINITE;
                break;
        }


        Snackbar snackbar = Snackbar
                .make(mView, mTitleText, duration);



        View sbView        = snackbar.getView();
        TextView txtTitle  = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        TextView txtAction = sbView.findViewById(com.google.android.material.R.id.snackbar_action);


        // Cambia el color del texto del mensaje
        txtTitle.setTextColor(mTitleTextColor);


        // Cambia el color del texto de la acción
        txtAction.setTextColor(mActionTextColor);

        //Cambiar el background del snackbar
        mBackgroundColor = context.getResources().getColor(booleanBackground == 0?R.color.yellow_dark:R.color.yellow_dark);
        sbView.setBackgroundColor(mBackgroundColor);
        sbView.setAlpha(0.8f);

        if(showTop){
            try{
                CoordinatorLayout.LayoutParams params =(CoordinatorLayout.LayoutParams)sbView.getLayoutParams();
                params.gravity = Gravity.TOP;
//                params.height  = Constants.dpToPx(18);
                sbView.setLayoutParams(params);
            }catch (ClassCastException e){
                FrameLayout.LayoutParams params=(FrameLayout.LayoutParams)sbView.getLayoutParams();
                params.gravity = Gravity.TOP;
//                params.height  = Constants.dpToPx(18);
                sbView.setLayoutParams(params);
            }

            txtTitle.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        //Cambia el estilo del título de texto
        txtTitle.setTypeface(mTfTitle);

        //Cambia el estilo de la acción
        txtAction.setTypeface(mTfAction);

        //setIcon(txtTitle,mTitleIconDrawable,mIconPositionTitle, Gravity.CENTER_VERTICAL, mIconTitlePadding);

        //setIcon(txtAction,mActionIconDrawable,mIconPositionAction,Gravity.CENTER,mIconActionPadding);




        return snackbar;
    }

    private void setIcon(TextView textView, Drawable drawable, IconPosition iconPosition, int gravity, int iconPadding) {

        textView.setGravity(gravity);
        textView.setCompoundDrawablePadding(iconPadding);
        switch (iconPosition){

            case LEFT:

                textView.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
                break;

            case RIGHT:
                textView.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);

                break;

            case TOP:
                textView.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);

                break;

            case BOTTOM:
                textView.setCompoundDrawablesWithIntrinsicBounds(null,null,null,drawable);

                break;
        }
    }

    public static Drawable generateDrawableBasedOnVersions(int code, Resources resources){
        if(code == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return resources.getDrawable(0, null);
            } else {
                return resources.getDrawable(0);
            }
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return resources.getDrawable(0, null);
            } else {
                return resources.getDrawable(0);
            }
        }
    }

    /**
     * Muestra el UI del snackbar
     * @param showTop parámetro que sirve para mostrar el Snackbar arriba si es True en caso
     *                contrario se mostrará abajo del View
     */
    public void show(boolean showTop) {
        getSnackBar(showTop).show();
    }
}

