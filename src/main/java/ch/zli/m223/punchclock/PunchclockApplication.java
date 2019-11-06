package ch.zli.m223.punchclock;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.Role;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.CategoryRepository;
import ch.zli.m223.punchclock.repository.EntryRepository;
import ch.zli.m223.punchclock.repository.UserRepository;
import ch.zli.m223.punchclock.repository.RoleRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class PunchclockApplication {

	public static void main(String[] args) {
		SpringApplication.run(PunchclockApplication.class, args);
	}

	/**
	 * This method creates some sample data in the database
	 * @param entryRepository The entry repository needed for saving the Entry objects
	 * @param userRepository The user repository needed for saving the User objects
	 * @param categoryRepository The category repository needed for saving the Category objects
	 * @param roleRepository The role repository needed for saving the Role objects
	 * @return arguments
	 */
	@Bean
	public ApplicationRunner demoData(EntryRepository entryRepository, UserRepository userRepository, CategoryRepository categoryRepository, RoleRepository roleRepository) {
		return args -> {
			var bCryptPasswordEncoder = new BCryptPasswordEncoder();
			var role1 = new Role();
			role1.setName("Admin");
			var role2 = new Role();
			role2.setName("Read");
			var user1 = new User();
			user1.setUserName("admin");
			user1.setPassWord(bCryptPasswordEncoder.encode("password"));
			user1.setRole(role1);
			var user2 = new User();
			user2.setUserName("test");
			user2.setPassWord(bCryptPasswordEncoder.encode("test"));
			user2.setRole(role2);
			var category1 = new Category();
			category1.setType("Worktime");
			var category2 = new Category();
			category2.setType("Break");
			var entry1 = new Entry();
			entry1.setCheckIn(LocalDateTime.parse("2016-08-25T08:55:34"));
			entry1.setCheckOut(LocalDateTime.parse("2016-08-25T17:55:34"));
			entry1.setUser(user1);
			entry1.setCategory(category1);
			var entry2 = new Entry();
			entry2.setCheckIn(LocalDateTime.parse("2016-08-26T08:55:34"));
			entry2.setCheckOut(LocalDateTime.parse("2016-08-26T17:55:34"));
			entry2.setUser(user1);
			entry2.setCategory(category2);

			roleRepository.save(role1);
			roleRepository.save(role2);
			userRepository.save(user1);
			userRepository.save(user2);
			categoryRepository.save(category1);
			categoryRepository.save(category2);
			entryRepository.save(entry1);
			entryRepository.save(entry2);
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
