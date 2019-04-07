package com.vastausf.volunteers.presentation.registration

import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.presentation.base.BasePresenter
import javax.inject.Inject

class RegistrationPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication
) : BasePresenter<RegistrationView>() {

}