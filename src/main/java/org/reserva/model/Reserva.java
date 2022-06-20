package org.reserva.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_reserva")
@Data
public class Reserva {
	@Id
	@Column(name="id_prod")
	private String idprod;
	@Column(name="des_prod")
	private String descripcion;
	@Column(name="stk_prod")
	private int stock;
	@Column(name="pre_prod")
	private double precio;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria", insertable = false, updatable = false)
	private Carrera categoria;
	
	private int idcategoria;
	
	@Column(name="est_prod")
	private int estado;
	
	private int idprovedor;
}
