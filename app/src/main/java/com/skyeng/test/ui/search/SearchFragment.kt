package com.skyeng.test.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.skyeng.test.R
import com.skyeng.test.data.Word
import com.skyeng.test.navigation.Navigation
import com.skyeng.test.presenters.SearchPresenter
import com.skyeng.test.ui.adapters.WordAdapter
import com.skyeng.test.ui.base.BaseFragment
import com.skyeng.test.ui.base.BaseView
import com.skyeng.test.ui.list.DividerDecoration
import com.skyeng.test.ui.list.InfiniteScrollListener
import com.skyeng.test.ui.list.OnItemClickedListener
import com.skyeng.test.ui.list.ViewType
import com.skyeng.test.ui.word.WordFragment
import com.skyeng.test.utils.Constants
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
interface SearchView : BaseView {
    fun showSearchedWords(words: MutableList<ViewType>)
    fun addData(data: MutableList<ViewType>)
    fun showReloadItem()
}

class SearchFragment : BaseFragment(), SearchView, OnItemClickedListener<ViewType> {
    override fun getFragmentLayout(): Int = R.layout.fragment_search

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    @Inject
    lateinit var presenter: SearchPresenter

    private var adapter: WordAdapter? = null
    private var page: Int = 1

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            searchBar?.addTextChangeListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    subscriber.onNext(s?.toString() ?: "")
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        })
            .debounce(250, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { searchedText ->
                page = 1
                load(searchedText, false, page)
            }
    }

    private fun initRecyclerView() {
        if (adapter == null) {
            adapter = WordAdapter(this)
        }

        val layoutManager = LinearLayoutManager(context)
        val scrollListener = InfiniteScrollListener(
            Constants.VISIBLE_ITEMS_THRESHOLD,
            layoutManager
        ) {
            if (adapter?.hasBottomProgress == true && adapter?.hasBottomReloader == false) {
                page += 1
                load(searchBar.text ?: "", true, page)
            }
        }

        swipe.isEnabled = false
        recyclerView.addItemDecoration(
            DividerDecoration(
                context ?: return,
                context?.resources?.getDimensionPixelSize(R.dimen.divider_left_padding) ?: 0
            )
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(scrollListener)
    }

    private fun load(search: String = "", loadMore: Boolean = false, page: Int = 1) {
        val params = hashMapOf<String, Any>()
        params["pageSize"] = Constants.ITEMS_PER_PAGE
        params["search"] = search
        params["page"] = page
        presenter.searchTranslate(loadMore, params)
    }

    override fun showSearchedWords(words: MutableList<ViewType>) {
        adapter?.hasBottomProgress = words.size >= Constants.ITEMS_PER_PAGE
        adapter?.reinit(words)
    }

    override fun addData(data: MutableList<ViewType>) {
        if (data.size == 0) {
            adapter?.hasBottomProgress = false
        }
        adapter?.addData(data)
    }

    override fun showReloadItem() {
        adapter?.showReloadItem()
    }

    override fun onStop() {
        super.onStop()
        presenter.stopSubscriptions()
    }

    override fun onDestroyView() {
        recyclerView.clearOnScrollListeners()
        super.onDestroyView()
    }

    override fun showProgress() {
        swipe?.isRefreshing = true
    }

    override fun hideProgress() {
        swipe?.isRefreshing = false
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: Int) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClicked(item: ViewType, view: View?) {
        Navigation.showFragmentBack(
            fragmentManager!!,
            WordFragment.newInstance(item as? Word ?: return)
        )
    }
}