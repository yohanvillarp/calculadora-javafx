package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class Controlador {
    @FXML
    private TextField pantalla;

    @FXML
    private Label lblHistorial;

    @FXML
    private Button btnPorcentaje;

    private double valorAnterior, valorActual, ultimoResultado;
    private String operadorBinario, operadorUnario;
    private boolean hayOperacionBinaria, banderaOperacionBinaria,signoBinarioActivo;
    private boolean hayOperacionUnaria;

    //constantes
    private static final String CERO = "0";


    @FXML
    public void initialize(){
        btnPorcentaje.setText("%");
        valorAnterior=0;
        valorActual=0;
        operadorBinario="";
        operadorUnario="";
    }

    //CE
    @FXML
    public void clearEntry(){
        pantalla.setText(CERO);
    }

    //C
    @FXML
    public void clear(){
        clearEntry();
        valorActual = 0;
        valorAnterior = 0;
        operadorBinario = "";
        operadorUnario = "";
        hayOperacionBinaria = false;
        banderaOperacionBinaria =false;
        signoBinarioActivo =false;
    }


    private String obtenerTextoDelBoton(ActionEvent e){
        return ((Button) e.getSource()).getText();
    }


    // Al precionar un signo
    @FXML
    private void definirOperacion(ActionEvent e){
        String operador = obtenerTextoDelBoton(e);

        //operador unario
        if(Operacion.isOperadorUnario(operador)){
            operadorUnario = operador;
            if(valorAnterior!=0) calcularResultado();
            else clear();
        }else {
            calcularResultado();
            operadorBinario = operador;
            signoBinarioActivo = true;
            hayOperacionBinaria = true;
            banderaOperacionBinaria = true;
        }
    }

    @FXML
    private void digitar(ActionEvent e) {
        if (banderaOperacionBinaria) {
            valorAnterior = Double.parseDouble(pantalla.getText());
            pantalla.setText(CERO);
            banderaOperacionBinaria = false;
        }
        String digito = obtenerTextoDelBoton(e);
        String txtPantalla = pantalla.getText();
        if (digito.equals(".")) {
            if (puedeAgregarPunto())
                pantalla.setText(txtPantalla + ".");
        } else {
            if (esCeroInicial()) pantalla.setText(digito);
            else pantalla.setText(txtPantalla + digito);
        }
        signoBinarioActivo = false;
    }

    @FXML
    private void calcularResultado(){
        valorActual = Double.parseDouble(pantalla.getText());

        //en caso de operador binario
        if(operadorUnario.isEmpty()){
            if(hayOperacionBinaria){
                Operacion operacion = new Operacion(valorAnterior, operadorBinario, valorActual);
                ultimoResultado = Double.parseDouble(operacion.calcular());
                valorAnterior = ultimoResultado;
                valorActual = 0;
                pantalla.setText(operacion.calcular());
            }
            hayOperacionBinaria = false;
        }
        //en caso de operador unario
        else {
            Operacion operacion = new Operacion(valorAnterior, operadorUnario, valorActual);
            System.out.println(operacion.operadorPorcentaje(operadorBinario));

            pantalla.setText("" + operacion.operadorPorcentaje(operadorBinario));
            operadorUnario = "";
        }
    }

    @FXML
    private void borrarUltimoDigito(){
        if(Double.parseDouble(pantalla.getText()) != 0){
            pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length()-1));
            if(pantalla.getText().isEmpty())
                pantalla.setText(CERO);
        }
    }

    private boolean puedeAgregarPunto(){
        return !pantalla.getText().contains(".");
    }
    private boolean esCeroInicial(){
        return pantalla.getText().equals(CERO);
    }
}
