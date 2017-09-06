package com.queroevento.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queroevento.models.Event;
import com.queroevento.repositories.EventRepository;
import com.queroevento.utils.CatalogStatusEvent;
import com.queroevento.utils.StatusEvent;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public Event save(Event event) {
		return eventRepository.save(event);
	}

	public void delete(Event event) {
		eventRepository.delete(event);
	}

	public Event findOne(Long id) {
		return eventRepository.findOne(id);
	}

	public List<Event> findByEventDateAfterAndCatalogStatusAndStatusOrderByEventDate(Date date,
			CatalogStatusEvent catalogStatusEvent, StatusEvent statusEvent) {
		return eventRepository.findByEventDateAfterAndCatalogStatusAndStatusOrderByEventDate(date, catalogStatusEvent,
				statusEvent);
	}

	public List<Event> findByEventDateBeforeOrderByEventDateDesc(Date date) {
		return eventRepository.findByEventDateBeforeOrderByEventDateDesc(date);
	}

	public List<Event> findByCategoryIdOrderByEventDate(Long id) {
		return eventRepository.findByCategoryIdOrderByEventDate(id);
	}

	public List<Event> findByOrderByPeopleEstimateDesc() {
		return eventRepository.findByOrderByPeopleEstimateDesc();
	}

	public Event findByUrlTitle(String url) {
		return eventRepository.findByUrlTitle(url);
	}

	public List<Event> findByTurbineTypeIsNotNullOrderByTurbineTypeDesc() {
		return eventRepository.findByTurbineTypeIsNotNullOrderByTurbineTypeDesc();
	}

	public List<Event> findByCatalogStatusOrderByCreateEventDate(CatalogStatusEvent catalogStatus) {
		return eventRepository.findByCatalogStatusOrderByCreateEventDate(catalogStatus);
	}
	
	public List<Event> findByStatusOrderByEventDate(StatusEvent status) {
		return eventRepository.findByStatusOrderByEventDate(status);
	}
	
	public List<Event> getEventByKeywordIgnoreCase(String keyword) {
		return eventRepository.getEventByKeywordIgnoreCase(keyword);
	}

	public List<Event> findByUserId(Long id) {
		return eventRepository.findByUserIdOrderByEventDate(id);
	}

	public String titleToUrlTitle(String title) {

		String urlTitle = title.replaceAll(" ", "-").replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e")
				.replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u")
				.replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I")
				.replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C')
				.replace('ñ', 'n').replace('Ñ', 'N');

		return urlTitle.toLowerCase();
	}


}