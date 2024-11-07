package kr.goodmit.framework.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialType {

    KAKAO("KAKAO"),
    GOOGLE("GOOGLE"),
    NAVER("NAVER"),
    GITHUB("GITHUB");

    private final String socialType;

    // @JsonCreator: JSON 데이터를 객체로 변환할 때 사용
    // 기능 : 대소문자 구분 없이 enum 상수를 찾아서 반환
    @JsonCreator
    public static SocialType from(String value) {
        return SocialType.valueOf(value.trim().toUpperCase());
    }
}