/**
 * 
 */
package com.learn.oauth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learn.oauth.model.User;

/**
 * @author vkalyana
 *
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepo;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		
		User testUser = new User();
		testUser.setActive(true);
		testUser.setFirstName("testFirstName");
		testUser.setLastName("testLastName");
		testUser.setUserName("testUserName");
		testUser.setPassword("testPassword1");
		testUser.setGender("male");
		testUser.setEmail("testemail@gmail.com");
		userRepo.save(testUser);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception {
		// clear the created test user
		User toDelete = userRepo.findByFirstName("testFirstName");
		if(Objects.nonNull(toDelete)){
			userRepo.delete(toDelete);
		}
	}

	@DisplayName("should find users")
	@Test
	public void testFindUsers() {
		
		List<User> actualUser = userRepo.findAll();
		
		Assertions.assertNotNull(actualUser);
		assertThat(actualUser.size()).isGreaterThanOrEqualTo(1);
	}
	
	@DisplayName("should create and find user")
	@Test
	public void testCreateUser() {
		
		User actualUser = userRepo.findByFirstName("testFirstName");
		
		Assertions.assertNotNull(actualUser);
		Assertions.assertAll("Collections",
				() -> Assertions.assertEquals("testFirstName", actualUser.getFirstName()),
				() -> Assertions.assertEquals("testLastName", actualUser.getLastName()),
				() -> Assertions.assertEquals("testUserName", actualUser.getUserName()),
				() -> Assertions.assertEquals("testPassword1", actualUser.getPassword()),
				() -> Assertions.assertEquals("male", actualUser.getGender()),
				() -> Assertions.assertEquals("testemail@gmail.com", actualUser.getEmail()),
				() -> Assertions.assertNull(actualUser.getRoles()));
		
	}
	
	@DisplayName("should update and find user")
	@Test
	public void testUpdateUser() {
		User actualUser = userRepo.findByFirstName("testFirstName");
		Assertions.assertNotNull(actualUser);
		Assertions.assertNull(actualUser.getRoles());

		actualUser.setRoles(Arrays.asList("admin","user"));
		
		User updatedUser = userRepo.save(actualUser);
		
		Assertions.assertAll("Collections",
				() -> Assertions.assertEquals(actualUser.getId(), updatedUser.getId()),
				() -> Assertions.assertNotNull(actualUser.getRoles()),
				() -> Assertions.assertEquals(Arrays.asList("admin","user"), updatedUser.getRoles()));
		
	}
	
	@DisplayName("should delete and cound not find user")
	@Test
	public void testDeleteUser() {
		User actualUser = userRepo.findByFirstName("testFirstName");
		Assertions.assertNotNull(actualUser);
		
		userRepo.delete(actualUser);
		User updatedUser = userRepo.findByFirstName("testFirstName");
		
		Assertions.assertNull(updatedUser);
	}

}
