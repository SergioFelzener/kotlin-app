package br.senac.redditcover.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(val text : Long, val user_id: Int): Parcelable