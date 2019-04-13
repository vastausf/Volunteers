package com.vastausf.volunteers.presentation.ui.fragment.editProfile

import com.vastausf.volunteers.model.volunteers.data.UserDataEdit
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentView

interface EditProfileFragmentView: BaseFragmentView {

    fun bindProfile(userDataEdit: UserDataEdit)

}
