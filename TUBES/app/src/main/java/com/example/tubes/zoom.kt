package com.example.tubes
import android.graphics.Matrix
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tubes.databinding.ZoomBinding
import kotlin.math.max
import kotlin.math.min

class ZoomActivity:AppCompatActivity(){
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor=1.0f
    private lateinit var imageView: ImageView

    private lateinit var binding: ZoomBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ZoomBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val imageView: ImageView = binding.cameraImage
        scaleGestureDetector = ScaleGestureDetector(this,ScaleListener())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return scaleGestureDetector.onTouchEvent(event)
        return true
    }

    private inner class ScaleListener:SimpleOnScaleGestureListener(){
        override fun onScale(scaleGestureDetector:ScaleGestureDetector):Boolean{
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = max(0.1f,min(scaleFactor,10.0f))

            val matrix = Matrix(imageView.imageMatrix)
            matrix.setScale(scaleFactor,scaleFactor)
            imageView.imageMatrix = matrix
            return true
        }
    }
}