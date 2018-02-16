/**
 * 
 */
package com.learn.oauth.repository;

import java.util.ArrayList;
import java.util.List;

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

import com.learn.oauth.model.LogEvent;

/**
 * @author vkalyana
 *
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DataMongoTest()
public class LogEventRepositoryTest {


	@Autowired
	LogEventRepository eventRepository;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		LogEvent actualEvent1 = new LogEvent("TestEntity", "operation", "");
		LogEvent actualEvent2 = new LogEvent("TestEntity", "operation", "");
		LogEvent actualEvent3 = new LogEvent("TestEntity", "operation", "");
		
		List<LogEvent> records = new ArrayList<LogEvent>();
		records.add(actualEvent1);
		records.add(actualEvent2);
		records.add(actualEvent3);
		
		eventRepository.save(records);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception {
		eventRepository.delete(eventRepository.findByEntity("TestEntity"));
		eventRepository.delete(eventRepository.findByEntity("NewEntity"));
	}

	@DisplayName("should list records saved")
	@Test
	public void testFindAll() {
		List<LogEvent> records = new ArrayList<LogEvent>();
		
		records = eventRepository.findByEntity("TestEntity");
		Assertions.assertEquals(3, records.size());
		
		records.forEach((LogEvent event) -> {
			Assertions.assertAll("Collections", 
					() -> Assertions.assertEquals("TestEntity", event.getEntity()),
					() -> Assertions.assertEquals("operation", event.getOperation()),
					() -> Assertions.assertEquals("", event.getErrorMessage()),
					() -> Assertions.assertNotNull(event.getId()),
					() -> Assertions.assertNotNull(event.getUpdated()));
		});
		
	}
	
	@DisplayName("should save log event with id not null")
	@Test
	public void testLogEventSave() {
		LogEvent actualEvent = eventRepository.save(new LogEvent("NewEntity", "operation", ""));
		Assertions.assertAll("Collections", 
				() -> Assertions.assertEquals("NewEntity", actualEvent.getEntity()),
				() -> Assertions.assertEquals("operation", actualEvent.getOperation()),
				() -> Assertions.assertEquals("", actualEvent.getErrorMessage()),
				() -> Assertions.assertNotNull(actualEvent.getId()),
				() -> Assertions.assertNotNull(actualEvent.getUpdated()));
	}

}
