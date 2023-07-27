package com.sun.external.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public interface AddressApiResponse {
    List<ContentDto> getContents();
    MetaDto getMeta();

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class MetaDto{
        private Integer totalCount;
        private Integer pageableCount;
        private Boolean isEnd;

        public static MetaDto fromKakao(KakaoApiResponse.Meta meta) {
            return new MetaDto(meta.getTotalCount(), meta.getPageableCount(), meta.getIsEnd());
        }

        public static MetaDto fromNaver(NaverApiResponse.Meta meta) {
            return new MetaDto(meta.getTotalCount(), meta.getCount(), meta.getTotalCount() <= 10 * meta.getPage());
        }
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class ContentDto{
        private String addressName;
        private String addressType;
        private Double longitude; //longitude;
        private Double latitude; //latitude;

        public static ContentDto fromKakao(KakaoApiResponse.Document document) {
            return new ContentDto(document.getAddressName(), document.getAddressType(), document.getLongitude(), document.getLatitude());
        }

        public static ContentDto fromNaver(NaverApiResponse.Address address) {
            return new ContentDto(address.getRoadAddress(), "ROAD_ADDR", Double.valueOf(address.getLongitude()), Double.valueOf(address.getLatitude()));
        }
    }
}
