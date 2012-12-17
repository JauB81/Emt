package com.researchmobile.emt.entity;

import java.io.Serializable;

public class Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//ADICIONAL PARA LOGIN
	private String codigoBarras;
	private String carnet;
	private String codigoPiloto;
	private String siglas;
	
	//DATOS DEL VEHICULO
	private String tarjeta;
	private String placa;
	private String tipo;
	private String marca;
	private String empresa;
	private String registro;
	private String sector;
	private String ruta;
	
	//DATOS DEL INFRACTOR
	private String nombre;
	private String apellido;
	private String direccion;
	private String licenciaClasificaion;
	private String licenciaNumero;
	private String direccionDomiciliar;
	
	//DATOS DE LA INFRACCION
	private String dia;
	private String mes;
	private String ano;
	private String hora;
	private String lugar;
	
	//INFRACCION COMETIDA POR
	private String infractor;
	private String articuloNumero;
	private String articuloDescripcion;
	private String inciso;
	private String valor;
	
	private Historial[] historial;
	
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getCarnet() {
		return carnet;
	}
	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}
	public String getCodigoPiloto() {
		return codigoPiloto;
	}
	public void setCodigoPiloto(String codigoPiloto) {
		this.codigoPiloto = codigoPiloto;
	}
	public String getSiglas() {
		return siglas;
	}
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	public Historial[] getHistorial() {
		return historial;
	}
	public void setHistorial(Historial[] historial) {
		this.historial = historial;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getLicenciaClasificaion() {
		return licenciaClasificaion;
	}
	public void setLicenciaClasificaion(String licenciaClasificaion) {
		this.licenciaClasificaion = licenciaClasificaion;
	}
	public String getLicenciaNumero() {
		return licenciaNumero;
	}
	public void setLicenciaNumero(String licenciaNumero) {
		this.licenciaNumero = licenciaNumero;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getInfractor() {
		return infractor;
	}
	public void setInfractor(String infractor) {
		this.infractor = infractor;
	}
	public String getArticuloNumero() {
		return articuloNumero;
	}
	public void setArticuloNumero(String articuloNumero) {
		this.articuloNumero = articuloNumero;
	}
	public String getArticuloDescripcion() {
		return articuloDescripcion;
	}
	public void setArticuloDescripcion(String articuloDescripcion) {
		this.articuloDescripcion = articuloDescripcion;
	}
	public String getInciso() {
		return inciso;
	}
	public void setInciso(String inciso) {
		this.inciso = inciso;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDireccionDomiciliar() {
		return direccionDomiciliar;
	}
	public void setDireccionDomiciliar(String direccionDomiciliar) {
		this.direccionDomiciliar = direccionDomiciliar;
	}

}
