package com.sun.external.properties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ConfigurationProperties("external.api")
public class ExternalApiProps {
    private KakaoProps kakao;
    private NaverProps naver;

    @Getter
    @AllArgsConstructor
    public static class KakaoProps{
        private String uri;
        private String authKey;
    }

    @Getter
    @AllArgsConstructor
    public static class NaverProps{
        private String uri;
        private String keyId;
        private String key;
    }
}
