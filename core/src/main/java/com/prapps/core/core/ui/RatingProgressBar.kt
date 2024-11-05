package com.prapps.core.core.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.prapps.core.R

class RatingProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var progress = 0f
    private var maxProgress = 100f
    private var progressColor = Color.GREEN
    private var backgroundColor = Color.WHITE
    private var strokeWidth = 20f

    private val backgroundPaint = Paint().apply {
        color = backgroundColor
        style = Paint.Style.STROKE
        strokeWidth = this@RatingProgressBar.strokeWidth
        isAntiAlias = true
    }

    private val progressPaint = Paint().apply {
        color = progressColor
        style = Paint.Style.STROKE
        strokeWidth = this@RatingProgressBar.strokeWidth
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        textSize = spToPx(12f)
        typeface = ResourcesCompat.getFont(context, R.font.poppinsblack)

        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }

    override fun setBackgroundColor(color: Int) {
        backgroundColor = color
        backgroundPaint.color = backgroundColor
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()
        val radius = (Math.min(width, height) / 2) - (strokeWidth / 2)

        canvas.drawCircle(width / 2, height / 2, radius, backgroundPaint)

        val sweepAngle = (progress / maxProgress) * 360
        canvas.drawArc(
            strokeWidth / 2,
            strokeWidth / 2,
            width - strokeWidth / 2,
            height - strokeWidth / 2,
            -90f,
            sweepAngle,
            false,
            progressPaint
        )

        val percentageText = "${(progress / maxProgress * 100).toInt()}%"
        textPaint.color = progressColor
        canvas.drawText(
            percentageText,
            width / 2,
            height / 2 - (textPaint.ascent() + textPaint.descent()) / 2,
            textPaint
        )
    }

    private fun updateProgressColor() {
        progressColor = when {
            progress / maxProgress >= 0.75 -> Color.GREEN
            progress / maxProgress >= 0.50 -> Color.YELLOW
            else -> Color.RED
        }
        progressPaint.color = progressColor
        textPaint.color = progressColor
    }

    fun setProgressWithAnimation(voteAveragePercentage: Float) {
        val animator = ValueAnimator.ofFloat(progress, voteAveragePercentage)
        animator.duration = 500
        animator.addUpdateListener { animation ->
            progress = (animation.animatedValue as Float)
            updateProgressColor()
            invalidate()
        }
        animator.start()
    }

    private fun spToPx(sp: Float): Float {
        return sp * context.resources.displayMetrics.scaledDensity
    }
}
