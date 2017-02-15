package br.com.trixlog.repository;

import br.com.trixlog.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kaynan on 19/01/17.
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
