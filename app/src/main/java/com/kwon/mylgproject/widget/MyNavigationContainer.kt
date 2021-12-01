package com.kwon.mylgproject.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import com.kwon.mylgproject.R

class MyNavigationContainer(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        context?.let { con ->
            attrs?.let { attr ->
                initAttrs(con, attr)
            }
        }
    }
    var action:String = ""
    private fun child() {
        for(v in children) {
            (v as MyNavigationView).layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1F)
            action = v.action
            (v as MyNavigationView).setOnClickListener {
                onclick?.invoke(v.action)
            }
        }
    }
    private fun initAttrs(context: Context, attrs: AttributeSet) {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyNavigationContainer)

        typedArray.recycle()
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        child()
    }
    var onclick: (action: String) -> Unit = {

    }

}