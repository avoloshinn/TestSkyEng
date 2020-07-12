package com.skyeng.test.ui.list

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.skyeng.test.R

/**
 * Created on 18.01.2019 by Andrey Voloshin.
 */
class DividerDecoration(
    context: Context,
    private val padding: Int = 0,
    colorResource: Int = R.color.colorDivider,
    private val includeLastItem: Boolean = true,
    private val correctItemId: Int? = null) : RecyclerView.ItemDecoration() {


    private val paint: Paint

    init {
        val paint = Paint()
        val color = ContextCompat.getColor(context, colorResource)
        val size = context.resources.getDimensionPixelSize(R.dimen.divider_size)

        this.paint = paint.apply {
            this.strokeWidth = size.toFloat()
            this.color = color
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var i = 0
        var y: Int
        var child: View
        var params: RecyclerView.LayoutParams
        val left = parent.paddingLeft + padding
        val right = parent.width - (parent.paddingRight + padding)
        val length = parent.childCount - (if (includeLastItem) 0 else 1)

        while (i < length) {
            child = parent.getChildAt(i++)
            if (correctItemId != null && child.id != correctItemId) {
                continue
            }

            params = child.layoutParams as RecyclerView.LayoutParams
            y = child.bottom + params.bottomMargin

            c.drawLine(left.toFloat(), y.toFloat(), right.toFloat(), y.toFloat(), paint)
        }
    }
}