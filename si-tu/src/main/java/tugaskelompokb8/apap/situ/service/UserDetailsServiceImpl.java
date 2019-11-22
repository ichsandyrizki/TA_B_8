package tugaskelompokb8.apap.situ.service;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.UserDb;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserDb userDb;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		UserModel user = userDb.findByUsername(username);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getNama()));
		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}


	
}