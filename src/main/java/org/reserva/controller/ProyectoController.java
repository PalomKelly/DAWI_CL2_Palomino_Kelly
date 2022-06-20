package org.reserva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProyectoController { // Equivalente al Servlet...
	
	// crear y mapear acciones : GET (url, href) y POST (form)
	
	@GetMapping("/saludo")
	public String greeting(@RequestParam(name="name", 
	required=false, defaultValue="World") String name, Model model) {
		
		// @RequestParam = acción que recibe parámetros
		// 		required=false = el parámetro es opcional
		// 		defaultValue="World" = valor por defecto, si no se pasa un parámetro
		// En Servlet : name = request.getParameter("name")
		// Model : es una clase utilizada para "enviar" información (atributos, páginas)
		// 
		
		model.addAttribute("name", name);
		// En Servlet: request.setAttribute("name",name)
		return "saluditos";	// "saluditos" es el nombre de la página
		// En Servlet: request.getRequestDispatcher...
	}
	
	@PostMapping("/saludo")
	public String greetingPost(@RequestParam(name="name", 
	required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "saluditos";
	}
}
