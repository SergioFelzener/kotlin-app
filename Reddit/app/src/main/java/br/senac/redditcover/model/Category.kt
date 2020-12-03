package br.senac.redditcover.model

data class Category(val _id: String, val name: String?, val description: String){
    override fun toString(): String {
        return name.toString()
    }
}
