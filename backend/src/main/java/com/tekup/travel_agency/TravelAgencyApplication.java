package com.tekup.travel_agency;

import com.tekup.travel_agency.model.Reservation;
import com.tekup.travel_agency.model.User;
import com.tekup.travel_agency.model.Voyage;
import com.tekup.travel_agency.repository.ReservationRepository;
import com.tekup.travel_agency.repository.UserRepository;
import com.tekup.travel_agency.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class TravelAgencyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VoyageRepository voyageRepository;


	@Autowired
	private ReservationRepository reservationRepository;
	@Override
	public void run(String... args) throws Exception {
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
		Voyage voyage1 = Voyage.builder()
				.name("Summer Adventure 2024")
				.destination("Maldives")
				.price(2500.00f)
				.tripDate(Date.from(Instant.parse("2024-08-15T00:00:00Z")))
				.startDate(Date.from(Instant.parse("2024-08-10T00:00:00Z")))
				.endDate(Date.from(Instant.parse("2024-08-20T00:00:00Z")))
				.build();

		Voyage savedVoyage1 = voyageRepository.save(voyage1);

		// Assuming you have access to UserRepository, VoyageRepository, and ReservationRepository

			Reservation reservation1 = Reservation.builder()
					.user(user)
					.voyage(savedVoyage1)
					.reservationDate(Date.from(Instant.parse("2024-07-01T10:00:00Z")))
					.build();

			Reservation savedReservation1 = reservationRepository.save(reservation1);

	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Allow only this origin
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
		corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);

		return new CorsFilter(source);
	}
}
