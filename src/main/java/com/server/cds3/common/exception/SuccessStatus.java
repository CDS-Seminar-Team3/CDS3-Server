package com.server.cds3.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessStatus {
    /*
    user
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료되었습니다."),

    /*
    petitionAgree
     */
    POST_AGREE_SUCCESS(HttpStatus.CREATED, "청원 동의가 완료되었습니다."),

    /*
    MyPetition
     */
    GET_MY_PETITION(HttpStatus.OK, "나의 청원 조회하기 성공"),

    /*
    petitionDetail
     */
    GET_PETITION_DETAIL(HttpStatus.OK, "청원 상세 내용 조회하기 성공")
    ;



    private final HttpStatus httpStatus;
    private final String message;

}
