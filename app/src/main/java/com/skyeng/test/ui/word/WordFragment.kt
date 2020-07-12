package com.skyeng.test.ui.word

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.piasy.rxandroidaudio.PlayConfig
import com.github.piasy.rxandroidaudio.RxAudioPlayer
import com.skyeng.test.R
import com.skyeng.test.data.Word
import com.skyeng.test.presenters.WordPresenter
import com.skyeng.test.ui.base.BaseFragment
import com.skyeng.test.ui.base.BaseView
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_word.*
import javax.inject.Inject

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
interface WordView : BaseView {

}

class WordFragment : BaseFragment(), WordView {
    override fun getFragmentLayout(): Int = R.layout.fragment_word

    companion object {
        const val KEY_WORD = "com.skyeng.test.ui.word"

        fun newInstance(word: Word): WordFragment {
            val fragment = WordFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY_WORD, word)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var presenter: WordPresenter

    private fun getWord(): Word = arguments?.getParcelable(KEY_WORD)
        ?: throw IllegalArgumentException("Word can't be null. Please check that it has been already set.")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initUI()
    }

    private fun initToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbarTitle)
        (activity as AppCompatActivity).supportActionBar?.title = ""
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbarTitle.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initUI() {
        val word = getWord()
        Glide.with(context!!)
            .load(word?.meanings[0]?.formatImageUrl() ?: "")
            .into(imageWord)

        textWord.text = getString(R.string.word_template, word.text, word?.meanings[0]?.transcription ?: "")
        textTranslate.text = word?.meanings[0]?.translation?.text ?: ""
        textDescription.text = word?.meanings[0]?.translation?.note ?: ""

        imagePlaySound.setOnClickListener {
            RxAudioPlayer.getInstance()
                .play(PlayConfig.url(word.meanings[0].formatSoundUrl()).build())
                .subscribeOn(Schedulers.io())
                .subscribe()
        }
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: Int) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }
}