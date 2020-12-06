package com.web.apiteste.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig {
		 
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
					.allowedHeaders("*")
					.allowedOrigins("http://localhost:3000","*");
				}
			};
		}	
		
}
