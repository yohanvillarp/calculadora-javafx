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

    private double valorAnterior=0, valorActual=0;
    private String operadorBinario="", operadorUnario="";
    private boolean hayOperacionBinaria, banderaOperacionBinaria, signoBinarioActivo;
    private boolean hayOperacionUnaria;


    @FXML
    public void initialize(){
        btnPorcentaje.setText("%");
    }

    //CE
    @FXML
    public void clearEntry(){
        pantalla.setText("0");
    }

    //C
    @FXML
    public void clear(){
        pantalla.setText("0");
        lblHistorial.setText("");
        valorActual = 0;
        valorAnterior = 0;
        operadorBinario = "";
        operadorUnario = "";
        hayOperacionBinaria = false;
        banderaOperacionBinaria =false;
        signoBinarioActivo =false;
    }

    // Al precionar un signo
    @FXML
    private void definirOperacion(ActionEvent e){

        Button btn = (Button) e.getSource();
        if(Operacion.isOperadorBinario(btn.getText())){
            operadorUnario = btn.getText();


            if(valorAnterior!=0) calcularResultado();
            else clear();
        }else{
            //si se ingreso un signo antes
            if(signoBinarioActivo) lblHistorial.setText(lblHistorial.getText().substring(0,lblHistorial.getText().length()-1));
            //si se ingreso un número antes
            else calcularResultado();
            operadorBinario = btn.getText();
            signoBinarioActivo = true;
            hayOperacionBinaria = true;
            banderaOperacionBinaria = true;
            lblHistorial.setText(lblHistorial.getText()+ operadorBinario);
        }
    }

    @FXML
    private void calcularResultado(){
        valorActual = Double.parseDouble(pantalla.getText());
        if(operadorUnario.isEmpty()){
            if(hayOperacionBinaria){
                Operacion operacion = new Operacion(valorAnterior, operadorBinario, valorActual);
                pantalla.setText(""+operacion.calcular());
            }
            hayOperacionBinaria = false;

        }else{
            if(!lblHistorial.getText().isEmpty()){
                int nroValorActual = (int) valorActual;
                String textDigitosValorActual = ""+nroValorActual;
                int nroDigitosValorActual = textDigitosValorActual.length();
                lblHistorial.setText(lblHistorial.getText().substring(0,lblHistorial.getText().length()-nroDigitosValorActual));
            }
            Operacion operacion = new Operacion(valorAnterior, operadorUnario, valorActual);
            System.out.println(operacion.operadorPorcentaje(operadorBinario));

                pantalla.setText(""+operacion.operadorPorcentaje(operadorBinario));
                lblHistorial.setText(lblHistorial.getText() + operacion.operadorPorcentaje(operadorBinario));
                operadorUnario = "";


        }
    }

    @FXML
    private void digitar(ActionEvent e){
        if(banderaOperacionBinaria){
            valorAnterior= Double.parseDouble(pantalla.getText());
            pantalla.setText("0");
            banderaOperacionBinaria =false;
        }
        Button btn = (Button) e.getSource();
        String digito = btn.getText();
        String txtPantalla = pantalla.getText();
        if(digito.equals(".")){
            if(puedeAgregarPunto())
                pantalla.setText(txtPantalla+".");
        }
        else {
            if(esCeroInicial())
                pantalla.setText(digito);
            else
                pantalla.setText(txtPantalla + digito);
        }
        signoBinarioActivo = false;
        lblHistorial.setText(lblHistorial.getText()+digito);
    }

    @FXML
    private void borrarUltimoDigito(){
        if(Integer.parseInt(pantalla.getText()) != 0){
            pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length()-1));
            if(pantalla.getText().isEmpty())
                pantalla.setText("0");
        }
    }

    private boolean puedeAgregarPunto(){
        return !pantalla.getText().contains(".");
    }
    private boolean esCeroInicial(){
        return pantalla.getText().equals("0");
    }
}
