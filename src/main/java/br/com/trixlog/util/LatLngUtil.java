package br.com.trixlog.util;

import br.com.trixlog.model.Coordinate;
import com.google.maps.model.LatLng;

/**
 * Created by kaynan on 19/01/17.
 */
public class LatLngUtil {

    public static LatLng parseToLatLng(Coordinate coordinate) {
        return new LatLng(coordinate.getLat(), coordinate.getLng());
    }

}
