package com.kwon.mylgproject.widget

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.kwon.mylgproject.R

class MyNavigationView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        context?.let { con ->
            attrs?.let { attr ->
                initAttrs(con, attr)
            }
        }
    }

    lateinit var action: String

    private val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

    private fun initAttrs(context: Context, attrs: AttributeSet) {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyNavigationView)
        action = typedArray.getString(R.styleable.MyNavigationView_nv_action)!!

        val imageView = ImageView(context)
        imageView.layoutParams = LayoutParams(typedArray.getInteger(R.styleable.MyNavigationView_nv_image_width, 0).dp, typedArray.getInteger(R.styleable.MyNavigationView_nv_image_height, 0).dp)
        imageView.setImageResource(typedArray.getResourceId(R.styleable.MyNavigationView_nv_image_src, 0))
        addView(imageView)

        val text = typedArray.getString(R.styleable.MyNavigationView_nv_text)
        val textView = TextView(context)
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        textView.text = text
        textView.setTextAppearance(typedArray.getResourceId(R.styleable.MyNavigationView_nv_text_style, 0))
        textView.includeFontPadding = false
        addView(textView)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}