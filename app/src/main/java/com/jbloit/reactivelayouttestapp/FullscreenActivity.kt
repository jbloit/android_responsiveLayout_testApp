package com.jbloit.reactivelayouttestapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_fullscreen.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    val TAG = "FULLSCREEN"

    private var viewsCount = 8
    private var imageViews: ArrayList<ImageView> = ArrayList(viewsCount)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)
        seekBar_imageCount.setOnSeekBarChangeListener(this)

        imageViews.add(img1)
        imageViews.add(img2)
        imageViews.add(img3)
        imageViews.add(img4)


    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (progress < 3){
            guideline_margin1.setGuidelinePercent(1.toFloat())
        } else {
            guideline_margin1.setGuidelinePercent(0.5.toFloat())
        }

        for (i in 0..3){
            imageViews[i].visibility = ImageView.GONE
        }
        for (i in 1..progress){
            Log.d(TAG, "progress : $i")
            imageViews[i-1]?.visibility = ImageView.VISIBLE
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

}
