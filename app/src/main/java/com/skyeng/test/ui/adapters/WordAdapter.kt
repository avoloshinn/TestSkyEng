package com.skyeng.test.ui.adapters

import com.skyeng.test.ui.list.*

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
class WordAdapter(listener: OnItemClickedListener<ViewType>): PaginationAdapter(listener) {

    init {
        delegateAdapters[ListItemType.WORD.ordinal] = WordsDelegateAdapter(listener)
        delegateAdapters[ListItemType.PROGRESS.ordinal] = ProgressDelegateAdapter()
        delegateAdapters[ListItemType.RELOAD.ordinal] = ReloadDelegateAdapter(listener)
    }
}