package com.kwon.mylgproject.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
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

    private var nvTextStyles: Int = 0
    private var nvItemSelector: Int = 0
    private var viewList = mutableListOf<View>()

    private fun child() {
        for(v in children) {
            viewList.add(v)
            (v as MyNavigationView).layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1F)
            v.setTextStyle(nvTextStyles)
            v.setItemSelector(nvItemSelector)
            (v as MyNavigationView).setOnClickListener {
                for(vList in viewList) { v.itemInit((vList as MyNavigationView)) }
                if(v.isChecked) v.toggle() else v.toggle()
                onItemClick?.invoke(v.action)
            }
        }
    }
    private fun initAttrs(context: Context, attrs: AttributeSet) {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyNavigationContainer)

        nvTextStyles = typedArray.getResourceId(R.styleable.MyNavigationContainer_nv_text_styles, 0)
        nvItemSelector = typedArray.getResourceId(R.styleable.MyNavigationContainer_nv_item_selector, 0)

        typedArray.recycle()
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        child()
    }

    var onItemClick: (action: String) -> Unit = { }
}