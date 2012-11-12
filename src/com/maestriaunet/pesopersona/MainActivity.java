package com.maestriaunet.pesopersona;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	final float MINIMO_SANO = 18.5f;
	final float MAXIMO_SANO = 25;
	final float MAXIMO_SOBREPESO = 30;
	
	public double indiceCorporal(double peso, double altura){
		return peso / Math.pow(altura, 2);
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Calculador del índice de masa corporal");
        
        NumberPicker npMts = (NumberPicker) findViewById(R.id.npMts);
        npMts.setMaxValue(2);
        npMts.setMinValue(0);
        npMts.setValue(1);
        
        NumberPicker npCms = (NumberPicker) findViewById(R.id.npCms);
        npCms.setMaxValue(99);
        npCms.setMinValue(0);
        npCms.setValue(50);
        
        NumberPicker npPeso = (NumberPicker) findViewById(R.id.npPeso);
        npPeso.setMaxValue(300);
        npPeso.setMinValue(1);
        npPeso.setValue(50);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void clicBoton(View view){
    	NumberPicker npPeso = (NumberPicker) findViewById(R.id.npPeso);
    	NumberPicker npMts = (NumberPicker) findViewById(R.id.npMts);
    	NumberPicker npCms = (NumberPicker) findViewById(R.id.npCms);
    	
    	double peso = npPeso.getValue();
    	double altura = intToDoble(npMts.getValue()) + (intToDoble(npCms.getValue()) / 100);
    	double indice = this.indiceCorporal(peso, altura);
    	
    	this.mostrarVentanaResultado(indice);
    }
    
    public void mostrarVentanaResultado(double indice){
    	StringBuilder mensaje = new StringBuilder("Su IMC es de ");
    	mensaje.append(Double.toString(Math.round(indice)));
    	mensaje.append(" y ");
    	
    	if( indice < MINIMO_SANO )
    		mensaje.append(" posees problemas de bajo peso.");
    	
    	if( indice > MINIMO_SANO && indice <= MAXIMO_SANO)
			mensaje.append(" tienes un peso normal y sano.");
    		
		if( indice > MAXIMO_SANO && indice <= MAXIMO_SOBREPESO)
			mensaje.append(" posees problemas de sobrepeso.");
		
		if( indice > MAXIMO_SOBREPESO)
			mensaje.append(" posees problemas de obesidad.");	
		    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(mensaje.toString()).setTitle("Índice de Masa Corporal");
    	AlertDialog dialog = builder.create();
    	dialog.show();
    }
    
    private double intToDoble(int value){
    	return Double.parseDouble(Integer.toString(value));
    }
}
