package com.zows.hubxrecruitmentcase.presentation.paywall

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpacingItemDecoration(private val space: Int) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = space
    }
}

class TwoColumnItemDecoration(
    private val spanCount: Int,
    private val spacing: Int
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % 2
        val lastRowStartIndex = (state.itemCount - 1) / spanCount * spanCount

        outRect.left = if (column == 0) 0 else spacing
        outRect.right = if (column == 0) spacing else 0

        if (position >= 2) outRect.top = spacing
        if (position >= lastRowStartIndex) outRect.bottom = 100 else outRect.bottom = spacing
    }
}