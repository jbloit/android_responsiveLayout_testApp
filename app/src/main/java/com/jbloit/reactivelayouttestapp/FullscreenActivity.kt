package com.jbloit.reactivelayouttestapp

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

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    val TAG = "FULLSCREEN"

    private var viewsCount = 8
    private var compoundViews: ArrayList<CompoundView> = ArrayList(viewsCount)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val v = findViewById<CompoundView>(R.id.compound1)
        v?.x = 500.toFloat()
        v?.y = 500.toFloat()
//        v?.layoutParams = ViewGroup.LayoutParams(500, 500)
        v?.layoutParams?.width = 100
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

}
