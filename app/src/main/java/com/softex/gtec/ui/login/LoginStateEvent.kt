package com.softex.gtec.ui.login

sealed class LoginStateEvent {
    object Login : LoginStateEvent()
    object InvalidUsername : LoginStateEvent()
    object InvalidPassword : LoginStateEvent()
}