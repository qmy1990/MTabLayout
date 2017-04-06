package com.qmy.mtablayoutlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 邱明月 on 2017/4/6 0006.
 */

public class IconTabView extends LinearLayout implements ITabView {


    private boolean isSelected;

    private Drawable iconDrawable;

    private Drawable selectedDrawable;


    private ImageView iconView;

    private TextView textView;

    private float textSize;

    private int textSelectColor;

    private int textUnselectColor;

    private String text;


    public IconTabView(Context context) {
        this(context, null);
    }

    public IconTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
        setUpView(context);
    }


    private void initData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabView);

        text = typedArray.getString(R.styleable.TabView_tab_text);

        iconDrawable = typedArray.getDrawable(R.styleable.TabView_tab_icon);
        selectedDrawable = typedArray.getDrawable(R.styleable.TabView_tab_selected_icon);

        textSize = typedArray.getDimension(R.styleable.TabView_tab_text_size, DisplayUtils.sp2px(context, 12));
        textSelectColor = typedArray.getColor(R.styleable.TabView_tab_selected_text_color, Color.rgb(0xf1, 0x3d, 0x1c));
        textUnselectColor = typedArray.getColor(R.styleable.TabView_tab_unselected_text_color, Color.rgb(0x66, 0x66, 0x66));

        typedArray.recycle();
    }


    private void setUpView(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);


        if (iconDrawable != null) {
            iconView = new ImageView(context);
            iconView.setImageDrawable(iconDrawable);
            LayoutParams params = new LayoutParams(DisplayUtils.dp2px(context, 30), DisplayUtils.dp2px(context, 30), 0);
            iconView.setLayoutParams(params);
            addView(iconView);
        } else if (selectedDrawable != null) {
            iconView = new ImageView(context);
            iconView.setImageDrawable(selectedDrawable);
            LayoutParams params = new LayoutParams(DisplayUtils.dp2px(context, 30), DisplayUtils.dp2px(context, 30), 0);
            iconView.setLayoutParams(params);
            addView(iconView);
        }

        if (!TextUtils.isEmpty(text)) {
            textView = new TextView(context);
            textView.setText(text);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            textView.setTextColor(textUnselectColor);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0);
            textView.setLayoutParams(params);
            addView(textView);
        }


    }

    @Override
    public void setTabSelected(boolean isSelected) {
        if (this.isSelected == isSelected) {
            return;
        }
        if (isSelected) {
            tabSelect();
        }else{
            tabUnselect();
        }

    }

    @Override
    public boolean isTabSelected() {
        return isSelected;
    }

    private void tabSelect() {
        isSelected = true;
        if (iconView != null) {
            iconView.setImageDrawable(selectedDrawable);
        }
        if (textView != null) {
            textView.setTextColor(textSelectColor);
        }
    }

    private void tabUnselect() {
        isSelected = false;
        if (iconView != null) {
            iconView.setImageDrawable(iconDrawable);
        }
        if (textView != null) {
            textView.setTextColor(textUnselectColor);
        }
    }


}
