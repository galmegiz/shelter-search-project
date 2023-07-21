package com.sun.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class KakaoApiResponse implements AddressApiResponse {
    private List<Document> documents;
    private Meta meta;

    @Override
    public List<ContentDto> getContents() {
        return documents.stream().map(ContentDto::fromKakao).toList();
    }

    @Override
    public MetaDto getMeta() {
        return MetaDto.fromKakao(meta);
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Document{
        @JsonProperty("address_name")
        private String addressName;
        @JsonProperty("address_type")
        private String addressType;
        @JsonProperty("x")
        private Double longitude; //longitude;
        @JsonProperty("y")
        private Double latitude; //latitude;
    }
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Meta{
        @JsonProperty("total_count")
        private Integer totalCount;
        @JsonProperty("pageable_count")
        private Integer pageableCount;
        @JsonProperty("is_end")
        private Boolean isEnd;
    }
}
