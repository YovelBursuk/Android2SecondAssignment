package com.example.myapplicationrecyclerview.Model

import java.io.Serializable

class State : Serializable {
    private var name: String? = null
    private var borders: List<String>? = null
    private var nativeName: String? = null
    private var flag: String? = null

    constructor(name: String?, nativeName: String?) {
        this.name = name
        this.nativeName = nativeName
    }
    constructor(name: String?, borders: List<String>?, nativeName: String?, flag: String?) {
        this.name = name
        this.borders = borders
        this.nativeName = nativeName
        this.flag = flag
    }

    fun getName(): String? {
        return name
    }

    fun getBorders(): List<String?>? {
        return borders
    }

    fun getNativeName(): String? {
        return nativeName
    }

    fun getFlag(): String? {
        return flag
    }
}