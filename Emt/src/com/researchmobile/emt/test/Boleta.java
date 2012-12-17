package com.researchmobile.emt.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.researchmobile.emt.entity.Datos;
import com.researchmobile.emt.entity.Persona;
import com.researchmobile.emt.utility.MyDate;
import com.zebra.android.devdemo.connectivity.ConnectivityDemo;

public class Boleta extends Activity implements OnClickListener{
	private TextView tarjetaTextView;
	private TextView placaTextView;
	private TextView tipoTextView;
	private TextView marcaTextView;
	private TextView empresaTextView;
	private TextView registroTextView;
	private TextView sectorTextView;
	private TextView rutaTextView;
	
	private TextView nombreTextView;
	private TextView apellidoTextView;
	private TextView direccionDomiciliarTextView;
	private TextView licenciaClasificacionTextView;
	private TextView licenciaNumeroTextView;
	
	private TextView diaTextView;
	private TextView mesTextView;
	private TextView anoTextView;
	private TextView horaTextView;
	private TextView lugarTextView;
	
	private TextView articuloDescripcionTextView;
	private TextView articuloNumeroTextView;
	private TextView incisoTextView;
	private TextView valorTextView;
	
	private String pathFoto;
	private Button finalizarButton;
	private ImageView fotoImageView;
	
