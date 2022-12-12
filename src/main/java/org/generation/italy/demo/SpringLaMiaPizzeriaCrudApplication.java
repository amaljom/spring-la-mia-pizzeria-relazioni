package org.generation.italy.demo;

import java.time.LocalDate;
import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.service.DrinkService;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private PromozioneService promozioneService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Promozione p1 = new Promozione(LocalDate.parse("2022-01-31"), LocalDate.parse("2022-12-31"), "Nuova Promo");
		Promozione p2 = new Promozione(LocalDate.parse("2023-01-31"), LocalDate.parse("2023-12-31"), "Altra Promo");
		
		promozioneService.save(p1);
		promozioneService.save(p2);
		
		Pizza b1 = new Pizza("rossa", "pomodori", 10, p1);
		Pizza b2 = new Pizza("bianca", "rosamrino", 5, p1);
		Pizza b3 = new Pizza("boscaiola", "funghi e salsiccia", 13, p1);
		Pizza b4 = new Pizza("napoli", "alici", 10, p2);
		Pizza b5 = new Pizza("samename", "pizza", 10, null);
		
		pizzaService.save(b1);
		pizzaService.save(b2);
		pizzaService.save(b3);
		pizzaService.save(b4);
		pizzaService.save(b5);
		
		
		List<Pizza> pizze = pizzaService.findAll();
		
		Drink d1 = new Drink("mojito", "bho", 10);
		Drink d2 = new Drink("Zacapa", "rum", 7);
		Drink d3 = new Drink("rum&cola", "cola", 9);
		Drink d4 = new Drink("fanta", "fanta", 5);
		Drink d5 = new Drink("samename", "drink", 10);
		
		drinkService.save(d1);
		drinkService.save(d2);
		drinkService.save(d3);
		drinkService.save(d4);
		drinkService.save(d5);
		
		
		List<Drink> drinks = drinkService.findAll();
		
		// test 
		// promozioneService.deletePromotionById(1);
		
		
		List<Promozione> promos = promozioneService.findPizze();
		System.out.println("---------------------------");
		System.out.println("Solo le pizze con promozioni");
		for (Promozione p : promos) {
			for (Pizza pizza : p.getPizzas()) {
				System.err.println("\t" + pizza.getPromozione() + " - " + pizza.getNome());
			}
		}
	}
	
	
	

}
