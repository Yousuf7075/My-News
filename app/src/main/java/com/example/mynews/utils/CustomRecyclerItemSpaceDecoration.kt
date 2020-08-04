/*
 * Copyright (C) 2017 NURDCODER
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://nurdcoder.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package com.example.mynews.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import java.util.*

/**
 * Created by Mohammad Yousuf on August, 2020
 *
 * */
class CustomRecyclerItemSpaceDecoration : ItemDecoration {
    private var mVerticalSpace = 0
    private var mHorizontalSpace = 0
    private var mVerticalStartSpace = 0
    private var mVerticalEndSpace = 0
    private var mHorizontalStartSpace = 0
    private var mHorizontalEndSpace = 0

    constructor() {}
    constructor(
        mSpaceTop: Int,
        mSpaceBottom: Int,
        mSpaceStart: Int,
        mSpaceEnd: Int,
        mItemsVerticalSpace: Int,
        mItemsHorizontalSpace: Int
    ) {
        mVerticalSpace = mItemsVerticalSpace
        mHorizontalSpace = mItemsHorizontalSpace
        mVerticalStartSpace = mSpaceTop
        mVerticalEndSpace = mSpaceBottom
        mHorizontalStartSpace = mSpaceStart
        mHorizontalEndSpace = mSpaceEnd
    }

    fun setVerticalSpace(mVerticalSpace: Int) {
        this.mVerticalSpace = mVerticalSpace
    }

    fun setHorizontalSpace(mHorizontalSpace: Int) {
        this.mHorizontalSpace = mHorizontalSpace
    }

    fun setVerticalStartSpace(mVerticalStartSpace: Int) {
        this.mVerticalStartSpace = mVerticalStartSpace
    }

    fun setVerticalEndSpace(mVerticalEndSpace: Int) {
        this.mVerticalEndSpace = mVerticalEndSpace
    }

    fun setHorizontalStartSpace(mHorizontalStartSpace: Int) {
        this.mHorizontalStartSpace = mHorizontalStartSpace
    }

    fun setHorizontalEndSpace(mHorizontalEndSpace: Int) {
        this.mHorizontalEndSpace = mHorizontalEndSpace
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        when (resolveDisplayMode(parent.layoutManager)) {
            VERTICAL -> {
                outRect.left = mHorizontalStartSpace
                outRect.right = mHorizontalEndSpace
                outRect.bottom = mVerticalSpace
                if (position == 0) {
                    outRect.top = mVerticalStartSpace
                }
                if (position == itemCount - 1) {
                    outRect.bottom = mVerticalEndSpace
                }
            }
            HORIZONTAL -> {
                outRect.top = mVerticalStartSpace
                outRect.bottom = mVerticalEndSpace
                outRect.right = mHorizontalSpace
                if (position == 0) {
                    outRect.left = mHorizontalStartSpace
                }
                if (position == itemCount - 1) {
                    outRect.right = mHorizontalEndSpace
                }
            }
            GRID -> {
                val spanCount =
                    (Objects.requireNonNull(parent.layoutManager) as GridLayoutManager).spanCount
                outRect.bottom = mVerticalSpace
                outRect.right = mHorizontalSpace
                if (position < spanCount) {
                    outRect.top = mVerticalStartSpace
                }
                if (position % spanCount == 0) {
                    outRect.left = mHorizontalStartSpace
                }
                if (position % spanCount == spanCount - 1) {
                    outRect.right = mHorizontalEndSpace
                }
                if (position >= itemCount - spanCount) {
                    outRect.bottom = mVerticalEndSpace
                }
            }
        }
    }

    private fun resolveDisplayMode(layoutManager: RecyclerView.LayoutManager?): Int {
        if (layoutManager is GridLayoutManager) return GRID
        return if (layoutManager!!.canScrollHorizontally()) HORIZONTAL else VERTICAL
    }

    companion object {
        private const val VERTICAL = 0
        private const val HORIZONTAL = 1
        private const val GRID = 2
    }
}