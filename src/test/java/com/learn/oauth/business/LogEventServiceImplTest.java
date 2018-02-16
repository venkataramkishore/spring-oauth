/**
 * 
 */
package com.learn.oauth.business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learn.oauth.model.LogEvent;
import com.learn.oauth.repository.LogEventRepository;

/**
 * @author vkalyana
 *
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
 class LogEventServiceImplTest {

	@TestConfiguration
	static class LogEventServiceImplTestContextConfiguration {
		  
        @Bean
        public LogEventService logEventService() {
            return new LogEventServiceImpl();
        }
    }
	
	 @MockBean
	 LogEventRepository eventRepository;
	 
	 @Autowired
	 LogEventService logEventService;
	 
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	 void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	 void tearDown() throws Exception {
		logEventService = null;
	}

	@DisplayName("should initialise repository bean")
	@Test
	 void testRepositoryInitialised() {
		Assertions.assertNotNull(eventRepository);
	}

	@DisplayName("should return the event as saved ")
	@Test
	 void testSaveLogEvent() {
		LogEvent eventModel = new LogEvent("Entity", "Operation", "");
		Mockito.when(eventRepository.save(eventModel)).thenReturn(eventModel);	
		
		LogEvent actualEvent = logEventService.log(eventModel);
		Assertions.assertAll("Collections", 
				() -> Assertions.assertNotNull(actualEvent),
				() -> Assertions.assertEquals("Entity", actualEvent.getEntity()),
				() -> Assertions.assertEquals("Operation", actualEvent.getOperation()),
				() -> Assertions.assertEquals("", actualEvent.getErrorMessage()));
		
	}
	
}
