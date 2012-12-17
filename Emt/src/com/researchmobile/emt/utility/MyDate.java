package com.researchmobile.emt.utility;

import java.util.Calendar;

public class MyDate {
	private Calendar calendar = Calendar.getInstance();
	
	public int Semana (){
		int semana = getCalendar().get(Calendar.WEEK_OF_MONTH);
		return semana;
	}
	
	public int SemanaAno (){
		int semana = getCalendar().get(Calendar.WEEK_OF_YEAR);
		return semana;
	}
	
	public String SemanaParImpar(){
		String semana="";
		int numSemana = getCalendar().get(Calendar.WEEK_OF_YEAR);
		if (numSemana%2 > 0){
			semana = "Impar";
		}else{
			semana = "Par";
		}
		
		return semana;
	}
	
	public String Dia (){
		String dia = null;
		if (getCalendar().get(Calendar.DAY_OF_WEEK) == 1){
        	dia = "DOMINGO";
        }else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 2){
        	dia = "LUNES";
        }else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 3){
        	dia = "MARTES";
        } else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 4){
        	dia = "MIERCOLES";
        }else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 5){
        	dia = "JUEVES";
        }else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 6){
        	dia = "VIERNER";
        }else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 7){
        	dia = "SABADO";
        }
		
		return dia;
	}
	
	public String NumeroDia (){
		String dia = null;
		dia = String.valueOf(getCalendar().get(Calendar.DAY_OF_MONTH));
		return dia;
	}
	
	public String Mes (){
		String mes = null;
		
		mes = String.valueOf(getCalendar().get(Calendar.MONTH ) + 1);
		return mes;
	}
	
	public String Ano (){
		String anio = null;
		
		anio = String.valueOf(getCalendar().get(Calendar.YEAR));
		return anio;
	}
	
	public String LetraDia (){
		String dia = null;
		if (getCalendar().get(Calendar.DAY_OF_WEEK) == 1){
        	dia = "D";
        }else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 2){
        	dia = "L";
        }else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 3){
        	dia = "M";
        } else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 4){
        	dia = "K";
        }else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 5){
        	dia = "J";
        }else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 6){
        	dia = "V";
        }else if (getCalendar().get(Calendar.DAY_OF_WEEK) == 7){
        	dia = "S";
        }
		
		return dia;
	}
	
	public String FechaHoy () {
		int dia = 0;
		int mes = 0;
		int anio = 0;
		String fecha = null;
		
		dia = getCalendar().get(Calendar.DAY_OF_MONTH);
		mes = getCalendar().get(Calendar.MONTH ) + 1;
		anio = getCalendar().get(Calendar.YEAR);
		fecha = ""+anio+"/"+mes+"/"+dia;
		return fecha;
	}
	
	public String Hora(){
		String hora = null;
		String minuto = null;
		String segundo = null;
		
		String horaFinal = null;
		
		hora = "" + getCalendar().get(Calendar.HOUR_OF_DAY);
		minuto = "" + getCalendar().get(Calendar.MINUTE);
		
		horaFinal = hora + ":" + minuto;
		
		return horaFinal;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

}
