package com.example.moviepediaa.Animation;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScaleupCenterItemLayoutManager2 extends LinearLayoutManager {

    public ScaleupCenterItemLayoutManager2(Context context) {
        super(context);
    }

    public ScaleupCenterItemLayoutManager2(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
        lp.width = getWidth()/3;

        return true;
    }

    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        scaleMiddleitem();
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int Scrolled = super.scrollHorizontallyBy(dx, recycler, state);

        scaleMiddleitem();
        if(getOrientation()==RecyclerView.HORIZONTAL)
            return Scrolled;
        else
            return 0;
    }

    private void scaleMiddleitem() {
        float mid = getWidth()/2.0f;
        float d1 = 0.9f*mid;
        for(int i=0;i<getChildCount();i++)
        {
            View child = getChildAt(i);
            float childmid = (getDecoratedRight(child)+ getDecoratedLeft(child))/2.0f;
            float d=Math.min(d1,Math.abs(mid-childmid));
            float scale = 1f-0.15f *d/d1;
            child.setScaleX(scale);
            child.setScaleY(scale);

        }

    }
}

