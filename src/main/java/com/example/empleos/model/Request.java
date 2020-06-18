/**
 * Esta clase representa una entidad (un registro) en la tabla de Solicitudes de la base de datos
 */
package com.example.empleos.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Requests")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer id;
	private Date date;
	private String comments;
	private String file;

	@OneToOne
	@JoinColumn(name = "idVacant")
	private Vacant vacant;

	@OneToOne
	@JoinColumn(name = "idUser")
	private User user;

	public Request() {
		this.date = new Date();
	}

	public Request(Date date) {
		this.date = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date fecha) {
		this.date = fecha;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String archivo) {
		this.file = archivo;
	}

	public Vacant getVacant() {
		return vacant;
	}

	public void setVacant(Vacant vacant) {
		this.vacant = vacant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", fecha=" + date + ", comentarios=" + comments + ", archivo=" + file
				+ ", vacante=" + vacant + ", usuario=" + user + "]";
	}

}
