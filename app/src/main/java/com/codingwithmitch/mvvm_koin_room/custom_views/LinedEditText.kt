package com.codingwithmitch.mvp_room_koin.custom_views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class LinedEditText(
    context: Context?,
    attrs: AttributeSet?
) :
    AppCompatEditText(context, attrs) {

    private val rect: Rect

    private val paint: Paint

    override fun onDraw(canvas: Canvas) { // get the height of the view
        val height = (this.getParent() as View).height
        val lineHeight: Int = getLineHeight()
        val numberOfLines = height / lineHeight
        val r = rect
        val paint = paint
        var baseline: Int = getLineBounds(0, r)
        for (i in 0 until numberOfLines) {
            canvas.drawLine(
                r.left.toFloat(),
                baseline + 1.toFloat(),
                r.right.toFloat(),
                baseline + 1.toFloat(),
                paint
            )
            baseline += lineHeight
        }
        super.onDraw(canvas)
    }

    companion object {
        private const val TAG = "LinedEditText"
    }

    // we need this constructor for LayoutInflater
    init {
        rect = Rect()
        paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f
        paint.color = -0x269a // Color of the lines on paper
    }
}