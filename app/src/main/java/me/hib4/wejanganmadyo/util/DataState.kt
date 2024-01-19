package me.hib4.wejanganmadyo.util

sealed class UiComponent {
    data class Toast(val message: String): UiComponent()

    data class Dialog(val title: String, val message: String): UiComponent()

    data class None(val message: String? = null): UiComponent()
}