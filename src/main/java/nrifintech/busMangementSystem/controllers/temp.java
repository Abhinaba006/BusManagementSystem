package nrifintech.busMangementSystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class temp {
	@GetMapping
	public void as() {
		System.out.println("hello");
	}
}
