package com.vastausf.volunteers.presentation.ui.fragment.loadImage

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import com.vastausf.volunteers.R
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_load_image.*
import kotlinx.android.synthetic.main.fragment_load_image.view.*
import java.io.File
import javax.inject.Inject

class LoadImageFragment : BaseFragment(), LoadImageFragmentView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: LoadImageFragmentPresenter

    @Inject
    lateinit var picasso: Picasso

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_load_image, container, false)

        view.bLoadImage.setOnClickListener {
            presenter.onNewImageClick()
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            DaggerFragmentComponent
                .builder()
                .applicationComponent(volunteersApplication.applicationComponent)
                .build()
                .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun openGalleryActivity() {
        Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            startActivityForResult(this, RESULT_IMAGE_GET_SUCCESS)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RESULT_IMAGE_GET_SUCCESS -> {
                val imageUri = data?.data

                if (imageUri != null)
                    presenter.uploadImage(imageUri)
                else
                    showToast(getString(R.string.unknown_error))
            }
        }
    }

    override fun bindImage(link: String) {
        view?.etImageLink?.setText(link)
    }

    companion object {
        const val RESULT_IMAGE_GET_SUCCESS = 1
    }

}