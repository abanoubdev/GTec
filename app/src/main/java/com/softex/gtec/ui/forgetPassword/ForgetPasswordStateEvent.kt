package com.softex.gtec.ui.forgetPassword

sealed class ForgetPasswordStateEvent {
    object ForgetPassword : ForgetPasswordStateEvent()
    object EmptyEmail : ForgetPasswordStateEvent()
    object InvalidEmail : ForgetPasswordStateEvent()
}