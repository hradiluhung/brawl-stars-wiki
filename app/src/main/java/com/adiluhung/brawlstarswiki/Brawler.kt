package com.adiluhung.brawlstarswiki

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brawler(
    val name: String,
    val rarity: String,
    val health: Int,
    val attack: Int,
    val desc: String,
    val detail: String,
    val pic: Int
) : Parcelable
