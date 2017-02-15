package br.com.trixlog.repository;

import br.com.trixlog.model.Route;
import br.com.trixlog.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by kaynan on 19/01/17.
 */
public interface RouteRepository extends MongoRepository<Route, String> {

    List<Route> findByName(String name);

    List<Route> findByVehicle(Vehicle vehicle);

    List<Route> findByDate(Long date);

}
