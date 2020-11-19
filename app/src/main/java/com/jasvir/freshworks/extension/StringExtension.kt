package com.jasvir.freshworks.extension

/**
 * string extention function
 *
 * @param stringList get list to check a string is contained
 * @return boolean value true if present
 */
fun String.contains(stringList: List<String>): Boolean {
    for (string in stringList) {
        if ((this.contains(string, true))) {
            return true
        }
    }
    return false
}