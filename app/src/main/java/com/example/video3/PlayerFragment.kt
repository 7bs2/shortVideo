package com.example.video3

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PlayerFragment(private val url: String) : Fragment() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaPlayer.apply {
            progressBar.visibility = View.VISIBLE
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener() {
                progressBar2.max = duration
                it.start()
                progressBar.visibility = View.INVISIBLE
            }
        }


        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                mediaPlayer.setDisplay(holder)
                mediaPlayer.setScreenOnWhilePlaying(true)
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }
        })

        lifecycleScope.launch {
            while (true) {
                progressBar2.progress = mediaPlayer.currentPosition
                delay(500)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
    }


    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

}