package com.researchmobile.emt.test;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.researchmobile.emt.entity.Datos;
import com.researchmobile.emt.entity.Persona;

public class Multar extends Activity implements OnClickListener{
	
	private static final int CAMERA_RESULT = 0;
	private Spinner articuloSpinner;
	private Spinner infractorSpinner;
	private EditText direccionEditText;
	private EditText otrosEditText;
	private CheckBox ausenteCheckBox;
	private CheckBox noFirmaCheckBox;
	private ImageButton fotoImageButton;
	private Button generarButton;
	private String pathFoto;
	
	private Persona persona;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multar);
        
        Bundle bundle = getIntent().getExtras();
        setPersona((Persona)bundle.get("persona"));
        setPathFoto("");
        
        setArticuloSpinner((Spinner)findViewById(R.id.multar_articulo_spinner));
        setInfractorSpinner((Spinner)findViewById(R.id.multar_infractor_spinner));
        setDireccionEditText((EditText)findViewById(R.id.multar_direccion_edittext));
        setOtrosEditText((EditText)findViewById(R.id.multar_otros_edittext));
        setAusenteCheckBox((CheckBox)findViewById(R.id.multar_ausente_checkbox));
        setNoFirmaCheckBox((CheckBox)findViewById(R.id.multar_nofirma_checkbox));
        setFotoImageButton((ImageButton)findViewById(R.id.multar_foto_imagebutton));
        setGenerarButton((Button)findViewById(R.id.multar_boleta_button));
        
        getFotoImageButton().setOnClickListener(this);
        getGenerarButton().setOnClickListener(this);
        
        Datos datos = new Datos();
        ArrayAdapter<String> adaptadorArticulo = new ArrayAdapter<String>(this, R.layout.opciones_spinner, R.id.item_opciones_spinnet, datos.articulos());
        getArticuloSpinner().setAdapter(adaptadorArticulo);
        
        ArrayAdapter<String> adaptadorInfractor = new ArrayAdapter<String>(this, R.layout.opciones_spinner, R.id.item_opciones_spinnet, datos.infractor());
        getInfractorSpinner().setAdapter(adaptadorInfractor);
    }

	@Override
	public void onClick(View view) {
		if (view == getFotoImageButton()){
			CapturaFoto();
		}else if (view == getGenerarButton()){
			GenerarBoleta();
		}
		
	}

	private void GenerarBoleta() {
		String articulo = getArticuloSpinner().getSelectedItem().toString();
		Datos datos = new Datos();
		String multa = datos.GeneraMulta(articulo);
		String lugar = getDireccionEditText().getText().toString();
		
		Intent intent = new Intent(Multar.this, Boleta.class);
		intent.putExtra("articulo", articulo);
		intent.putExtra("multa", multa);
		intent.putExtra("pathfoto", getPathFoto());
		intent.putExtra("lugar", lugar);
		intent.putExtra("persona", getPersona());
		startActivity(intent);
		
        //VerMulta(articulo);
		
	}

	private void VerMulta(String articulo) {
		Datos datos = new Datos();
		String multa = datos.GeneraMulta(articulo);
		new AlertDialog.Builder(this)
        .setTitle("IMPRIMIR")
        .setMessage("la multa es de " + multa + " segun el artículo " + articulo)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	Intent intent = new Intent(Multar.this, Emt.class);
                    startActivity(intent);
                }
        })
        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            	
            }
        })
        .show();
		
	}

	private void CapturaFoto() {
		//Activar la camara
	  	Intent cIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		//startActivityForResult(cIntent, CAMERA_RESULT); 
		System.out.println("activar camara");
		//asignar nombre y direccion a la imagen
		setPathFoto("/foto1-multa.jpg");
		String path = "/mifoto.jpg";
		System.out.println("crear nombre archivo " + getPathFoto());
		//crear nuevo archivo (imagen)
		File f = new File(Environment.getExternalStorageDirectory() + getPathFoto());
		Uri uri = Uri.fromFile(f);
		System.out.println("crear archivo " + f);
		cIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		
		startActivityForResult(cIntent, CAMERA_RESULT);
	}

	public Spinner getArticuloSpinner() {
		return articuloSpinner;
	}

	public void setArticuloSpinner(Spinner articuloSpinner) {
		this.articuloSpinner = articuloSpinner;
	}

	public Spinner getInfractorSpinner() {
		return infractorSpinner;
	}

	public void setInfractorSpinner(Spinner infractorSpinner) {
		this.infractorSpinner = infractorSpinner;
	}

	public EditText getDireccionEditText() {
		return direccionEditText;
	}

	public void setDireccionEditText(EditText direccionEditText) {
		this.direccionEditText = direccionEditText;
	}

	public EditText getOtrosEditText() {
		return otrosEditText;
	}

	public void setOtrosEditText(EditText otrosEditText) {
		this.otrosEditText = otrosEditText;
	}

	public CheckBox getAusenteCheckBox() {
		return ausenteCheckBox;
	}

	public void setAusenteCheckBox(CheckBox ausenteCheckBox) {
		this.ausenteCheckBox = ausenteCheckBox;
	}

	public CheckBox getNoFirmaCheckBox() {
		return noFirmaCheckBox;
	}

	public void setNoFirmaCheckBox(CheckBox noFirmaCheckBox) {
		this.noFirmaCheckBox = noFirmaCheckBox;
	}

	public ImageButton getFotoImageButton() {
		return fotoImageButton;
	}

	public void setFotoImageButton(ImageButton fotoImageButton) {
		this.fotoImageButton = fotoImageButton;
	}

	public Button getGenerarButton() {
		return generarButton;
	}

	public void setGenerarButton(Button generarButton) {
		this.generarButton = generarButton;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}
}
