package com.researchmobile.emt.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.researchmobile.emt.entity.Datos;
import com.researchmobile.emt.entity.Persona;

public class Historial extends Activity implements OnClickListener{
	private Button multarButton;
	private Button noMultarButton;
	private ListView historialListView;
	private Persona persona;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial);
        Bundle bundle = getIntent().getExtras();
        setPersona((Persona)bundle.get("persona"));
        setMultarButton((Button)findViewById(R.id.historial_multar_button));
        setNoMultarButton((Button)findViewById(R.id.historial_nomultar_button));
        setHistorialListView((ListView)findViewById(R.id.historial_listview));
        
        getMultarButton().setOnClickListener(this);
        getNoMultarButton().setOnClickListener(this);
        
        fillDataListView();
    }
	private void fillDataListView() {
		Datos datos = new Datos();
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.opciones_spinner, R.id.item_opciones_spinnet, datos.VerHistorial(getPersona()));
    	getHistorialListView().setAdapter(adaptador);
		
	}
	@Override
	public void onClick(View view) {
		if (view == getMultarButton()){
			IniciaMulta();
			//DialogoMultar();
		}else if (view == getNoMultarButton()){
			DialogNoMultar();
		}
	}
	private void IniciaMulta() {
		Intent intent = new Intent(Historial.this, Multar.class);
		intent.putExtra("persona", getPersona());
		startActivity(intent);
		
	}
	private void DialogNoMultar() {
		new AlertDialog.Builder(this)
        .setTitle("NO MULTAR")
        .setMessage("El registro se ha guardado exitosamente")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	Intent intent = new Intent(Historial.this, Emt.class);
                    startActivity(intent);
                }
        })
        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            
            }
        })
        
        .show();
    }
	private void DialogoMultar() {
		
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.dialogomultar , null);
        final Spinner articulosSpinner = (Spinner) textEntryView.findViewById(R.id.dialogomultar_articulos_spinner);
        Datos datos = new Datos();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.opciones_spinner, R.id.item_opciones_spinnet, datos.articulos());
    	articulosSpinner.setAdapter(adaptador);
        final AlertDialog.Builder alert = new AlertDialog.Builder(Historial.this );
        alert.setTitle( "MULTAR");
	    alert.setView(textEntryView);
	    alert.setPositiveButton("GENERAR BOLETA",
	                    new DialogInterface.OnClickListener() {
	                          @Override
	                          public void onClick(DialogInterface arg0, int arg1) {
	                               String articulo = articulosSpinner.getSelectedItem().toString();
	                               VerMulta(articulo);
	                         }
	                   });
	       alert.show();
	}
	
	protected void VerMulta(String articulo) {
		Datos datos = new Datos();
		String multa = datos.GeneraMulta(articulo);
		new AlertDialog.Builder(this)
        .setTitle("IMPRIMIR")
        .setMessage("la multa es de " + multa + " segun el artículo " + articulo)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	Intent intent = new Intent(Historial.this, Emt.class);
                    startActivity(intent);
                }
        })
        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            	
            }
        })
        .show();
    }
	public Button getMultarButton() {
		return multarButton;
	}
	public void setMultarButton(Button multarButton) {
		this.multarButton = multarButton;
	}
	public Button getNoMultarButton() {
		return noMultarButton;
	}
	public void setNoMultarButton(Button noMultarButton) {
		this.noMultarButton = noMultarButton;
	}
	public ListView getHistorialListView() {
		return historialListView;
	}
	public void setHistorialListView(ListView historialListView) {
		this.historialListView = historialListView;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
}
