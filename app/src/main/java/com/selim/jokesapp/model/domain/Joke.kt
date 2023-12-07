package com.selim.jokesapp.model.domain

data class Joke(
    val error:Boolean,
    val category:String,
    val type:String,
    val setup:String,
    val delivery:String,
    val flags:Flags,
    val id:Int,
    val safe:String,
    val lang:String
)

/*{
    "error": false,
    "category": "Programming",
    "type": "twopart",
    "setup": "How can you tell an extroverted programmer?",
    "delivery": "He looks at YOUR shoes when he's talking.",
    "flags": {
        "nsfw": false,
        "religious": false,
        "political": false,
        "racist": false,
        "sexist": false,
        "explicit": false
    },
    "id": 17,
    "safe": true,
    "lang": "en"
}*/
