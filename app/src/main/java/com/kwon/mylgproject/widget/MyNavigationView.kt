package com.kwon.mylgproject.widget

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.XmlResourceParser
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Checkable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import com.kwon.mylgproject.R

class MyNavigationView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs), Checkable {

    init {
        context?.let { con ->
            attrs?.let { attr ->
                initAttrs(con, attr)
            }
        }
    }

    lateinit var action: String
    lateinit var imageView: ImageView
    lateinit var textView:TextView
    lateinit var defaultColor: String
    lateinit var focusColor: String
    lateinit var disableColor: String

    private fun initAttrs(context: Context, attrs: AttributeSet) {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyNavigationView)

        action = typedArray.getString(R.styleable.MyNavigationView_nv_action)!!

        val imageView = AppCompatImageButton(context)
        imageView.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1F)
        imageView.adjustViewBounds = true
        imageView.setImageResource(typedArray.getResourceId(R.styleable.MyNavigationView_nv_image_src, 0))
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.isClickable = false
        this.imageView = imageView
        
        addView(imageView)

        val text = typedArray.getString(R.styleable.MyNavigationView_nv_text)
        textView = TextView(context)
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        textView.text = text

        textView.includeFontPadding = false
        addView(textView)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun itemInit(view: MyNavigationView) {
        view.isChecked = false
    }

    fun setTextStyle(id:Int) {
        textView.setTextAppearance(id)
    }

    fun setItemSelector(id: Int) {
        parseXml(resources.getXml(id))
    }

    private fun parseXml(parser: XmlResourceParser) {
        var eventType = -1
        val namespace = "http://schemas.android.com/apk/res/android"

        while (eventType != XmlResourceParser.END_DOCUMENT) {
            if (eventType == XmlResourceParser.START_TAG) {
                if (parser.name == "item") {
                    val stateFocused: String? = parser.getAttributeValue(namespace, "state_focused")
                    val color = resources.getString(parser.getAttributeResourceValue(namespace, "drawable", 0))

                    if(color != "0") {
                        if(stateFocused == null) {
                            val stateEnabled: String? = parser.getAttributeValue(namespace, "state_enabled")
                            if(stateEnabled == null) return else disableColor = color
                        } else {
                            if(stateFocused == "true") focusColor = color else defaultColor = color
                        }
                    }
                }
            }
            eventType = parser.next()
        }
    }

    interface OnCheckedChangeListener {
        fun onCheckedChanged(checkableView: View?, isChecked: Boolean)
    }

    private val checkedStateSet = intArrayOf(android.R.attr.state_checked)
    private var mChecked = false
    private var mOnCheckedChangeListener: OnCheckedChangeListener? = null

    override fun isChecked(): Boolean {
        return mChecked
    }

    override fun setChecked(checked: Boolean) {
        if (checked != mChecked) {
            if(checked) {
                imageView.imageTintList = ColorStateList.valueOf(Color.parseColor(focusColor))
                textView.setTextColor(Color.parseColor(focusColor))
            } else {
                imageView.imageTintList = ColorStateList.valueOf(Color.parseColor(defaultColor))
                textView.setTextColor(Color.parseColor(defaultColor))
            }

            mChecked = checked
            refreshDrawableState()
            if (mOnCheckedChangeListener != null) {
                mOnCheckedChangeListener!!.onCheckedChanged(this, mChecked)
            }
        }
    }

    override fun toggle() {
        isChecked = !mChecked
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray? {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            mergeDrawableStates(drawableState, checkedStateSet)
        }
        return drawableState
    }

    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        mOnCheckedChangeListener = listener
    }
}