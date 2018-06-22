package com.jbloit.reactivelayouttestapp

import android.graphics.Rect
import android.graphics.RectF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_fullscreen.*
import kotlinx.android.synthetic.main.layout_compound.view.*
import java.lang.Math.floor

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */



class FullscreenActivity : AppCompatActivity(){

    val TAG = "FULLSCREEN"

    private var viewsCount = 8
    private var compoundViews: ArrayList<CompoundView> = ArrayList(viewsCount)

    private var visibleViewsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)

        compound1.textView.text = "V1"
        compound2.textView.text = "V2"
        compound3.textView.text = "V3"
        compound4.textView.text = "V4"
        compound5.textView.text = "V5"
        compound6.textView.text = "V6"
        compound7.textView.text = "V7"
        compound8.textView.text = "V8"

        compoundViews.add(compound1)
        compoundViews.add(compound2)
        compoundViews.add(compound3)
        compoundViews.add(compound4)
        compoundViews.add(compound5)
        compoundViews.add(compound6)
        compoundViews.add(compound7)
        compoundViews.add(compound8)

        for (i in 0..(compoundViews.size - 1)){
            compoundViews[i]?.visibility = View.GONE
        }

        buttonAdd.setOnClickListener{
            if (visibleViewsCount<compoundViews.size) {
                visibleViewsCount += 1
                updateViewPositions()
            }
        }

        buttonRemove.setOnClickListener{
            if (visibleViewsCount > 0) {
                visibleViewsCount -= 1
                updateViewPositions()
            }
        }

    }

    fun updateViewPositions(){



        val layoutArray: ArrayList<Rect>? = getLayout(visibleViewsCount)

        for(i in 0..(compoundViews.size - 1)){
            // update visibility
            if (i < visibleViewsCount ) {

                if (layoutArray != null) {
                    compoundViews[i]?.visibility = View.VISIBLE
                    compoundViews[i].x = layoutArray[i].left.toFloat()
                    compoundViews[i].y = layoutArray[i].top.toFloat()
                    compoundViews[i].layoutParams?.width = layoutArray[i].right - layoutArray[i].left
                    compoundViews[i].layoutParams?.height = layoutArray[i].bottom - layoutArray[i].top
                }
            }
            else {
                compoundViews[i]?.visibility = View.GONE
            }
        }

    }

    fun getLayout(N: Int): ArrayList<Rect>?{

        if (N < 1){
            return null
        }

        val aspectRatio = 16/9
        var returnArray = ArrayList<Rect>(N)

        for (i in 0..(N-1)){
            returnArray.add(Rect(0,0,50,50))
        }

        val frameX = 0
        val frameY = 100
        val frameW = window.decorView.width
        val frameH = window.decorView.height - frameY

        val evenCount = (N % 2 == 0)

        val rectH = if (evenCount) frameH/(N/2) else frameH/((N+1)/2)
        val rectW = aspectRatio * rectH

        if (evenCount){
            for (i in 0..(N-1)){
                returnArray[i].left = frameX + rectW * (i % 2)
                returnArray[i].right = returnArray[i].left + rectW
                returnArray[i].top = frameY + rectH * floor(i.toDouble() / 2).toInt()
                returnArray[i].bottom = returnArray[i].top + rectH
            }
        } else
        {
            for (i in 0..(N-1)) {
                if (i == 0) {
                    returnArray[i].left = frameW/2 - rectW/2
                    returnArray[i].right = frameW/2 + rectW/2
                    returnArray[i].top = frameY
                    returnArray[i].bottom = frameY + rectH
                } else {
                    returnArray[i].left = frameX + rectW * ((i-1) % 2)
                    returnArray[i].right = returnArray[i].left + rectW
                    returnArray[i].top = frameY + rectH + rectH * floor((i-1).toDouble() / 2).toInt()
                    returnArray[i].bottom = returnArray[i].top + rectH
                }
            }
        }
        return returnArray
    }


    fun showNviews(N: Int){

        val frameX = 0
        val frameY = 100
        val frameW = window.decorView.width
        val frameH = window.decorView.height - frameY


        for(i in 0..(compoundViews.size - 1)){

            if (i < N ) {

                compoundViews[i]?.visibility = View.VISIBLE
                compoundViews[i]?.layoutParams?.width = frameW / N
                compoundViews[i]?.layoutParams?.height = frameH / N
            } else {
                compoundViews[i]?.visibility = View.GONE
            }
        }
    }

    fun moveImage(progress: Int){
        val v = findViewById<CompoundView>(R.id.compound1)
        v?.x = (progress*5).toFloat()
        v?.y = 500.toFloat()
//        v?.layoutParams = ViewGroup.LayoutParams(500, 500)
        v?.layoutParams?.width = 100
        v?.layoutParams?.height = 100
    }
}
