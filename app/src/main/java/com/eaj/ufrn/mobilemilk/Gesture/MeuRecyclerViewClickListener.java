package com.eaj.ufrn.mobilemilk.Gesture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MeuRecyclerViewClickListener implements RecyclerView.OnItemTouchListener{

    OnItemClickListener myListener;
    GestureDetector myGestureDetector;

    // Interface do listener .....
    public interface OnItemClickListener{
        void onItemLongClick(View view, int position);
        void onItemClick(View view, int position);
    }

    // constructor
    public MeuRecyclerViewClickListener(Context context, final RecyclerView view, OnItemClickListener listener){
        this.myListener = listener;
        this.myGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
           @Override
           public boolean onSingleTapUp(MotionEvent e){
               super.onSingleTapUp(e);
               View childView = view.findChildViewUnder(e.getX(), e.getY());
               if(childView != null && myListener != null)
                   myListener.onItemClick(childView, view.getChildAdapterPosition(childView));
               return true;
           }
           @Override
           public void onLongPress(MotionEvent e){
               super.onLongPress(e);
               View childView = view.findChildViewUnder(e.getX(), e.getY());
               if(childView != null && myListener != null)
                   myListener.onItemLongClick(childView, view.getChildAdapterPosition(childView));
           }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        this.myGestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

}
