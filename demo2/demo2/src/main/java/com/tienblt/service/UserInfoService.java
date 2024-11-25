package com.tienblt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tienblt.config.UserInfoUserDetails;
import com.tienblt.entity.UserInfo;
import com.tienblt.repository.UserInfoRepository;

public class UserInfoService implements UserDetailService{

	@Autowired
	UserInfoRepository reposity;
	
	public UserInfoService(UserInfoRepository userInfoRepository) {
		this.reposity= userInfoRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = reposity.findByName(username);
		return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
	}

}
