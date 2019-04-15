package com.eaj.ufrn.mobilemilk.Gesture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

public class MeuRecyclerViewClickListener implements RecyclerView.OnItemTouchListener{

    OnItemClickListener myListener;

    // Interface do listener .....
    public interface OnItemClickListener{
        void onItemLongClick(View view, int position);
        void onItemClick(View view, int position);
    }

    // constructor
    public MeuRecyclerViewClickListener(Context context, final RecyclerView view, OnItemClickListener listener){

    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

}
