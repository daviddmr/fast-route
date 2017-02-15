package br.com.trixlog.controller;

import br.com.trixlog.model.Route;
import br.com.trixlog.model.Vehicle;
import br.com.trixlog.repository.RouteRepository;
import br.com.trixlog.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kaynan on 22/01/17.
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody Route route) {
        System.err.println(route.toString());
        Vehicle vehicle = route.getVehicle();
        if (vehicleRepository.findByName(vehicle.getName()) == null) {
            vehicleRepository.save(vehicle);
        }
        vehicle = vehicleRepository.findByName(vehicle.getName());
        route.setVehicle(vehicle);
        route.setDate(Calendar.getInstance().getTimeInMillis());
        routeRepository.save(route);
    }

}
