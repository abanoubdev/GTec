package com.softex.gtec.ui.login

sealed class LoginStateEvent {
    object Login : LoginStateEvent()
    object None : LoginStateEvent()
    object Loading : LoginStateEvent()
}