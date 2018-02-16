/**
 * 
 */
package com.learn.oauth.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learn.oauth.exception.InvalidUserException;
import com.learn.oauth.model.User;
import com.learn.oauth.repository.UserRepository;

/**
 * @author vkalyana
 *
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("UserService unit tests")
public class UserServiceImplTest {

	@MockBean
	UserRepository repository;
	
	@Autowired
	UserService userService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setUp() throws Exception {
		User testUser1 = new User();
		testUser1.setActive(true);
		testUser1.setId("1");
		testUser1.setFirstName("testFirstName");
		testUser1.setLastName("testLastName");
		testUser1.setUserName("testUserName1");
		testUser1.setPassword("testPassword1");
		testUser1.setGender("male");
		testUser1.setEmail("testemail@gmail.com");
		
		User testUser2 = new User();
		testUser2.setActive(true);
		testUser2.setId("2");
		testUser2.setFirstName("testFirstName");
		testUser2.setLastName("testLastName");
		testUser2.setUserName("testUserName2");
		testUser2.setPassword("testPassword2");
		testUser2.setGender("male");
		testUser2.setEmail("testemail@gmail.com");
		
		when(repository.findAll()).thenReturn(Arrays.asList(testUser1, testUser2));
		when(repository.findOne("1")).thenReturn(testUser1);
		when(repository.findOne("2")).thenReturn(testUser2);
		when(repository.findByFirstName("testFirstName")).thenReturn(testUser1);
		when(repository.findByUserNameAndPassword("testUserName2", "testPassword2")).thenReturn(testUser2);
		when(repository.findByUserNameAndPassword("unknown", "unknown")).thenThrow(InvalidUserException.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception {
		repository = null;
		userService = null;
	}

	@DisplayName("should find 2 users")
	@Test
	public void testFindAllUsers() {
		List<User> list = userService.findAll();
		
		Assertions.assertNotNull(list);
		assertThat(list.size()).isEqualTo(2);
		
	}
	
	@DisplayName("should find user by id")
	@Test
	public void testFindUserById() {
		User user = userService.findById("1");
		
		Assertions.assertNotNull(user);
		Assertions.assertAll(
				() -> Assertions.assertEquals("testFirstName", user.getFirstName()),
				() -> Assertions.assertEquals("testUserName1", user.getUserName()),
				() -> Assertions.assertEquals("testPassword1", user.getPassword())
				);
		
	}

	@DisplayName("should find user by first name")
	@Test
	public void testFindUserByFirstName() {
		User user = userService.findByFirstName("testFirstName");
		
		Assertions.assertNotNull(user);
		Assertions.assertAll(
				() -> Assertions.assertEquals("testFirstName", user.getFirstName()),
				() -> Assertions.assertEquals("testUserName1", user.getUserName()),
				() -> Assertions.assertEquals("testPassword1", user.getPassword())
				);
		
	}

	@DisplayName("should find user by credentials")
	@Test
	public void testFindUserByUserNameAndPassword() {
		User user;
		try {
			user = userService.findByUserNameAndPassword("testUserName2", "testPassword2");
			Assertions.assertNotNull(user);
			Assertions.assertAll(
					() -> Assertions.assertEquals("testFirstName", user.getFirstName()),
					() -> Assertions.assertEquals("testUserName2", user.getUserName()),
					() -> Assertions.assertEquals("testPassword2", user.getPassword())
					);
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
	}
	
	@DisplayName("should throw Authentication Error when credentials are wrong")
	@Test
	public void testThrowExceptionWhenCredentialsAreWrong() {
		InvalidUserException error = Assertions.assertThrows(InvalidUserException.class, 
				() -> userService.findByUserNameAndPassword("unknown", "unknown"));
		
		Assertions.assertEquals("Authentication Failed", error.getTitle());
		Assertions.assertEquals("Not a valid credentials", error.getMessage());
	}
}
