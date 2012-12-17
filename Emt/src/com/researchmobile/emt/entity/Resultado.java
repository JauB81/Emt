package com.researchmobile.emt.entity;

import java.io.Serializable;

public class Resultado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Persona[] persona;

	public Persona[] getPersona() {
		return persona;
	}

	public void setPersona(Persona[] persona) {
		this.persona = persona;
	}

}
