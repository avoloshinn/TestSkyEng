package com.skyeng.test.presenters

import com.skyeng.test.SchedulersFacade
import com.skyeng.test.ui.word.WordView
import javax.inject.Inject

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
abstract class WordPresenter : BasePresenter() {

}

class WordPresenterImpl @Inject constructor(
    private val view: WordView,
    private val schedulersFacade: SchedulersFacade
) : WordPresenter() {

}