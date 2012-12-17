package com.researchmobile.emt.entity;

public class Datos {

	public Persona BuscarDato(int opcion, String codigo) {
		Persona[] persona = ListaPersonas();
		int tamano = persona.length;
		for (int i = 0; i < tamano; i++){
			if (opcion == 1){
				if (codigo.equalsIgnoreCase(persona[i].getCodigoBarras())){
					return persona[i];
				}
			}else if (opcion == 2){
				if (codigo.equalsIgnoreCase(persona[i].getCarnet())){
					return persona[i];
				}
			}
			else if (opcion == 3){
				if (codigo.equalsIgnoreCase(persona[i].getCodigoPiloto())){
					return persona[i];
				}
			}
			else if (opcion == 4){
				if (codigo.equalsIgnoreCase(persona[i].getSiglas())){
					return persona[i];
				}
			}
		}
		return null;
		
	}
	
	public String[] infractor(){
		String [] lista = {
				"Prestador del Servicio",
				"Conductor",
				"Inspector Particular"
		};
		return lista;
	}
	
	public String[] articulos(){
			String [] listaArticulos = {
					"72.1.1.",
					"72.1.2.",
					"72.1.3.",
					"72.1.4.",
					"72.2.1.",
					"72.2.2.",
					"72.2.3.",
					"72.2.4.",
					"72.3.1.",
					"72.3.2.",
					"72.3.3.",
					"72.3.4.",
					"72.4.1.",
					"72.4.2.",
					"72.4.3.",
					"72.4.4.",
					"72.4.5.",
					"72.4.6.",
					"72.4.7.",
					"73.1.1.",
					"73.1.2.",
					"73.1.3.",
					"73.1.4.",
					"73.1.5.",
					"73.1.6.",
					"73.1.7.",
					"73.1.8.",
					"73.1.9.",
					"73.1.10.",
					"73.1.11.",
					"73.1.12.",
					"73.1.13.",
					"73.2.1.",
					"73.2.2.",
					"73.2.3.",
					"73.2.4.",
					"73.2.5.",
					"73.2.6.",
					"73.2.7.",
					"73.2.8.",
					"73.2.9.",
					"73.2.10.",
					"73.2.11.",
					"73.2.12.",
					"73.2.13.",
					"74.1.1.",
					"74.1.2.",
					"74.2.1.",
					"74.2.2.",
					"74.2.3.",
					"76.1.",
					"68"};
			return listaArticulos;
	}
	
	public String Articulo(String articulo) {
		String art = articulo.substring(0, 2);
		return art;
	}
	
	public String DescripcionArticulo(String articulo) {
		String descripcion = "";
		if (articulo.equalsIgnoreCase("68")){
			descripcion = "Cuando se verifique una contravención dos o más veces a una misma disposición de este Reglamento en un plazo de seis meses, se impondrá el doble de la multa aplicable a la fecha en la que se reincide. ";
			return descripcion;
		}else if (articulo.equalsIgnoreCase("76.1.")){
			descripcion = "Servicio de Transporte mediante unidades no registradas, dará lugar a la imposición de una multa de CINCUENTA MIL QUETZALES (Q. 50,000.00).";
			return descripcion;
		}else{
			String sub = articulo.substring(0, 4);
			if (sub.equalsIgnoreCase("74.2")){
				descripcion = "A LOS INSPECTORES PARTICULARES ";
			}else if (sub.equalsIgnoreCase("74.1")){
				descripcion = "A LOS INSPECTORES PARTICULARES ";
			}else if (sub.equalsIgnoreCase("73.2")){
				descripcion = "A  LOS CONDUCTORES ";
			}else if (sub.equalsIgnoreCase("73.1")){
				descripcion = "A  LOS CONDUCTORES ";
			}else if (sub.equalsIgnoreCase("72.4")){
				descripcion = "A LOS PRESTADORES DEL SERVICIO ";
			}else if (sub.equalsIgnoreCase("72.3")){
				descripcion = "A LOS PRESTADORES DEL SERVICIO ";
			}else if (sub.equalsIgnoreCase("72.2")){
				descripcion = "A LOS PRESTADORES DEL SERVICIO ";
			}else if (sub.equalsIgnoreCase("72.1")){
				descripcion = "A LOS PRESTADORES DEL SERVICIO ";
			}
			return descripcion;
		}
	}
	
