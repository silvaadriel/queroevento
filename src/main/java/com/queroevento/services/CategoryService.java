package com.queroevento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queroevento.models.Category;
import com.queroevento.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	public void delete(Category category) {
		categoryRepository.delete(category);
	}

	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findByUrlNameIgnoreCase(String urlName) {
		return categoryRepository.findByUrlNameIgnoreCase(urlName);
	}

	public String nameToUrlName(String name) {

		String urlName = name.replaceAll(" ", "_").replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e")
				.replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u")
				.replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I")
				.replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C')
				.replace('ñ', 'n').replace('Ñ', 'N');

		return urlName.toLowerCase();
	}

}