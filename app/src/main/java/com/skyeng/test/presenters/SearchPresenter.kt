package com.skyeng.test.presenters

import com.skyeng.test.SchedulersFacade
import com.skyeng.test.interactors.SearchInteractor
import com.skyeng.test.ui.search.SearchView
import com.skyeng.test.utils.Constants
import javax.inject.Inject

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
abstract class SearchPresenter : BasePresenter<SearchView>() {
    abstract fun searchTranslate(loadMore: Boolean, search: String)
    abstract fun afterTextChanged(text: String)
}

class SearchPresenterImpl @Inject constructor(
    private val schedulersFacade: SchedulersFacade,
    private val interactor: SearchInteractor
) : SearchPresenter() {

    private var view: SearchView? = null
    private var page: Int = 0

    override fun searchTranslate(loadMore: Boolean, search: String) {
        page += 1
        val params = hashMapOf<String, Any>()
        params["pageSize"] = Constants.ITEMS_PER_PAGE
        params["search"] = search
        params["page"] = page

        disposables.add(
            interactor.searchTranslate(params)
                .subscribeOn(schedulersFacade.io())
                .doOnSubscribe {
                    view?.showProgress()
                }
                .doFinally {
                    view?.hideProgress()
                }
                .observeOn(schedulersFacade.ui())
                .subscribe(
                    { response ->
                        if (!loadMore) {
                            view?.showSearchedWords(response.map { it.produceEntity() }
                                .toMutableList())
                        } else {
                            view?.addData(response.map { it.produceEntity() }.toMutableList())
                        }
                    },
                    { error ->
                        view?.showError(error.localizedMessage ?: "Unknown error")
                    }
                )
        )
    }

    override fun afterTextChanged(text: String) {
        if (text.isEmpty()) {
            view?.showSearchedWords(mutableListOf())
        } else {
            page = 0
            searchTranslate(false, text)
        }
    }

    override fun attachView(view: SearchView) {
        this.view = view
    }

    override fun detachView() {
        view = null
        stopSubscriptions()
    }
}