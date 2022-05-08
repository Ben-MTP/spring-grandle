package com.jindo.core.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jindo.core.mapper.UserMapper;
import com.jindo.core.model.User;
import com.jindo.core.model.UserExample;

/**
 * Có bao nhiều các có thể sử dụng CustomerUserDetailService với SpringSecurityConfig
 * Cách 1: New một đối tượng
 * Cách 2: Sử dụng đẩy thành Bean và đưa vào Context để quản lý.
 * @author ManhKM
 *
 */
@Service
public class CustomerUserDetailService implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * Lấy username của người dùng nhập vào.
	 * Query với Database -> tham chiếu với trường email.
	 * Lấy ra password -> tạo ra chứng thực bằng hoàn toàn code động.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andEmailEqualTo(username);
		
		List<User> listUser = userMapper.selectByExample(userExample);
		
		if(listUser.size() > 0) {
			// Đăng nhập thành công
			User user = listUser.get(0);
			
			List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
			grantList.add(authority);
			
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), grantList);
			return userDetails;
			
		} else {
			// Đăng nhập thất bại
			new UsernameNotFoundException("Login Fail !");
		}
		
		return null;
	}

}
