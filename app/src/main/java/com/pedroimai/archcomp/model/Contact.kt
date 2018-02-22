package com.pedroimai.archcomp.model

data class Contact(
    val id: String,
    val photo: String,
    val name: String,
    val email: String,
    val phone: String
)

data class SimpleContact(val id: String, val photo: String, val name: String)