package br.com.trixlog.repository;

import br.com.trixlog.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kaynan on 21/01/17.
 */
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
