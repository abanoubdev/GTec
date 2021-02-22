package com.softex.gtec.ui.shop

sealed class ShopStateEvent {
    object Shop : ShopStateEvent()
    object Loading : ShopStateEvent()
}