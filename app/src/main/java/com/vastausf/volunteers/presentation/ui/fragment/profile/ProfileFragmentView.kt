package com.vastausf.volunteers.presentation.ui.fragment.profile

import com.vastausf.volunteers.model.volunteers.data.UserDataFull
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentView

interface ProfileFragmentView : BaseFragmentView {

    fun bindUserData(userData: UserDataFull)

}
