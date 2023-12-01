/**
 * 
 */
package com.owt.BoatApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owt.BoatApp.entity.Boat;
import com.owt.BoatApp.repository.BoatRepository;

/**
 * @author abelb
 *
 */
@Service
public class BoatService {

	private final BoatRepository boatRepository;

	@Autowired
	public BoatService(final BoatRepository boatRepository) {
		this.boatRepository = boatRepository;
	}

	/**
	 * 
	 * @return
	 */
	public List<Boat> fetchBoats() {
		return (List<Boat>) this.boatRepository.findAll();
	}

	/**
	 * 
	 * @param boatId
	 * @return
	 */
	public Optional<Boat> fetchBoatById(final Long boatId) {
		return this.boatRepository.findById(boatId);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Boat> fetchBoatsByName(final String name) {
		return this.boatRepository.findByNameContainingIgnoreCase(name);
	}

	/**
	 * 
	 * @param boat
	 */
	public Boat createBoat(final Boat boat) {
		return this.boatRepository.save(boat);
	}

	/**
	 * 
	 * @param boat
	 * @param id
	 * @return
	 */
	public Boat updateBoat(final Boat newBoat, final Boat boat) {
		if (null != newBoat.getName() && !"".equalsIgnoreCase(newBoat.getName())) {
			boat.setName(newBoat.getName());
		}

		if (null != newBoat.getDescription() && !"".equalsIgnoreCase(newBoat.getDescription())) {
			boat.setDescription(newBoat.getDescription());
		}

		return this.boatRepository.save(boat);
	}

	/**
	 * 
	 * @param boatId
	 */
	public void deleteBoatById(final Long boatId) {
		this.boatRepository.deleteById(boatId);
	}
}
