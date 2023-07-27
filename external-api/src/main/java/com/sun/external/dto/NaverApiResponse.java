package com.sun.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class NaverApiResponse implements AddressApiResponse{

    private List<Address> addresses;
    private Meta meta;
    @Override
    public List<ContentDto> getContents() {
        return this.addresses.stream().map(ContentDto::fromNaver).toList();
    }

    @Override
    public MetaDto getMeta() {
        return MetaDto.fromNaver(this.meta);
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Meta{
        private Integer totalCount;
        private Integer page;
        private Integer count;

    }
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Address {
        private String roadAddress;
        private String jibunAddress;
        private String englishAddress;
        @JsonProperty("x")
        private String longitude; //latitude
        @JsonProperty("y")
        private String latitude;
        private Double distance;
        private List<Object> addressElements;
    }


}
