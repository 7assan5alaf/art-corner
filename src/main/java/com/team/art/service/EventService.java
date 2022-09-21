package com.team.art.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.art.dto.EventDto;
import com.team.art.error.RecordNotFoundException;
import com.team.art.model.Event;
import com.team.art.model.User;
import com.team.art.repository.EventRepository;
import com.team.art.repository.UserReopsitory;
import com.team.art.response.ApiResponse;

@Service
public class EventService {

	 @Autowired
	 private EventRepository eventRepository;
	 @Autowired
	 private UserReopsitory userReopsitory;
	 @Autowired
	 private TokenService tokenService;
	public ApiResponse createEvent(EventDto eventDto) {
		User user=userReopsitory.findByEmail(eventDto.getEmail());
		if(Objects.isNull(user)) {
			return new ApiResponse("invalid", "Invalid username and password");
		}
		Event event=new Event();
		event.setDate(eventDto.getDate());
		event.setEmail(eventDto.getEmail());
		event.setLocation(eventDto.getLocation());
		event.setPhone(eventDto.getPhone());
		event.setUser(user);
		eventRepository.save(event);
		return new ApiResponse("success","Add event success! Wait for admin approval");
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Event findById(Long id) {
	Optional<Event>event=eventRepository.findById(id);
	  if(event.isEmpty()) {
		  throw new RecordNotFoundException("event not found");
	  }
		return event.get();
	}

	public void save(Event event) {
		eventRepository.save(event);
	}

	public List<Event> getAllByUser(String token) {
		tokenService.authintecation(token);
		User user=tokenService.getUser(token);
		return eventRepository.findByUser_Id(user.getId());
	}

	public ApiResponse deleteEvent(Long id) {
		try {
			Event event=eventRepository.findById(id).orElseThrow(
					()->new RecordNotFoundException("Event not found"));
			eventRepository.delete(event);
			return new ApiResponse("success", "delete event successfully");
		} catch (RecordNotFoundException e) {
		   e.getMessage();
		}
		return null;
	}

}
