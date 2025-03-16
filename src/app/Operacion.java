package app;

public class Operacion {

    private Double num1,num2;
    private String signo;

    public Operacion(Double num1,String signo, Double num2){
        this.num1 = num1;
        this.signo = signo;
        this.num2 = num2;
    }

    public Double calcular(){

        if(signo.equals("+"))
            return num1+num2;
        else if(signo.equals("-"))
            return num1-num2;
        else if(signo.equals("x"))
            return num1*num2;

        return num1/num2;

    }

    public Double operadorPorcentaje(String signoBinario){
        System.out.println(signoBinario+" "+ num1 + " " + num2);
        if(signoBinario.isEmpty()) return 0.0;
        else if(signoBinario.equals("+") || signoBinario.equals("-")) return num2/100*num1;
        else return num2/100;
    }

    public static boolean isOperadorBinario(String signo){
        if(signo.equals("%"))  return true;
        return false;
    }

}
