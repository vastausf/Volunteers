package com.vastausf.volunteers.presentation.ui.fragment.groups

import com.vastausf.volunteers.model.volunteers.data.GroupDataFull
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentView

interface GroupsFragmentView: BaseFragmentView {

    fun groupsLoadState(state: Boolean)

    fun bindGroupList(groups: List<GroupDataFull>)

    fun bindMoreGroupList(groups: List<GroupDataFull>)

}