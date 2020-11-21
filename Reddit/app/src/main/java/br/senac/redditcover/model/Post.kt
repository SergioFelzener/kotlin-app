package br.senac.redditcover.model

data class Post (

        var id: String? = null,
        var nome: String,
        var desc: String,
        var curtido: Boolean = false

)


