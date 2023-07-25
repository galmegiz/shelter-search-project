package com.sun.domain.util.distance;

import org.springframework.stereotype.Component;

@Component
public class HaversineDistanceCalculator implements DistanceCalculateUtil{
    @Override
    public double calculateDistance(double clientLatitude, double clientLongitude, double shelterLatitude, double shelterLongitude) {
        clientLatitude = Math.toRadians(clientLatitude);
        clientLongitude = Math.toRadians(clientLongitude);
        shelterLatitude = Math.toRadians(shelterLatitude);
        shelterLongitude = Math.toRadians(shelterLongitude);

        double dLat = shelterLatitude - clientLatitude;
        double dLon = shelterLongitude - clientLongitude;

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(clientLatitude) * Math.cos(shelterLatitude) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        return distance;
    }
}
