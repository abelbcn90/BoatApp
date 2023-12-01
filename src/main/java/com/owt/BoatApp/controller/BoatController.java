/**
 * 
 */
package com.owt.BoatApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.owt.BoatApp.entity.Boat;
import com.owt.BoatApp.service.BoatService;

import jakarta.validation.Valid;

/**
 * @author abelb
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api")
public class BoatController {

	private final BoatService boatService;
	
	@Autowired
	public BoatController(final BoatService boatService) {
		this.boatService = boatService;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping("/boats")
	public ResponseEntity<List<Boat>> fetchBoats(@RequestParam(required = false) String name) {
		try {
			final List<Boat> boats = new ArrayList<Boat>();
			
			if (name == null) {
				this.boatService.fetchBoats().forEach(boats::add);
				
			} else {
				this.boatService.fetchBoatsByName(name).forEach(boats::add);
			}
			
			if (boats.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(boats, HttpStatus.OK);
			
		} catch (final Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 
	 * @param boatId
	 * @return
	 */
	@GetMapping("/boats/{id}")
	public ResponseEntity<Boat> getTutorialById(@PathVariable("id") Long boatId) {
		final Optional<Boat> tutorialData = this.boatService.fetchBoatById(boatId);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 
	 * @param boat
	 * @return
	 */
	@PostMapping("/boats")
	public ResponseEntity<Boat> createBoat(@Valid @RequestBody Boat boat) {
		try {
			final Boat newBoat = this.boatService.createBoat(new Boat(boat.getName(), boat.getDescription()));
			
			return new ResponseEntity<>(newBoat, HttpStatus.CREATED);
		
		} catch (final Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 
	 * @param boat
	 * @param boatId
	 * @return
	 */
	@PutMapping("/boats/{id}")
	public ResponseEntity<Boat> updateBoat(@RequestBody Boat boat, @PathVariable("id") Long boatId) {
		final Optional<Boat> boatData = this.boatService.fetchBoatById(boatId);
			
		if (boatData.isPresent()) {
			return new ResponseEntity<>(this.boatService.updateBoat(boat, boatData.get()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 
	 * @param boatId
	 * @return
	 */
	@DeleteMapping("/boats/{id}")
	public ResponseEntity<HttpStatus> deleteBoatById(@PathVariable("id") Long boatId) {
		try {
			this.boatService.deleteBoatById(boatId);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (final Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
