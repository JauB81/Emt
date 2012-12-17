/********************************************** 
 * CONFIDENTIAL AND PROPRIETARY 
 *
 * The source code and other information contained herein is the confidential and the exclusive property of
 * ZIH Corp. and is subject to the terms and conditions in your end user license agreement.
 * This source code, and any other information contained herein, shall not be copied, reproduced, published, 
 * displayed or distributed, in whole or in part, in any medium, by any means, for any purpose except as
 * expressly permitted under such license agreement.
 * 
 * Copyright ZIH Corp. 2010
 *
 * ALL RIGHTS RESERVED 
 ***********************************************/
package com.zebra.android.devdemo.connectivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.researchmobile.emt.test.R;
import com.zebra.android.comm.BluetoothPrinterConnection;
import com.zebra.android.comm.TcpPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.devdemo.util.DemoSleeper;
import com.zebra.android.devdemo.util.SettingsHelper;
import com.zebra.android.printer.PrinterLanguage;
import com.zebra.android.printer.ZebraPrinter;
import com.zebra.android.printer.ZebraPrinterFactory;
import com.zebra.android.printer.ZebraPrinterLanguageUnknownException;

public class ConnectivityDemo extends Activity {

    private ZebraPrinterConnection zebraPrinterConnection;
    private RadioButton btRadioButton;
    private ZebraPrinter printer;
    private TextView statusField;
    private EditText macAddress, ipDNSAddress, portNumber;
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_screen_with_status);

        ipDNSAddress = (EditText) this.findViewById(R.id.ipAddressInput);
        ipDNSAddress.setText(SettingsHelper.getIp(this));

        portNumber = (EditText) this.findViewById(R.id.portInput);
        portNumber.setText(SettingsHelper.getPort(this));

        macAddress = (EditText) this.findViewById(R.id.macInput);
        macAddress.setText("00:22:58:01:8e:5f");

        statusField = (TextView) this.findViewById(R.id.statusText);
        btRadioButton = (RadioButton) this.findViewById(R.id.bluetoothRadio);

        testButton = (Button) this.findViewById(R.id.testButton);
        testButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        enableTestButton(false);
                        Looper.prepare();
                        doConnectionTest();
                        Looper.loop();
                        Looper.myLooper().quit();
                    }
                }).start();
            }
        });

        RadioGroup radioGroup = (RadioGroup) this.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.bluetoothRadio) {
                    toggleEditField(macAddress, true);
                    toggleEditField(portNumber, false);
                    toggleEditField(ipDNSAddress, false);
                } else {
                    toggleEditField(portNumber, true);
                    toggleEditField(ipDNSAddress, true);
                    toggleEditField(macAddress, false);
                }
            }
        });
    }

    private void toggleEditField(EditText editText, boolean set) {
        /*
         * Note: Disabled EditText fields may still get focus by some other means, and allow text input.
         *       See http://code.google.com/p/android/issues/detail?id=2771
         */
        editText.setEnabled(set);
        editText.setFocusable(set);
        editText.setFocusableInTouchMode(set);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (zebraPrinterConnection != null && zebraPrinterConnection.isConnected()) {
            disconnect();
        }
    }

    private void enableTestButton(final boolean enabled) {
        runOnUiThread(new Runnable() {
            public void run() {
                testButton.setEnabled(enabled);
            }
        });
    }

    private boolean isBluetoothSelected() {
        return btRadioButton.isChecked();
    }

    public ZebraPrinter connect() {
        setStatus("Connecting...", Color.YELLOW);
        zebraPrinterConnection = null;
        if (isBluetoothSelected()) {
            zebraPrinterConnection = new BluetoothPrinterConnection(getMacAddressFieldText());
            SettingsHelper.saveBluetoothAddress(this, getMacAddressFieldText());
        } else {
            try {
                int port = Integer.parseInt(getTcpPortNumber());
                zebraPrinterConnection = new TcpPrinterConnection(getTcpAddress(), port);
                SettingsHelper.saveIp(this, getTcpAddress());
                SettingsHelper.savePort(this, getTcpPortNumber());
            } catch (NumberFormatException e) {
                setStatus("Port Number Is Invalid", Color.RED);
                return null;
            }
        }

        try {
            zebraPrinterConnection.open();
            setStatus("Connected", Color.GREEN);
        } catch (ZebraPrinterConnectionException e) {
            setStatus("Comm Error! Disconnecting", Color.RED);
            DemoSleeper.sleep(1000);
            disconnect();
        }

        ZebraPrinter printer = null;

        if (zebraPrinterConnection.isConnected()) {
            try {
                printer = ZebraPrinterFactory.getInstance(zebraPrinterConnection);
                setStatus("Determining Printer Language", Color.YELLOW);
                PrinterLanguage pl = printer.getPrinterControlLanguage();
                setStatus("Printer Language " + pl, Color.BLUE);
            } catch (ZebraPrinterConnectionException e) {
                setStatus("Unknown Printer Language", Color.RED);
                printer = null;
                DemoSleeper.sleep(1000);
                disconnect();
            } catch (ZebraPrinterLanguageUnknownException e) {
                setStatus("Unknown Printer Language", Color.RED);
                printer = null;
                DemoSleeper.sleep(1000);
                disconnect();
            }
        }

        return printer;
    }

    public void disconnect() {
        try {
            setStatus("Disconnecting", Color.RED);
            if (zebraPrinterConnection != null) {
                zebraPrinterConnection.close();
            }
            setStatus("Not Connected", Color.RED);
        } catch (ZebraPrinterConnectionException e) {
            setStatus("COMM Error! Disconnected", Color.RED);
        } finally {
            enableTestButton(true);
        }
    }

    private void setStatus(final String statusMessage, final int color) {
        runOnUiThread(new Runnable() {
            public void run() {
                statusField.setBackgroundColor(color);
                statusField.setText(statusMessage);
            }
        });
        DemoSleeper.sleep(1000);
    }

    private String getMacAddressFieldText() {
        return macAddress.getText().toString();
    }

    private String getTcpAddress() {
        return ipDNSAddress.getText().toString();
    }

    private String getTcpPortNumber() {
        return portNumber.getText().toString();
    }

    private void doConnectionTest() {
        printer = connect();
        if (printer != null) {
            sendTestLabel();
        } else {
            disconnect();
        }
    }

    private void sendTestLabel() {
        try {
            byte[] configLabel = getConfigLabel();
            zebraPrinterConnection.write(configLabel);
            setStatus("Sending Data", Color.BLUE);
            DemoSleeper.sleep(1500);
            if (zebraPrinterConnection instanceof BluetoothPrinterConnection) {
                String friendlyName = ((BluetoothPrinterConnection) zebraPrinterConnection).getFriendlyName();
                setStatus(friendlyName, Color.MAGENTA);
                DemoSleeper.sleep(500);
            }
        } catch (ZebraPrinterConnectionException e) {
            setStatus(e.getMessage(), Color.RED);
        } finally {
            disconnect();
        }
    }

    /*
    * Returns the command for a test label depending on the printer control language
    * The test label is a box with the word "TEST" inside of it
    * 
    * _________________________
    * |                       |
    * |                       |
    * |        TEST           |
    * |                       |
    * |                       |
    * |_______________________|
    * 
    * 
    */
    private byte[] getConfigLabel() {
        PrinterLanguage printerLanguage = printer.getPrinterControlLanguage();

        byte[] configLabel = null;
        if (printerLanguage == PrinterLanguage.ZPL) {
            configLabel = "^XA^FO17,16^GB379,371,8^FS^FT65,255^A0N,135,134^FDTEST^FS^XZ".getBytes();
        } else if (printerLanguage == PrinterLanguage.CPCL) {
            String x1 = "10";
            String x2 = "130" ;
            String x3 = "250" ;
            String x4 = "370" ;
            String cpclConfigLabel = "! 0 200 200 850 1\r\n" + "ON-FEED IGNORE\r\n" + "T 0 3 10 10 BOLETA DE AVISO, REQUERIMIENTO DE\r\n" + "T 0 3 200 40 PAGO Y CITACION\r\n"
                    + "T 0 3 10 70 DATOS DEL VEHICULO\r\n"
                    + "T 0 2 " + x1 + " 100 Tarjeta:\r\n"
                    + "T 0 2 " + x2 + " 100 Placa:\r\n"
                    + "T 0 2 " + x3 + " 100 Tipo:\r\n"
                    + "T 0 2 " + x4 + " 100 Marca:\r\n"
                    + "T 0 2 " + x1 + " 125 AND876\r\n"
                    + "T 0 2 " + x2 + " 125 AS999P\r\n"
                    + "T 0 2 " + x3 + " 125 Urbano\r\n"
                    + "T 0 2 " + x4 + " 125 Toyota\r\n"
                    + "T 0 2 " + x1 + " 160 Empresa:\r\n"
                    + "T 0 2 " + x2 + " 160 Registro:\r\n"
                    + "T 0 2 " + x3 + " 160 Sector:\r\n"
                    + "T 0 2 " + x4 + " 160 Ruta:\r\n"
                    + "T 0 2 " + x1 + " 185 El Quetzal\r\n"
                    + "T 0 2 " + x2 + " 185 110021\r\n"
                    + "T 0 2 " + x3 + " 185 Sector 3\r\n"
                    + "T 0 2 " + x4 + " 185 Ruta 3\r\n"
                    + "T 0 3 " + x1 + " 220 DATOS DEL INFRACTOR\r\n"
                    + "T 0 2 " + x1 + " 250 Nombre:\r\n"
                    + "T 0 2 " + x3 + " 250 Julio Cesar\r\n"
                    + "T 0 2 " + x1 + " 280 Apellido:\r\n"
                    + "T 0 2 " + x3 + " 280 Chan Pelaez\r\n"
                    + "T 0 2 " + x1 + " 310 Direccion Domiciliar\r\n"
                    + "T 0 2 " + x3 + " 310 Guatemala\r\n"
                    + "T 0 3 " + x1 + " 340 LICENCIA\r\n"
                    + "T 0 2 " + x1 + " 370 Clasi:\r\n"
                    + "T 0 2 " + x2 + " 370 Clase C\r\n"
                    + "T 0 2 " + x3 + " 370 Registro:\r\n"
                    + "T 0 2 " + x4 + " 370 6543345\r\n"
                    + "T 0 3 " + x1 + " 400 DATOS DE LA INFRACCION\r\n"
                    + "T 0 2 " + x1 + " 430 Dia\r\n"
                    + "T 0 2 " + x2 + " 430 Mes\r\n"
                    + "T 0 2 " + x3 + " 430 Anio\r\n"
                    + "T 0 2 " + x4 + " 430 Hora\r\n"
                    + "T 0 2 " + x1 + " 460 07\r\n"
                    + "T 0 2 " + x2 + " 460 12\r\n"
                    + "T 0 2 " + x3 + " 460 2012\r\n"
                    + "T 0 2 " + x4 + " 460 14:32\r\n"
                    + "T 0 2 " + x1 + " 490 Lugar: Cerca de la calle\r\n"
                    + "T 0 3 " + x1 + " 530 INFRACCION AL REGLAMENTO DE \r\n"
                    + "T 0 3 " + x1 + " 560 TRANSPORTE PUBLICO COLECTIVO \r\n"
                    + "T 0 3 " + x1 + " 590 URBANO COMETIDA POR\r\n"
                    + "T 0 2 " + x1 + " 620 Descripcion: \r\n"
                    + "T 0 2 " + x1 + " 650 Descripcion del hecho\r\n"
                    + "T 0 2 " + x1 + " 680 Articulo: \r\n"
                    + "T 0 2 " + x2 + " 680 Inciso: \r\n"
                    + "T 0 2 " + x4 + " 680 Valor: \r\n"
                    + "T 0 2 " + x1 + " 710 72\r\n"
                    + "T 0 2 " + x2 + " 710 72.1.1\r\n"
                    + "T 0 3 " + x4 + " 710 Q. 500.00\r\n"
                    + "PRINT\r\n" ;

            configLabel = cpclConfigLabel.getBytes();

            // String cpclConfigLabel = "! 0 200 200 406 1\r\n" + "ON-FEED IGNORE\r\n" + "BOX 20 20 380 380 8\r\n" +
            // "T 0 6 137 177 TEST\r\n" + "PRINT\r\n";
            configLabel = cpclConfigLabel.getBytes();
        }
        return configLabel;
    }
}
