/**
 * 
 */
package com.owt.BoatApp.component;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.owt.BoatApp.entity.Boat;
import com.owt.BoatApp.entity.User;
import com.owt.BoatApp.repository.BoatRepository;
import com.owt.BoatApp.repository.UserRepository;

/**
 * @author abelb
 *
 */
@Component
public class InitialDatabaseLoader implements CommandLineRunner {

	private final BoatRepository boatRepository;
	private final UserRepository userRepository;

	@Autowired
	public InitialDatabaseLoader(final BoatRepository boatRepository, final UserRepository userRepository) {
		this.boatRepository = boatRepository;
		this.userRepository = userRepository;
	}

//	@Override
	public void run(String... args) throws Exception {
		// Boats
		Stream.of("BoatOne", "BoatTwo", "BoatThree").forEach(name -> {
			final Boat boat = new Boat(name, name + " description");
			boatRepository.save(boat);
		});

		// Users
		final User user = new User("UserOne", "UserOne@domain.com", "UserOne");
		userRepository.save(user);

		boatRepository.findAll().forEach(System.out::println);
		userRepository.findAll().forEach(System.out::println);
	}
}
