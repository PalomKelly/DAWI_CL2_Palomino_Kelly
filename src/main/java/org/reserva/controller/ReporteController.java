package org.reserva.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import org.ciberfarma.repository.ICategoriaRepository;
import org.ciberfarma.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


@Controller
public class ReporteController {
	
	@Autowired
	private ICategoriaRepository repoc;
	
	@Autowired
	private IProductoRepository repo;
	
	@GetMapping("/reporte/cargar")
	public String cargarform(Model model) {
		model.addAttribute("lstCategorias",repoc.findAll());
		//model.addAttribute("producto",new Producto());
		return "listado2";
	}

	@PostMapping("/reporte/listar")
	public String listarform(@RequestParam(name="categoria") int categoria, Model model) {
		model.addAttribute("lstCategorias",repoc.findAll());
		model.addAttribute("lstProductos",repo.findByIdcategoria(categoria));
		return "listado2";
	}
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping("/reporte")
	public void reportePDF(HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/pdf");
		try {
			String ru = resourceLoader.getResource("classpath:demo.jasper").getURI().getPath();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@GetMapping("/grafico")
	public void reporteGrafico(HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename=\"grafico.pdf\";");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/pdf");
		try {
			String ru = resourceLoader.getResource("classpath:grafico.jasper").getURI().getPath();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
