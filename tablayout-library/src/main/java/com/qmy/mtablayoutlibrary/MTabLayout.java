package com.qmy.mtablayoutlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Tab容器
 * Created by 邱明月 on 2017/3/17 0017.
 */

public class MTabLayout extends LinearLayout  implements ViewPager.OnPageChangeListener {

    /**
     *
     */
    private ViewPager mViewPager;

    /**
     *
     */
    private List<ITabView> mTabViewList;

    /**
     *
     */
    private int mSelectedPosition = 0;

    /**
     * 用于绘制下标指示器
     * */
    private Rect mIndicatorRect = new Rect();

    /**
     * 用于实现滚动居中
     * */
    private Rect mTabRect = new Rect();

    private GradientDrawable mIndicatorDrawable = new GradientDrawable();

    private Paint mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);



    /**
     *
     */
    private int mTabCount;


    public MTabLayout(Context context) {
        this(context, null);
    }

    public MTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        mTabViewList = new ArrayList<>();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomTabView);
        typedArray.getInt(R.styleable.BottomTabView_selectedPosition, 0);

        typedArray.recycle();
    }

    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null || viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager or ViewPager adapter can not be NULL !");
        }

        this.mViewPager = viewPager;
        mViewPager.removeOnPageChangeListener(this);
        mViewPager.addOnPageChangeListener(this);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mTabCount = 0;
        for (int i = 0; i < getChildCount(); i++){
            final int index = i;
            if (getChildAt(i) instanceof ITabView){
                mTabViewList.add((ITabView) getChildAt(i));
                getChildAt(i).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setSelectedPosition(index);
                    }
                });
                mTabCount++;
            }
        }
        if (mTabViewList.size() > 0 ){
            mTabViewList.get(mSelectedPosition).setTabSelected(true);
        }
    }


    private void setSelectedPosition(int position){
        if (mSelectedPosition != position){
            mTabViewList.get(mSelectedPosition).setTabSelected(false);
            mTabViewList.get(position).setTabSelected(true);
            mSelectedPosition = position;
            if (mViewPager != null){
                mViewPager.setCurrentItem(position);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mTabViewList.size() > position && mTabViewList.size() > mSelectedPosition){
            mTabViewList.get(mSelectedPosition).setTabSelected(false);
            mTabViewList.get(position).setTabSelected(true);
        }
        mSelectedPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
