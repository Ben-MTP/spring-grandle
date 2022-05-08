package com.jindo.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Mục đích sử dụng thêm @Configuration là để khi project ban đầu chạy lên thì nó sẽ chạy vào đây trước.
 */
@Configuration
@EnableWebMvc
public class ConfigResource implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/resources/static/**");
	}
}
