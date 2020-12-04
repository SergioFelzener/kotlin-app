package br.senac.redditcover.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    var _id: String? = null,
    var name: String? = null,
    var description: String? = null
): Parcelable {
    override fun toString(): String {
        return name.toString()
    }
}
