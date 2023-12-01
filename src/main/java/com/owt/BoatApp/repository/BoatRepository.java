/**
 * 
 */
package com.owt.BoatApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.owt.BoatApp.entity.Boat;

/**
 * @author abelb
 *
 */
@Repository
public interface BoatRepository extends CrudRepository<Boat, Long> {

	List<Boat> findByNameContainingIgnoreCase(String name);
}
