package widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import com.kwon.mylgproject.R

class CustomButton(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        attrs?.let { attr ->
            initAttrs(context, attr)
        }
    }

    lateinit var imageView: AppCompatImageButton
    lateinit var textView: AppCompatTextView
    private lateinit var defaultColor: String
    private lateinit var focusColor: String
    private lateinit var pressedColor: String
    private lateinit var disableColor: String
    private var checkItemSelector: Boolean = false
    private val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

    @SuppressLint("ResourceType")
    private fun initAttrs(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LgButton)
        val btnSelector = typedArray.getResourceId(R.styleable.LgButton_btn_selector, 0)
        val btnFocus = typedArray.getBoolean(R.styleable.LgButton_btn_focus, false)
        val btnIcon = typedArray.getResourceId(R.styleable.LgButton_btn_icon, 0)
        val btnItemSelector = typedArray.getResourceId(R.styleable.LgButton_btn_item_selector, 0)
        val btnTextStyle = typedArray.getResourceId(R.styleable.LgButton_btn_text_style, 0)
        val btnTextColor = typedArray.getColor(R.styleable.LgButton_btn_text_color, Color.parseColor("#000000"))
        val btnIconPosition = typedArray.getString(R.styleable.LgButton_btn_icon_position)

        orientation = HORIZONTAL
        gravity = Gravity.CENTER

        imageView = AppCompatImageButton(context)
        textView = AppCompatTextView(context)

        setBackgroundResource(btnSelector)

        isClickable = true
        if(btnFocus) {
            isFocusable = true
            isFocusableInTouchMode = true
        }

        if(typedArray.getString(R.styleable.LgButton_btn_text) != null) {
            val textViewLayout = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            textView.layoutParams = textViewLayout
            textView.text = typedArray.getString(R.styleable.LgButton_btn_text)
            textView.setTextAppearance(btnTextStyle)
            textView.setTextColor(btnTextColor)
            textView.isClickable = false
            textView.isFocusable = false
            textView.isFocusableInTouchMode = false

            if(btnIcon != 0) {
                textViewLayout.marginStart = 8.dp
                textViewLayout.marginEnd = 8.dp
                textView.textAlignment = TEXT_ALIGNMENT_TEXT_START
            } else {
                textViewLayout.marginStart = 0
                textViewLayout.marginEnd = 0
                textView.textAlignment = TEXT_ALIGNMENT_CENTER
            }
        }

        if(btnIcon != 0) {
            val imageViewLayout = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            imageView.layoutParams = imageViewLayout
            imageView.setImageResource(btnIcon)
            imageView.setBackgroundColor(Color.TRANSPARENT)
            imageView.adjustViewBounds = true
            imageView.isClickable = false
            imageView.isFocusable = false
            imageView.isFocusableInTouchMode = false
        }

        if(btnIcon != 0 && btnIconPosition == "End") {
            addView(textView)
            addView(imageView)
        } else {
            addView(imageView)
            addView(textView)
        }

        if(btnItemSelector != 0) {
            checkItemSelector = true
            setColorSelector(btnItemSelector)
        } else {
            checkItemSelector = false
        }

        typedArray.recycle()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()

        if(checkItemSelector) {
            imageView.imageTintList = ColorStateList.valueOf(Color.parseColor(defaultColor))
            textView.setTextColor(Color.parseColor(defaultColor))

            if(isFocused) {
                imageView.imageTintList = ColorStateList.valueOf(Color.parseColor(focusColor))
                textView.setTextColor(Color.parseColor(focusColor))
            }
            if(isPressed) {
                imageView.imageTintList = ColorStateList.valueOf(Color.parseColor(pressedColor))
                textView.setTextColor(Color.parseColor(pressedColor))
            }
            if(!isEnabled) {
                imageView.imageTintList = ColorStateList.valueOf(Color.parseColor(disableColor))
                textView.setTextColor(Color.parseColor(disableColor))
            }
        }
    }

    private fun setColorSelector(id: Int) {
        parseColorXml(resources.getXml(id))
    }

    private fun parseColorXml(parser: XmlResourceParser) {
        var eventType = -1
        val namespace = "http://schemas.android.com/apk/res/android"

        while (eventType != XmlResourceParser.END_DOCUMENT) {
            if (eventType == XmlResourceParser.START_TAG) {
                if (parser.name == "item") {
                    val stateFocused: String? = parser.getAttributeValue(namespace, "state_focused")
                    val stateEnabled: String? = parser.getAttributeValue(namespace, "state_enabled")
                    val statePressed: String? = parser.getAttributeValue(namespace, "state_pressed")
                    val color = resources.getString(parser.getAttributeResourceValue(namespace, "color", 0))

                    if(color != "0") {
                        if(stateFocused != null) {
                            focusColor = color
                        }
                        if(stateEnabled != null) {
                            defaultColor = color
                        } else {
                            disableColor = color
                        }
                        if(statePressed != null) {
                            pressedColor = color
                        }
                    }
                }
            }
            eventType = parser.next()
        }
    }

}