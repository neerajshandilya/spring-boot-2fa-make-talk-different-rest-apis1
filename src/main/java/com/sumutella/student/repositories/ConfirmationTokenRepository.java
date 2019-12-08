package com.sumutella.student.repositories;

import com.sumutella.student.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sumutella
 * @time 10:35 PM
 * @since 12/7/2019, Sat
 */
@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Integer> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
