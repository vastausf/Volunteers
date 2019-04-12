package com.vastausf.volunteers.presentation.ui.fragment.loadImage

import android.net.Uri
import android.provider.MediaStore
import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.data.UploadImageI
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import javax.inject.Inject

@InjectViewState
class LoadImageFragmentPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersApiClient: VolunteersApiClient
) : BaseFragmentPresenter<LoadImageFragmentView>() {

    fun onNewImageClick() {
        viewState.openGalleryActivity()
    }

    fun uploadImage(imageUri: Uri) {
        val image = volunteersApplication.contentResolver?.query(imageUri,
            listOf(MediaStore.Images.Media.DATA).toTypedArray(),
            null, null, null)
            ?.use { cursor ->
                val col = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                return@use File(cursor.getString(col))
            }

        if (image != null)
            volunteersApiClient
                .uploadImage(image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onUploadImageSuccess, ::onUploadImageError)
                .unsubscribeOnDestroy()
    }

    private fun onUploadImageSuccess(uploadImageI: UploadImageI) {
        viewState.bindImage(uploadImageI.imageLink)
    }

    private fun onUploadImageError(error: Throwable) {
        error.printStackTrace()
        viewState.showToast(error::class.java.name)
    }

}