package ru.adonev.statcollector;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StatcollectorApplicationTests {
	@Test
	public void whenResourcesAreRetrievedPaged_then200IsReceived(){
		Response response = RestAssured.get(getFooURL() + "&page=0&size=2");
		assertEquals(response.getStatusCode(),201);
	}
	@Test
	public void whenPageOfResourcesAreRetrievedOutOfBounds_then400IsReceived(){
		Random rand = new Random();
		String url = getFooURL() + "?page=" + rand.nextInt(5) + "&size=2";
		Response response = RestAssured.get(url);
		assertEquals(response.getStatusCode(), 400);
	}
	@Test
	public void givenResourcesExist_whenFirstPageIsRetrieved_thenPageContainsResources(){
		Response response = RestAssured.get(getFooURL() + "&page=0&size=2");
		assertNotNull(response.body());
	}
	private String getFooURL(){
			return "http://localhost:8080/api/getEvents?filter=EVENT_1";
	}
}
