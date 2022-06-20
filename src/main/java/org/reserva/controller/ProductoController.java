package org.reserva.controller;

import org.ciberfarma.repository.ICategoriaRepository;
import org.ciberfarma.repository.IProductoRepository;
import org.ciberfarma.repository.IProveedorRepository;
import org.reserva.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {
	
	@Autowired
	private ICategoriaRepository repoc;
	
	@Autowired
	private IProveedorRepository repop;
	
	@Autowired
	private IProductoRepository reprod;
	
	@GetMapping("/producto/cargar")
	public String cargarform(Model model) {
		model.addAttribute("producto", new Reserva());
		// listado -> cboCategorias
		model.addAttribute("lstCategorias",repoc.findAll());
		// listado -> cboProveedores
		model.addAttribute("lstProveedores",repop.findAll());
		
		return "crudproducto";
	}
	
	@PostMapping("/producto/grabar")
	public String grabarform(@ModelAttribute Reserva producto, Model model) {
		System.out.println(producto);
		model.addAttribute("lstCategorias",repoc.findAll());
		model.addAttribute("lstProveedores",repop.findAll());
		
		//Grabar datos en BBDD
		try {
			reprod.save(producto);	//merge - si el ID existe lo actualiza, sino registra
			model.addAttribute("success","Proceso realizado con éxito");
		} catch (Exception e) {
			model.addAttribute("error","Error al registrar producto");
		}
		return "crudproducto";
	}
	
	@GetMapping("/producto/listar")
	public String listadoProducto(Model model) {
		model.addAttribute("lstProductos",reprod.findAll());
		return "listado";
	}

	@PostMapping("/producto/editar")
	public String buscarProd(@ModelAttribute Reserva p, Model model) {
		System.out.println(p);
		model.addAttribute("producto",reprod.findById(p.getIdprod()));
		
		model.addAttribute("lstCategorias",repoc.findAll());
		model.addAttribute("lstProveedores",repop.findAll());
		
		return "crudproducto";
	}
	
}
