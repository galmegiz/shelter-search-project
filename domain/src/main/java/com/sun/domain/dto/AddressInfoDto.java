package com.sun.domain.dto;

import com.sun.external.dto.AddressApiResponse;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressInfoDto {

    private MetaInfo metaInfo;
    private List<Content> contents;

    public static AddressInfoDto fromApiResponse(AddressApiResponse apiResponse) {
        return new AddressInfoDto(
                MetaInfo.fromApiResponse(apiResponse.getMeta()),
                apiResponse.getContents().stream().map(Content::formApiResponse).toList());
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class MetaInfo{
        private Integer totalCount;
        private Integer pageableCount;
        private Boolean isEnd;

        public static MetaInfo fromApiResponse(AddressApiResponse.MetaDto metaDto) {
            return new MetaInfo(metaDto.getTotalCount(), metaDto.getPageableCount(), metaDto.getIsEnd());
        }

    }
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class Content{
        private String addressName;
        private String addressType;
        private Double longitude; //longitude;
        private Double latitude; //latitude;

        public static Content formApiResponse(AddressApiResponse.ContentDto contentDto) {
            return new Content(contentDto.getAddressName(), contentDto.getAddressType(), contentDto.getLongitude(), contentDto.getLatitude());
        }
    }
}
