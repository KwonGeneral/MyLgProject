package com.kwon.mylgproject.data

data class AccountData (
    val result: Result,
    val data: AccountInfo
)

data class AccountInfo (
    /**
     * 고유 번호
     */
    val id: Int,

    /**
     * 유저 타입 : 일반 ( normal ), 체험 ( experience )
     */
    val account_type: String,

    /**
     * 인증 토큰
     */
    val access_token: String,
)
