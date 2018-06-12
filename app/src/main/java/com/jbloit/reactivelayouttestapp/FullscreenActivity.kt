package com.jbloit.reactivelayouttestapp

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_fullscreen.*
import javax.security.auth.callback.Callback

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {


    val TAG = "FULLSCREEN"

    private var viewsCount = 8
    private var videoViews: ArrayList<SurfaceView> = ArrayList(viewsCount)
    private var mediaPlayers: ArrayList<MediaPlayer> = ArrayList(viewsCount)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)
        seekBar_imageCount.setOnSeekBarChangeListener(this)
        videoViews.add(surface1)
        videoViews.add(surface2)
        videoViews.add(surface3)
        videoViews.add(surface4)


        for (i in 0..3){
            val mp = initMediaPlayer()
            mediaPlayers.add(mp)
        }

        for (i in 0..3){
            videoViews[i].holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                    Log.v(TAG, "surfaceChanged format=" + format + ", width=" + width + ", height=" + height)
                }

                override fun surfaceCreated(holder: SurfaceHolder) {
                    Log.v(TAG, "surfaceCreated")
                    mediaPlayers[i].setSurface(holder.surface)
                    prepareMediaPlayer(mediaPlayers[i])
                }

                override fun surfaceDestroyed(holder: SurfaceHolder) {
                    Log.v(TAG, "surfaceDestroyed")
                }
            })
        }
    }

    private fun initMediaPlayer(): MediaPlayer{

        val mediaPlayer = MediaPlayer()
        val uri = Uri.parse("android.resource://"
                + packageName + "/raw/musette_simple_contrebasse")

        try {
            mediaPlayer.setDataSource(this, uri)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        return mediaPlayer
    }

    // SEEKBAR CALLBACKS
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (progress < 3){
            guideline_margin1.setGuidelinePercent(1.toFloat())
        } else {
            guideline_margin1.setGuidelinePercent(0.5.toFloat())
        }

        for (i in 0..3){
            videoViews[i].visibility = ImageView.GONE
        }
        for (i in 1..progress){
            Log.d(TAG, "progress : $i")
            videoViews[i-1]?.visibility = ImageView.VISIBLE
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }


    // move from initialized to prepared state
    private fun prepareMediaPlayer(mediaPlayer: MediaPlayer) {
        try {
            mediaPlayer.prepareAsync()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }

        mediaPlayer.setOnPreparedListener{
            mediaPlayer.seekTo(0)
            mediaPlayer.start()
        }

        // get w & h of video to resize surface
//        mediaPlayer.setOnVideoSizeChangedListener { player, width, height ->
//            setSurfaceDimensions(player, width, height)
//        }
    }

}
