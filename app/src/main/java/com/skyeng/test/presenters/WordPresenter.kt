package com.skyeng.test.presenters

import com.github.piasy.rxandroidaudio.PlayConfig
import com.github.piasy.rxandroidaudio.RxAudioPlayer
import com.skyeng.test.SchedulersFacade
import com.skyeng.test.ui.word.WordView
import javax.inject.Inject

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
abstract class WordPresenter : BasePresenter<WordView>() {
    abstract fun loadSound(url: String)
}

class WordPresenterImpl @Inject constructor(
    private val schedulersFacade: SchedulersFacade
) : WordPresenter() {

    private var view: WordView? = null

    override fun loadSound(url: String) {
        disposables.add(
            RxAudioPlayer.getInstance()
                .play(PlayConfig.url(url).build())
                .subscribeOn(schedulersFacade.ui())
                .subscribe()
        )
    }

    override fun attachView(view: WordView) {
        this.view = view
    }

    override fun detachView() {
        view = null
        stopSubscriptions()
    }
}