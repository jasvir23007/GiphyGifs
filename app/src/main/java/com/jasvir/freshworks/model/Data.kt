package com.jasvir.freshworks.model

data class Data(val id:String,val url:String?,val images:Images )

data class Images(val original: Orignal)

data class Orignal(val url: String?)