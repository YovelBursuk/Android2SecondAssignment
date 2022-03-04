package com.example.myapplicationrecyclerview.Swiper

import android.view.MotionEvent
import android.view.View

class SwipeDetector : View.OnTouchListener {
    // not in use - for Future goals
    enum class Action {
        LR,  // Left to Right
        RL,  // Right to Left
        TB,  // Top to bottom
        BT,  // Bottom to Top
        None // when no action was detected
    }

    private val logTag = "SwipeDetector"
    private val MIN_DISTANCE = 75
    private var downX = 0f
    private var downY = 0f
    private var upX = 0f
    private var upY= 0f
    private var mSwipeDetected = Action.None

    fun swipeDetected(): Boolean {
        return mSwipeDetected != Action.None
    }

    fun getAction(): Action? {
        return mSwipeDetected
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                mSwipeDetected = Action.None
                return false // allow other events like Click to be processed
            }
            MotionEvent.ACTION_MOVE -> {
                upX = event.x
                upY = event.y
                val deltaX: Float = downX - upX
                val deltaY: Float = downY - upY

                // horizontal swipe detection
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // left or right
                    if (deltaX < 0) {
                        //   Logger.show(Log.INFO, logTag, "Swipe Left to Right");
                        mSwipeDetected = Action.LR
                        return true
                    }
                    if (deltaX > 0) {
                        //      Logger.show(Log.INFO,logTag, "Swipe Right to Left");
                        mSwipeDetected = Action.RL
                        return true
                    }
                } else  // vertical swipe detection
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        // top or down
                        if (deltaY < 0) {
                            //           Logger.show(Log.INFO,logTag, "Swipe Top to Bottom");
                            mSwipeDetected = Action.TB
                            return false
                        }
                        if (deltaY > 0) {
                            //          Logger.show(Log.INFO,logTag, "Swipe Bottom to Top");
                            mSwipeDetected = Action.BT
                            return false
                        }
                    }
                return true
            }
        }
        return false
    }
}