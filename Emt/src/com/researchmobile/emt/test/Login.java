package com.researchmobile.emt.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener, OnKeyListener{
	private EditText usuarioEditText;
	private EditText claveEditText;
	private Button entrarButton;
	private Button salirButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        setUsuarioEditText((EditText)findViewById(R.id.login_usuario_edittext));
        setClaveEditText((EditText)findViewById(R.id.login_clave_edittext));
        getUsuarioEditText().setOnKeyListener(this);
        getClaveEditText().setOnKeyListener(this);
        setEntrarButton((Button)findViewById(R.id.login_entrar_button));
        setSalirButton((Button)findViewById(R.id.login_salir_button));
        
        getEntrarButton().setOnClickListener(this);
        getSalirButton().setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if (view == getEntrarButton()){
			Entrar();
		}
		
	}
	
	 @Override
     public boolean onKey(View v, int keyCode, KeyEvent event)
     {
         if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
         {
        	 if (v == getUsuarioEditText()){
        		 getClaveEditText().requestFocus();
        	 }else if (v == getClaveEditText()){
        		 Entrar();
        	 }
         }
         if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
         {
             //TODO: When the enter key is pressed
             return true;
         }
         return false;
     }

	private void Entrar() {
		if (VerificaCampos()){
			Intent intent = new Intent(Login.this, Emt.class);
			startActivity(intent);
		}else{
			MensajeErrorCaposVacios();
		}
		
	}

	private void MensajeErrorCaposVacios() {
		Toast.makeText(getBaseContext(), "Debe llenar los campos requeridos", Toast.LENGTH_SHORT).show();
		
	}

	private boolean VerificaCampos() {
		if (getUsuarioEditText().getText().toString().equalsIgnoreCase("") || getClaveEditText().getText().toString().equalsIgnoreCase("")){
			return false;
		}else{
			return true;
		}
		
	}

	public EditText getUsuarioEditText() {
		return usuarioEditText;
	}

	public void setUsuarioEditText(EditText usuarioEditText) {
		this.usuarioEditText = usuarioEditText;
	}

	public EditText getClaveEditText() {
		return claveEditText;
	}

	public void setClaveEditText(EditText claveEditText) {
		this.claveEditText = claveEditText;
	}

	public Button getEntrarButton() {
		return entrarButton;
	}

	public void setEntrarButton(Button entrarButton) {
		this.entrarButton = entrarButton;
	}

	public Button getSalirButton() {
		return salirButton;
	}

	public void setSalirButton(Button salirButton) {
		this.salirButton = salirButton;
	}

	

}
