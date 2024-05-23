package com.geeui.guide.model

data class ListItemModel(
    val desc: String,
    val icon: Int? = 0,
    val type: String? = "activity",
    val action: Class<*>? = null,
    var isChecked:Boolean?=false,
    var packageName:String?=null,
    var clazzName:String?=null,
    var argument:String?=null
)
