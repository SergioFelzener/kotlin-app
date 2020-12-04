package br.senac.redditcover.model

data class Category(
    var _id: String? = null,
    var name: String? = null,
    var description: String? = null
){
    override fun toString(): String {
        return name.toString()
    }
}
