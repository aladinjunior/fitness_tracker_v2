package co.aladinjunior.tfitstudio

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Items(
    val id: Int,
    @StringRes
    val name: Int,
    @StringRes
    val name3L: Int,
    @DrawableRes
    val icon: Int
)
