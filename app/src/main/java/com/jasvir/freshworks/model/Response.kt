package com.jasvir.freshworks.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {

    @SerializedName("data")
    @Expose
    var items: List<Data>? = null


}
