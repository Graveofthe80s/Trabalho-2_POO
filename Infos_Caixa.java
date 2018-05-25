import java.util.Scanner;

public abstract class Infos_Caixa{
    //Informa quantidade e valores das cédulas.
    public void ced_100(int ced_100, double valor){
        if(ced_100>0) {
            System.out.println(ced_100 + " cédulas de R$" + valor);
        }
    }
    public void ced_50(int ced_50, double valor){
        if(ced_50>0) {
            System.out.println(ced_50 + " cédulas de R$" + valor);
        }
    }
    public void ced_20(int ced_20, double valor){
        if(ced_20>0) {
            System.out.println(ced_20 + " cédulas de R$" + valor);
        }
    }
    public void ced_10(int ced_10, double valor){
        if(ced_10>0) {
            System.out.println(ced_10 + " cédulas de R$" + valor);
        }
    }

    public void codeNeg(){
        System.out.print("Código inválido.");
    }

    public void valorSacado(double valorSacado){
        System.out.println("Saque: R$"+valorSacado);
    }

    Scanner in = new Scanner(System.in);
    public Double init_Saque(){
        System.out.print("Valor do saque: R$");
        double valorDeSaque = in.nextDouble();
        return valorDeSaque;
    }
    public void saqueNeg(double a, double s, double d, double w){
        System.out.println("Saque não autorizado.\nCédulas disponíveis: (R$"+a+"), (R$"+s+"), (R$"+d+"), (R$"+w+")");
    }
    public void saqueInv(){
        System.out.println("Saque inválido.");
    }
    public String repetirSaque(){
        System.out.print("Deseja realizar outro saque? [S/N] ");
        String resp = in.next().toUpperCase();
        return resp;
    }
    public void saqueEncerrado(){
        System.out.println("Saque finalizado.");
    }
    public void saqueFinalizado(){
        System.out.println("Saque realizado com sucesso!");
    }
    public void caixaVazio(){
        System.out.println("Saldo caixa insuficiente.");
    }
    public void infoCaixa(double info){
        System.out.println("Saldo Caixa disponível: R$"+info);
    }
    public void show(int row, double x){
        System.out.println("Saldo Bandeja["+row+"]: "+x);
    }


}
