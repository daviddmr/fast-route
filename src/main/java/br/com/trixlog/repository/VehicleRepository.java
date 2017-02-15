package br.com.trixlog.repository;

import br.com.trixlog.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kaynan on 23/01/17.
 */
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    Vehicle findByName(String name);
}
