package com.softex.gtec.ui.register

sealed class RegisterStateEvent {
    object Register : RegisterStateEvent()
    object InvalidName : RegisterStateEvent()
    object InvalidUsernameOrEmail : RegisterStateEvent()
    object InvalidPassword : RegisterStateEvent()
}