package com.jbloit.reactivelayouttestapp

import android.content.Context
import android.graphics.Canvas
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout

/**
 * Created by bloit on 13/06/2018.
 */

// see https://medium.com/@elye.project/building-custom-component-with-kotlin-fc082678b080

class CompoundView @JvmOverloads constructor (
        context: Context,
        attrs: AttributeSet? = null,
        defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleRes){
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_compound, this, true)

    }
}