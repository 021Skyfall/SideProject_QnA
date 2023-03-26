package com.study.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    COFFEE_NOT_FOUND(404, "Coffee not found"),
    COFFEE_CODE_EXISTS(409, "Coffee Code exists"),
    ORDER_NOT_FOUND(404, "Order not found"),
    CANNOT_CHANGE_ORDER(403, "Order can not change"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),
    PASSWORD_MISMATCHED(400,"Password Does Not Match"),
    BOARD_NOT_FOUND(404,"Board Not Found"),
    ID_MISMATCHED(400,"Id Does Not Match"),
    DELETED_BOARD(404,"Board has already deleted"),
    CANNOT_CHANGE_BOARD(400,"Board Can't Change"),
    ADMIN_ACCESS_ONLY(400,"Admin Only, Please Check your Id or Password"),
    REPLY_NOT_FOUND(404,"Reply not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
