package com.researchmobile.emt.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.researchmobile.emt.entity.Datos;
import com.researchmobile.emt.entity.Persona;

public class Emt extends Activity implements OnClickListener{
	private Button buscarButton;
	private Button historialButton;
	private Spinner opcionSpinner;
	private EditText buscarEditText;
	private LinearLayout resultadoLinearLayout;
	
	private TextView tarjetaTextView;
	private TextView placaTextView;
	private TextView tipoTextView;
	private TextView nombreTextView;
	private TextView apellidoTextView;
	private TextView direccionTextView;
	private int opcion;
	private int busqueda;
	private Persona persona;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        setBusqueda(0);
        setBuscarButton((Button)findViewById(R.id.principal_buscar_button));
        setHistorialButton((Button)findViewById(R.id.principal_historial_button));
        setBuscarEditText((EditText)findViewById(R.id.principal_buscar_edittext));
        setOpcionSpinner((Spinner)findViewById(R.id.principal_opcion_spinner));
        setResultadoLinearLayout((LinearLayout)findViewById(R.id.principal_resultado_layout));
        getResultadoLinearLayout().setVisibility(View.INVISIBLE);
        getBuscarButton().setOnClickListener(this);
        getBuscarEditText().setOnClickListener(this);
        getHistorialButton().setOnClickListener(this);
        
        setTarjetaTextView((TextView)findViewById(R.id.principal_tarjeta_textview));
        setPlacaTextView((TextView)findViewById(R.id.principal_placa_textview));
        setTipoTextView((TextView)findViewById(R.id.principal_tipo_textview));
        setNombreTextView((TextView)findViewById(R.id.principal_nombre_textview));
        setApellidoTextView((TextView)findViewById(R.id.principal_apellido_textview));
        setDireccionTextView((TextView)findViewById(R.id.principal_direccion_textview));
        fillDataSpinner();
        
    }
    
   	@Override
	public void onClick(View view) {
		if (view == getBuscarButton()){
			VerificarCampos();
		}else if (view == getBuscarEditText()){
			if (getOpcion() == 1){
				CapturarUsuario();
			}else{
				
			}
		}else if (view == getHistorialButton()){
			if (getBusqueda() == 0){
				Toast.makeText(getBaseContext(), "Debe realizar una busqueda", Toast.LENGTH_SHORT).show();
			}else{
				VerHistorial();
			}
		}
		
	}
   	
   	private void VerHistorial() {
		Intent intent = new Intent(Emt.this, Historial.class);
		intent.putExtra("persona", getPersona());
		startActivity(intent);
		
	}

	private void VerificarCampos() {
		if (getBuscarEditText().getText().toString().equalsIgnoreCase("")){
			Toast.makeText(getBaseContext(), "Debe llenar el campo", Toast.LENGTH_SHORT).show();
		}else{
			String codigo = getBuscarEditText().getText().toString();
			getBuscarEditText().setText("");
			Datos datos = new Datos();
			setPersona(datos.BuscarDato(getOpcion(), codigo));
			if (getPersona() == null){
				Toast.makeText(getBaseContext(), "No existe", Toast.LENGTH_SHORT).show();
			}else{
				llenaDatos(getPersona());
				setBusqueda(1);
				getResultadoLinearLayout().setVisibility(View.VISIBLE);
			}
		}
	}

	private void llenaDatos(Persona persona) {
		getTarjetaTextView().setText(persona.getTarjeta());
		getPlacaTextView().setText(persona.getPlaca());
		getTipoTextView().setText(persona.getTipo());
		getNombreTextView().setText(persona.getNombre());
		getApellidoTextView().setText(persona.getApellido());
		getDireccionTextView().setText(persona.getDireccion());
	}

	public void CapturarUsuario(){
		//setControlScan(0);
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		  IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null) {
			String barcode;
			barcode = scanResult.getContents();
			getBuscarEditText().setText(barcode);
		}
	}
   	
    private void fillDataSpinner() {
    	ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.opciones_spinner, R.id.item_opciones_spinnet, Opciones());
    	getOpcionSpinner().setAdapter(adaptador);
    	
    	getOpcionSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id){
            	String item = parent.getItemAtPosition(position).toString();
            	VerificaItem(item);
            }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
        });
    }
    
    protected void VerificaItem(String item) {
		if (item.equalsIgnoreCase("Código de Barras")){
			setOpcion(1);
		}else if (item.equalsIgnoreCase("Código de Carnet")){
			setOpcion(2);
		}else if (item.equalsIgnoreCase("Código de Piloto")){
			setOpcion(3);
		}else if (item.equalsIgnoreCase("Siglas del Bus")){
			setOpcion(4);
		} 
		
	}
    
    /**
     * MENU
     */
   
    // To change item text dynamically
      public boolean onPrepareOptionsMenu(Menu menu) {            
            return true;
      }
  
    public  boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_emt, menu);               
       
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
       case R.id.menu_emt_salir:        
    	   moveTaskToBack(true);
        
        return true;
       default:
        return super.onOptionsItemSelected(item);
       }
    }

    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
              // Preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR     
              return true;
        }
        
        return super.onKeyDown(keyCode, event);
      }


	public String[] Opciones(){
    	String[] datos = new String[]{"Código de Barras","Código de Carnet","Código de Piloto","Siglas del Bus"};
    	return datos;
    }
	public Button getBuscarButton() {
		return buscarButton;
	}
	public void setBuscarButton(Button buscarButton) {
		this.buscarButton = buscarButton;
	}
	public Button getHistorialButton() {
		return historialButton;
	}
	public void setHistorialButton(Button historialButton) {
		this.historialButton = historialButton;
	}
	public Spinner getOpcionSpinner() {
		return opcionSpinner;
	}
	public void setOpcionSpinner(Spinner opcionSpinner) {
		this.opcionSpinner = opcionSpinner;
	}
	public EditText getBuscarEditText() {
		return buscarEditText;
	}
	public void setBuscarEditText(EditText buscarEditText) {
		this.buscarEditText = buscarEditText;
	}

	public LinearLayout getResultadoLinearLayout() {
		return resultadoLinearLayout;
	}

	public void setResultadoLinearLayout(LinearLayout resultadoLinearLayout) {
		this.resultadoLinearLayout = resultadoLinearLayout;
	}

	public TextView getTarjetaTextView() {
		return tarjetaTextView;
	}

	public void setTarjetaTextView(TextView tarjetaTextView) {
		this.tarjetaTextView = tarjetaTextView;
	}

	public TextView getPlacaTextView() {
		return placaTextView;
	}

	public void setPlacaTextView(TextView placaTextView) {
		this.placaTextView = placaTextView;
	}

	public TextView getTipoTextView() {
		return tipoTextView;
	}

	public void setTipoTextView(TextView tipoTextView) {
		this.tipoTextView = tipoTextView;
	}

	public TextView getNombreTextView() {
		return nombreTextView;
	}

	public void setNombreTextView(TextView nombreTextView) {
		this.nombreTextView = nombreTextView;
	}

	public TextView getApellidoTextView() {
		return apellidoTextView;
	}

	public void setApellidoTextView(TextView apellidoTextView) {
		this.apellidoTextView = apellidoTextView;
	}

	public TextView getDireccionTextView() {
		return direccionTextView;
	}

	public void setDireccionTextView(TextView direccionTextView) {
		this.direccionTextView = direccionTextView;
	}

	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	public int getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(int busqueda) {
		this.busqueda = busqueda;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}