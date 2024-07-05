package com.tekup.travel_agency;

import com.tekup.travel_agency.model.User;
import com.tekup.travel_agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelAgencyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
//		Run Code on boot
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setEmail("john.doe@gmail.com");
		userRepository.save(user);

		User user1 = new User();
		user1.setFirstName("Jane");
		user1.setLastName("Doe");
		user1.setEmail("jane.doe@gmail.com");
		userRepository.save(user1);
	}
}