	private Persona persona;
	private MyDate myDate;
	private Datos datos;
	private String articulo;
	private String multa;
	private String lugar;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boleta);
        Bundle bundle = getIntent().getExtras();
        setPersona((Persona)bundle.get("persona"));
        setArticulo(bundle.getString("articulo"));
        setMulta(bundle.getString("multa"));
        setLugar(bundle.getString("lugar"));
        setPathFoto(bundle.getString("pathfoto"));
        String imagen = "sdcard" + getPathFoto();
        System.out.println(imagen);
        setMyDate(new MyDate());
        setDatos(new Datos());
        
        setFinalizarButton((Button)findViewById(R.id.boleta_finalizar_button));
        //setFotoButton((Button)findViewById(R.id.boleta_foto_button));
        getFinalizarButton().setOnClickListener(this);
        setTarjetaTextView((TextView)findViewById(R.id.boleta_tarjeta_textview));
        setPlacaTextView((TextView)findViewById(R.id.boleta_placa_textview));
        setTipoTextView((TextView)findViewById(R.id.boleta_tipo_textview));
        setMarcaTextView((TextView)findViewById(R.id.boleta_marca_textview));
        setEmpresaTextView((TextView)findViewById(R.id.boleta_empresa_textview));
        setRegistroTextView((TextView)findViewById(R.id.boleta_registro_textview));
        setSectorTextView((TextView)findViewById(R.id.boleta_sector_textview));
        setRutaTextView((TextView)findViewById(R.id.boleta_ruta_textview));
        setNombreTextView((TextView)findViewById(R.id.boleta_nombre_textview));
        setApellidoTextView((TextView)findViewById(R.id.boleta_apellidos_textview));
        setDireccionDomiciliarTextView((TextView)findViewById(R.id.boleta_direcciondomiciliar_textview));
        setLicenciaClasificacionTextView((TextView)findViewById(R.id.boleta_licenciaclasificacion_textview));
        setLicenciaNumeroTextView((TextView)findViewById(R.id.boleta_licencianumero_textview));
        setDiaTextView((TextView)findViewById(R.id.boleta_dia_textview));
        setMesTextView((TextView)findViewById(R.id.boleta_mes_textview));
        setAnoTextView((TextView)findViewById(R.id.boleta_ano_textview));
        setHoraTextView((TextView)findViewById(R.id.boleta_hora_textview));
        setLugarTextView((TextView)findViewById(R.id.boleta_direccion_textview));
        setArticuloDescripcionTextView((TextView)findViewById(R.id.boleta_descripcion_textview));
        setArticuloNumeroTextView((TextView)findViewById(R.id.boleta_articulo_textview));
        setIncisoTextView((TextView)findViewById(R.id.boleta_inciso_textview));
        setValorTextView((TextView)findViewById(R.id.boleta_valor_textview));
        
        setFotoImageView((ImageView)findViewById(R.id.boleta_foto_imageview));
        VerImagen();
        
        getTarjetaTextView().setText(getPersona().getTarjeta());
        getPlacaTextView().setText(getPersona().getPlaca());
        getTipoTextView().setText(getPersona().getTipo());
        getMarcaTextView().setText(getPersona().getMarca());
        getEmpresaTextView().setText(getPersona().getEmpresa());
        getRegistroTextView().setText(getPersona().getRegistro());
        getSectorTextView().setText(getPersona().getSector());
        getRutaTextView().setText(getPersona().getRuta());
        getNombreTextView().setText(getPersona().getNombre());
        getApellidoTextView().setText(getPersona().getApellido());
        getDireccionDomiciliarTextView().setText(getPersona().getDireccionDomiciliar());
        getLicenciaClasificacionTextView().setText(getPersona().getLicenciaClasificaion());
        getLicenciaNumeroTextView().setText(getPersona().getLicenciaNumero());
        
        getDiaTextView().setText(getMyDate().NumeroDia());
        getAnoTextView().setText(getMyDate().Ano());
        getMesTextView().setText(getMyDate().Mes());
        getHoraTextView().setText(getMyDate().Hora());
        getLugarTextView().setText(getLugar());
        getArticuloNumeroTextView().setText(getDatos().Articulo(getArticulo()));
        getArticuloDescripcionTextView().setText(getDatos().DescripcionArticulo(getArticulo()));
        getIncisoTextView().setText(getArticulo());
        getValorTextView().setText(getMulta());
	}
	
	private void VerImagen() {
		System.out.println("a");
		BitmapFactory.Options options = new BitmapFactory.Options();
		System.out.println("a");
        options.inSampleSize = 0;
        System.out.println("a");
        Bitmap bm = BitmapFactory.decodeFile("sdcard" + getPathFoto(), options);
          	  
        getFotoImageView().setImageBitmap(bm);
    }

	@Override
	public void onClick(View view) {
		if (view == getFinalizarButton()){
			Finalizar();
		}
		
	}

	private void VerFoto() {
		// TODO Auto-generated method stub
		
	}

	private void Finalizar() {
		Intent intent = new Intent(Boleta.this, ConnectivityDemo.class);
		startActivity(intent);
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

	public TextView getMarcaTextView() {
		return marcaTextView;
	}

	public void setMarcaTextView(TextView marcaTextView) {
		this.marcaTextView = marcaTextView;
	}

	public TextView getEmpresaTextView() {
		return empresaTextView;
	}

	public void setEmpresaTextView(TextView empresaTextView) {
		this.empresaTextView = empresaTextView;
	}

	public TextView getRegistroTextView() {
		return registroTextView;
	}

	public void setRegistroTextView(TextView registroTextView) {
		this.registroTextView = registroTextView;
	}

	public TextView getSectorTextView() {
		return sectorTextView;
	}

	public void setSectorTextView(TextView sectorTextView) {
		this.sectorTextView = sectorTextView;
	}

	public TextView getRutaTextView() {
		return rutaTextView;
	}

	public void setRutaTextView(TextView rutaTextView) {
		this.rutaTextView = rutaTextView;
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

	public TextView getDireccionDomiciliarTextView() {
		return direccionDomiciliarTextView;
	}

	public void setDireccionDomiciliarTextView(TextView direccionDomiciliarTextView) {
		this.direccionDomiciliarTextView = direccionDomiciliarTextView;
	}

	public TextView getLicenciaClasificacionTextView() {
		return licenciaClasificacionTextView;
	}

	public void setLicenciaClasificacionTextView(
			TextView licenciaClasificacionTextView) {
		this.licenciaClasificacionTextView = licenciaClasificacionTextView;
	}

	public TextView getLicenciaNumeroTextView() {
		return licenciaNumeroTextView;
	}

	public void setLicenciaNumeroTextView(TextView licenciaNumeroTextView) {
		this.licenciaNumeroTextView = licenciaNumeroTextView;
	}

	public TextView getDiaTextView() {
		return diaTextView;
	}

	public void setDiaTextView(TextView diaTextView) {
		this.diaTextView = diaTextView;
	}

	public TextView getMesTextView() {
		return mesTextView;
	}

	public void setMesTextView(TextView mesTextView) {
		this.mesTextView = mesTextView;
	}

	public TextView getAnoTextView() {
		return anoTextView;
	}

	public void setAnoTextView(TextView anoTextView) {
		this.anoTextView = anoTextView;
	}

	public TextView getHoraTextView() {
		return horaTextView;
	}

	public void setHoraTextView(TextView horaTextView) {
		this.horaTextView = horaTextView;
	}

	public TextView getLugarTextView() {
		return lugarTextView;
	}

	public void setLugarTextView(TextView lugarTextView) {
		this.lugarTextView = lugarTextView;
	}

	public TextView getArticuloDescripcionTextView() {
		return articuloDescripcionTextView;
	}

	public void setArticuloDescripcionTextView(TextView articuloDescripcionTextView) {
		this.articuloDescripcionTextView = articuloDescripcionTextView;
	}

	public TextView getArticuloNumeroTextView() {
		return articuloNumeroTextView;
	}

	public void setArticuloNumeroTextView(TextView articuloNumeroTextView) {
		this.articuloNumeroTextView = articuloNumeroTextView;
	}

	public TextView getIncisoTextView() {
		return incisoTextView;
	}

	public void setIncisoTextView(TextView incisoTextView) {
		this.incisoTextView = incisoTextView;
	}

	public TextView getValorTextView() {
		return valorTextView;
	}

	public void setValorTextView(TextView valorTextView) {
		this.valorTextView = valorTextView;
	}

	public MyDate getMyDate() {
		return myDate;
	}

	public void setMyDate(MyDate myDate) {
		this.myDate = myDate;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

	public Datos getDatos() {
		return datos;
	}

	public void setDatos(Datos datos) {
		this.datos = datos;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Button getFinalizarButton() {
		return finalizarButton;
	}

	public void setFinalizarButton(Button finalizarButton) {
		this.finalizarButton = finalizarButton;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public ImageView getFotoImageView() {
		return fotoImageView;
	}

	public void setFotoImageView(ImageView fotoImageView) {
		this.fotoImageView = fotoImageView;
	}
}