	public String GeneraMulta(String articulo) {
		String multa = "";
		if (articulo.equalsIgnoreCase("68")){
			multa = "multa doble";
			return multa;
		}else if (articulo.equalsIgnoreCase("76.1.")){
			multa = "Q. 50,000.00";
			return multa;
		}else{
			String sub = articulo.substring(0, 4);
			if (sub.equalsIgnoreCase("74.2")){
				multa = "Q. 250.00";
			}else if (sub.equalsIgnoreCase("74.1")){
				multa = "Q. 100.00";
			}else if (sub.equalsIgnoreCase("73.2")){
				multa = "Q. 500.00";
			}else if (sub.equalsIgnoreCase("73.1")){
				multa = "Q. 250.00";
			}else if (sub.equalsIgnoreCase("72.4")){
				multa = "Q. 5,000.00";
			}else if (sub.equalsIgnoreCase("72.3")){
				multa = "Q. 1,500.00";
			}else if (sub.equalsIgnoreCase("72.2")){
				multa = "Q. 1,000.00";
			}else if (sub.equalsIgnoreCase("72.1")){
				multa = "Q. 500.00";
			}
			return multa;
		}
	}
	
	public Persona[] ListaPersonas(){
		Persona[] persona = new Persona[3];
		
		Persona persona1 = new Persona();
		persona1.setCodigoBarras("A0000234");
		persona1.setCarnet("carnet1");
		persona1.setCodigoPiloto("piloto1");
		persona1.setSiglas("XYZ");
		
		persona1.setTarjeta("ABC123");
		persona1.setPlaca("SD888P");
		persona1.setTipo("URBANO");
		persona1.setMarca("Toyota");
		persona1.setEmpresa("El Quetzal");
		persona1.setRegistro("110021");
		persona1.setSector("Sector1");
		persona1.setRuta("Ruta1");
		
		
		persona1.setNombre("Marvin Otoniel");
		persona1.setApellido("Perez Jula");
		persona1.setDireccionDomiciliar("Ciudad Capital");
		persona1.setLicenciaClasificaion("Clase A");
		persona1.setLicenciaNumero("12345");
		
		Historial[] historial1 = new Historial[1];
		Historial historial1a = new Historial();
		historial1a.setHistorial("Multa 1");
		historial1a.setTipo("multa");
		historial1a.setFecha("06/09/2012");
		historial1[0] = historial1a;
		persona1.setHistorial(historial1);
		
		persona[0] = persona1;
		
		Persona persona2 = new Persona();
		persona2.setCodigoBarras("A0000235");
		persona2.setCarnet("carnet2");
		persona2.setCodigoPiloto("piloto2");
		persona2.setSiglas("MNO");
		
		persona2.setTarjeta("ABC000");
		persona2.setPlaca("SD222P");
		persona2.setTipo("EXTRAURBANO");
		persona2.setMarca("Toyota");
		persona2.setEmpresa("El Quetzal");
		persona2.setRegistro("110021");
		persona2.setSector("Sector1");
		persona2.setRuta("Ruta1");
		
		persona2.setNombre("María");
		persona2.setApellido("Méndez Pino");
		persona2.setDireccion("Ciudad Capital");
		persona2.setLicenciaClasificaion("Clase B");
		persona2.setLicenciaNumero("9898987");
		
		Historial[] historial2 = new Historial[1];
		Historial historial1b = new Historial();
		historial1b.setHistorial("Multa 1");
		historial1b.setTipo("multa");
		historial1b.setFecha("05/08/2012");
		historial2[0] = historial1b;
		persona2.setHistorial(historial2);
		persona[1] = persona2;
		
		Persona persona3 = new Persona();
		persona3.setCodigoBarras("A0000345");
		persona3.setCarnet("carnet3");
		persona3.setCodigoPiloto("piloto3");
		persona3.setSiglas("HIJ");
		
		persona3.setTarjeta("AND876");
		persona3.setPlaca("AS999P");
		persona3.setTipo("URBANO");
		persona3.setMarca("Toyota");
		persona3.setEmpresa("El Quetzal");
		persona3.setRegistro("110021");
		persona3.setSector("Sector3");
		persona3.setRuta("Ruta3");
		
		persona3.setNombre("Julio Cesar");
		persona3.setApellido("Chan Pelaez");
		persona3.setDireccion("Ciudad Capital");
		persona3.setLicenciaClasificaion("Clase C");
		persona3.setLicenciaNumero("6543345");
		
		Historial[] historial3 = new Historial[1];
		Historial historial1c = new Historial();
		historial1c.setHistorial("Cita 1");
		historial1c.setTipo("Citatorio");
		historial1c.setFecha("07/08/2012");
		historial3[0] = historial1c;
		persona3.setHistorial(historial3);
		
		persona[2] = persona3;
		
		return persona;
	}

	public String[] VerHistorial(Persona persona) {
		int tamano = persona.getHistorial().length;
		String[] historial = new String[3];
		historial[0] = persona.getHistorial()[0].getHistorial();
		historial[1] = persona.getHistorial()[0].getTipo();
		historial[2] = persona.getHistorial()[0].getFecha();
		return historial;
	}
}
