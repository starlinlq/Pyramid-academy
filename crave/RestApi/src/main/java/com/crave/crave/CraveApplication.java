package com.crave.crave;

import com.crave.crave.model.Category;
import com.crave.crave.repository.CategoryRepo;
import com.crave.crave.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CraveApplication {
	@Autowired
	private CategoryRepo categoryRepo;

	public static void main(String[] args) {
		SpringApplication.run(CraveApplication.class, args);
	}


//	@Bean
//	public CommandLineRunner loadCategories(){
//		return (args -> {
//			Category breakfast = new Category("Breakfast");
//			Category dinner = new Category("Dinner");
//
//			categoryRepo.saveAll(List.of(breakfast,dinner));
//		});
//	}

}
