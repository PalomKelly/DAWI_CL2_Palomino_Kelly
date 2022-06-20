package org.reserva.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_Carreras")
@Data
public class Carrera {
	@Id
	private int idcarrera;
	private String descripcion;
}
