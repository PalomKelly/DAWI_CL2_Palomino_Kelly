package org.reserva.controller;

import org.ciberfarma.repository.IUsuarioRepository;
import org.reserva.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioRepository repou;
	
	@GetMapping("/login/cargar")
	public String cargarform(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login/validar")
	public String validarIngreso(@ModelAttribute Usuario usuario, Model model) {
		System.out.println(usuario);	// correo y clave
		
		Usuario u = repou.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());
		
		if (u == null) {
			model.addAttribute("error","Usuario o Clave incorrecta");
			return "login";
		}
		model.addAttribute("usuario",u);
		
		return "principal";
	}
	
	@GetMapping("/usuario/cargar")
	public String cargarformRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		// -- listado para combos (opcional)
		
		return "registro";
	}
	
	@PostMapping("/usuario/grabar")
	public String grabarRegistro(@ModelAttribute Usuario usuario, Model model) {
		
		System.out.println(usuario); //del form registro
		usuario.setTipo(2);
		usuario.setEstado(1);
		
		try {
			repou.save(usuario);
			model.addAttribute("success", "Registro exitoso");
		} catch (Exception e) {
			model.addAttribute("error","Error al registrar" + e.getMessage());
		}
		
		return "registro";
	}
}
