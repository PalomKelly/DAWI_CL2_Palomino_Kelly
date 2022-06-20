package org.reserva.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_usuarios")
@Data
public class Usuario {
	@Id
	@Column(name = "cod_usua")
	private int codigo;
	@Column(name = "nom_usua")
	private String nombre;
	@Column(name = "ape_usua")
	private String apellido;
	@Column(name = "usr_usua")
	private String correo;
	@Column(name = "cla_usua")
	private String clave;
	@Column(name = "fna_usua")
	private String fechanacimiento;
	@Column(name = "idtipo")
	private int tipo;
	@Column(name = "est_usua")
	private int estado;
}
