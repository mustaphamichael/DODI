package com.dodi

/**
 * Created by mmustapha on 12/31/17.
 */
object CaseUtils {

    fun titleCase(word:String): String{
        val breakTheWord = word.split(" ")
        val casedWord = word.substring(0,1).capitalize().plus(word.substring(1))
        System.out.println(casedWord)
        return casedWord
    }
}