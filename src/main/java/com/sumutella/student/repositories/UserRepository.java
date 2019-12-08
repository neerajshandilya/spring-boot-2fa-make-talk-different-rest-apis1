package com.sumutella.student.repositories;

import com.sumutella.student.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sumutella
 * @time 10:34 PM
 * @since 12/7/2019, Sat
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmailIdIgnoreCase(String emailId);
}
