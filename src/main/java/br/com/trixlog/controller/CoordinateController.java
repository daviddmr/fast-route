package br.com.trixlog.controller;

import br.com.trixlog.model.Coordinate;
import br.com.trixlog.model.Stop;
import br.com.trixlog.util.LatLngUtil;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaynan on 22/01/17.
 */
@RestController
@RequestMapping("/coordinate")
public class CoordinateController {

    private final GeoApiContext geoApiContext = new GeoApiContext()
            .setApiKey("AIzaSyDK7P6B_C22Iv6K6vMC3aFqUmS9xR3jqKQ");

    @RequestMapping(method = RequestMethod.GET)
    public Coordinate findRouteByAddress(@RequestParam(value = "address") String address) {
        GeocodingResult[] geocodingResults;
        try {
            geocodingResults = GeocodingApi.geocode(geoApiContext, address).await();
        } catch (Exception e) {
            e.printStackTrace();
            geocodingResults = new GeocodingResult[0];
        }
        Geometry geometry = geocodingResults[0].geometry;
        Coordinate coordinate;
        if (geometry != null) {
            LatLng location = geometry.location;
            String formatedName = geocodingResults[0].formattedAddress;
            coordinate = new Coordinate(formatedName, location.lat, location.lng);
        } else {
            coordinate = new Coordinate();
        }
        return coordinate;
    }

    @RequestMapping(value = "/route", method = RequestMethod.POST)
    public List<Coordinate> findRouteByStops(@RequestBody List<Stop> stops) {
        List<Coordinate> coordinates = new ArrayList<>();
        if (!stops.isEmpty()) {
            Coordinate coordinateOrigin = stops.get(0).getCoordinate();
            Coordinate coordinateDestination = stops.get(stops.size() - 1).getCoordinate();

            DirectionsApiRequest directionsApiRequest = DirectionsApi
                    .newRequest(geoApiContext).origin(LatLngUtil.parseToLatLng(coordinateOrigin))
                    .destination(LatLngUtil.parseToLatLng(coordinateDestination));

            if (stops.size() > 2) {
                directionsApiRequest.waypoints(parseWayPoints(stops));
            }

            try {
                DirectionsResult directionsResult = directionsApiRequest.await();
                DirectionsRoute[] directionsRoute = directionsResult.routes;
                if (directionsRoute.length != 0) {
                    coordinates = parseCoordinates(directionsRoute[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return coordinates;
    }

    private static String parseWayPoints(List<Stop> stops) {
        String wayPoints = "";
        for (int i = 1; i < stops.size() - 1; i++) {
            Coordinate coordinate = stops.get(i).getCoordinate();
            if (stops.size() - 2 == 1) {
                wayPoints += new LatLng(coordinate.getLat(), coordinate.getLng()).toString();
            } else {
                wayPoints += new LatLng(coordinate.getLat(), coordinate.getLng()).toString() + "|";
            }
        }
        if (stops.size() - 2 > 1) {
            wayPoints = wayPoints.substring(0, wayPoints.length() - 1);
        }

        return wayPoints;
    }

    private List<Coordinate> parseCoordinates(DirectionsRoute directionsRoute) {
        List<Coordinate> coordinates = new ArrayList<>();
        DirectionsLeg[] directionsLegs = directionsRoute.legs;
        for (DirectionsLeg directionsLeg : directionsLegs) {
            for (DirectionsStep directionsStep : directionsLeg.steps) {
                List<LatLng> latLngs = directionsStep.polyline.decodePath();
                for (LatLng latLng : latLngs) {
                    coordinates.add(new Coordinate(latLng.lat, latLng.lng));
                }
            }
        }
        return coordinates;
    }
}
