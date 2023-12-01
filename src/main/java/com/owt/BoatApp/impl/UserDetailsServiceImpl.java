/**
 * 
 */
package com.owt.BoatApp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.owt.BoatApp.entity.User;
import com.owt.BoatApp.repository.UserRepository;

/**
 * @author abelb
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	final UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
}
