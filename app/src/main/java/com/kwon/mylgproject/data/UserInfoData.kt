package com.kwon.mylgproject.data

data class UserInfoData (
    val result: Result,
    val data: UserInfo
)

data class UserInfo (
    /**
     * 유저 아이디
     */
    val user_id: String,

    /**
     * 유저 닉네임
     */
    val user_nickname: String,

    /**
     * 유저 프로필
     */
    val user_profile: String,

    /**
     * 유저 이름
     */
    val user_name: String,

    /**
     * 유저 타임 : 일반 ( normal ), 체험 ( experience )
     */
    val user_type: String,

    /**
     * 생성 날짜 : YYYY-MM-DD hh:mm
     */
    val created_at: String,
)