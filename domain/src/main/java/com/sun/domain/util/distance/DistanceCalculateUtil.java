package com.sun.domain.util.distance;

public interface DistanceCalculateUtil {
    double EARTH_RADIUS = 6371.0;
    double calculateDistance(double clientLatitude, double clientLongitude, double shelterLatitude, double shelterLongitude);

}
