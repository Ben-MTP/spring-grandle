package com.jindo.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jindo.core.mapper.UserMapper;
import com.jindo.core.model.User;
import com.jindo.core.model.UserExample;
import com.jindo.core.util.AES;

/**
 * @author ManhKM on 5/5/2022
 * @project spring-grandle
 */
@RestController
public class HomePageController {
	
	@Autowired
	private UserMapper userMapper;

    @GetMapping(value = "/v1")
    public List<User> home(){
    	
    	UserExample example = new UserExample();
    	List<User> listUser = userMapper.selectByExample(example);
    	for(User value : listUser) {
    		System.out.println("Kiểm tra kích thước của List: " + listUser.size());
    		System.out.println(value);
    	}
        return listUser;
    }
    
    @GetMapping(value = "/v2")
    public List<User> searchAndCondition(){
    	
    	UserExample example = new UserExample();
    	example.createCriteria().andEmailIsNotNull().andFullNameIsNotNull().andUserPasswordIsNotNull().andIdEqualTo(2L);
    	List<User> listUser = userMapper.selectByExample(example);
    	
    	return listUser;
    }
    
    @GetMapping(value = "/v3")
    public List<Map<String, Object>> customQuery(){
    	String userName = "manhkm";
    	List<Map<String, Object>> listUser = userMapper.getAllUser(userName);
    	for(Map<String, Object> item : listUser) {
    		System.out.println("Info: " + item.get("full_name") + " | "+ item.get("user_name"));
    	}
    	return listUser;
    }
    
    @GetMapping(value = "/v4")
    public List<Map<String, Object>> getUserByUserNameAndId(){
    	Map<String, Object> param = new HashMap<>();
    	param.put("userName", "manhkm");
    	param.put("id", 2L);
    	System.out.println("Param: " + param);
    	List<Map<String, Object>> listUser = userMapper.getUserByUserNameAndId(param);
    	for(Map<String, Object> item : listUser) {
    		System.out.println("Info: " + item.get("full_name") + " | "+ item.get("user_name"));
    	}
    	return listUser;
    }

    @GetMapping(value = "/quickstart")
    public String quickstart(){
        return "Hello";
    }
    
}
