/*
 * Copyright (c) 2023.
 * github.com/EgorAdonev
 */
package ru.adonev.statcollector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.adonev.statcollector.model.Event;
import ru.adonev.statcollector.model.EventType;
import ru.adonev.statcollector.repository.EventRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class StatcollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatcollectorApplication.class, args);
	}
	@Bean
	CommandLineRunner saveData(EventRepository repo){
		return args -> {
			Event event1 = new Event(1,EventType.EVENT_1, LocalDate.now());
			Event event2 = new Event(2,EventType.EVENT_3, LocalDate.now());
			Event event3 = new Event(3,EventType.EVENT_2, LocalDate.now());
			ArrayList<Event> l = new ArrayList<>();
			l.add(event1); l.add(event2); l.add(event3);
			repo.saveAll(l);
		};
	}
}
