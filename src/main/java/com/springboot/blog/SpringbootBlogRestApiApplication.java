package com.springboot.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.blog.repository.RoleRepository;

@SpringBootApplication
public class SpringbootBlogRestApiApplication implements CommandLineRunner{

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// Role adminRole =new Role();
		// adminRole.setName("ROLE_ADMIN");
		// roleRepository.save(adminRole);

		// Role userRole =new Role();
		// userRole.setName("ROLE_ADMIN");
		// roleRepository.save(userRole);
	}

	

}
