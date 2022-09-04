package com.telusko;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@Controller
@RestController
public class AlienResource {

	@Autowired
	AlienRepository repo;
	@Autowired
	@Qualifier("lap")
	private Laptop laptop;

	// @GetMapping("aliens")
	@RequestMapping(path = "/aliens", produces = { "application/xml", "application/json" })
	public List<Alien> fetchAliens() {
		laptop.compile();
		System.out.println("fetchAliens");
		List<Alien> aliens = (List<Alien>) repo.findAll();

		return aliens;
	}

	// @RequestMapping("/home")
//	public String home() {
//		return "home.jsp";
//	}
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home.jsp");
		return mv;
	}

	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		return "home.jsp";
	}

	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int id) {
		System.out.println(repo.findByName("Sasha"));
		System.out.println(repo.findByIdGreaterThan(102));
		System.out.println(repo.findByNameSorted("Sasha"));
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(id).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}	
	
	@RequestMapping(path = "/alien/{id}", produces = { "application/xml", "application/json" })
	@ResponseBody
	public Alien getAliensById(@PathVariable int id) {
		Alien alien = repo.findById(id).orElse(new Alien());
		return alien;
	}

}
