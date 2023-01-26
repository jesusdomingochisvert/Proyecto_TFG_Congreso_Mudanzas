package com.example.congresotfg.LoginModule.model

import com.example.congresotfg.common.utils.Constants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AsistenteInfo(
    @SerializedName(Constants.USERNAME_PARAM) val username: String,
    @SerializedName(Constants.PASSWORD_PARAM) val password: String

) {
}