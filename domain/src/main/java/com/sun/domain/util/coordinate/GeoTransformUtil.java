package com.sun.domain.util.coordinate;


import org.locationtech.proj4j.*;

public class GeoTransformUtil {
    public static Double[] transform(Double x, Double y){

        // 중부원점(EPSG:2097) 좌표계 문자열 정의
        String srcCrsString = "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=500000 +ellps=bessel +units=m +no_defs +towgs84=-115.80,474.99,674.11,1.16,-2.31,-1.63,6.43";

        // *WGS84 경위도: GPS가 사용하는 좌표
        String targetCrsString = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";

        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem EPSG_2097 = crsFactory.createFromParameters("EPSG:2097", srcCrsString); // Bessel 중부원점TM(EPSG:2097)
        //CoordinateReferenceSystem EPSG_2097 = crsFactory.createFromName("epsg:2097"); // Bessel 중부원점TM(EPSG:2097)
        CoordinateReferenceSystem EPSG_4326 = crsFactory.createFromParameters("EPSG:4326", targetCrsString); // 경/위도
        //CoordinateReferenceSystem EPSG_4326 = crsFactory.createFromName("epsg:4326"); // 경/위도

        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform wgsToUtm = ctFactory.createTransform(EPSG_2097, EPSG_4326);
        ProjCoordinate result = new ProjCoordinate();
        wgsToUtm.transform(new ProjCoordinate(x, y), result);


        return new Double[]{result.y, result.x};
    }
}
