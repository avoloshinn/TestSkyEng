package com.skyeng.test.presenters

import com.skyeng.test.SchedulersFacade
import com.skyeng.test.interactors.SearchInteractor
import com.skyeng.test.ui.search.SearchView
import com.skyeng.test.ui.list.ViewType
import javax.inject.Inject

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
abstract class SearchPresenter : BasePresenter() {
    abstract fun searchTranslate(loadMore: Boolean, params: HashMap<String, Any>)
}

class SearchPresenterImpl @Inject constructor(
    private val view: SearchView,
    private val schedulersFacade: SchedulersFacade,
    private val interactor: SearchInteractor
) : SearchPresenter() {

    override fun searchTranslate(loadMore: Boolean, params: HashMap<String, Any>) {
        disposables.add(
            interactor.searchTranslate(params)
                .subscribeOn(schedulersFacade.io())
                .doOnSubscribe {
                    view.showProgress()
                }
                .doFinally {
                    view.hideProgress()
                }
                .observeOn(schedulersFacade.ui())
                .subscribe(
                    { response ->
                        if (!loadMore) {
                            view.showSearchedWords(
                                response as? MutableList<ViewType> ?: mutableListOf()
                            )
                        } else {
                            view.addData(response as? MutableList<ViewType> ?: mutableListOf())
                        }
                    },
                    { error ->
                        view.showError(error.localizedMessage ?: "Unknown error")
                    }
                )
        )
    }
}