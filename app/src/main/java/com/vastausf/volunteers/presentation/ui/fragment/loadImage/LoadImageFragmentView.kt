package com.vastausf.volunteers.presentation.ui.fragment.loadImage

import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentView

interface LoadImageFragmentView: BaseFragmentView {

    fun openGalleryActivity()

    fun bindImage(link: String)

}