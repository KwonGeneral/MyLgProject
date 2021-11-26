package com.kwon.mylgproject.data

data class Result (
    /**
     * 통신 코드
     */
    val code: String,

    /**
     * 통신 상태에 따른 메세지
     */
    val message: String,
)
