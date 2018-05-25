import java.util.regex.*;
public class CaixaEletronico extends Infos_Caixa {

    private String codigo;
    private String logradouro;
    private int numero;
    private String cidade;
    private String estado;
    public int b1=1, b2=2,b3=3,b4=4;
    protected Bandeja bandeja1 = new Bandeja(100,50);
    protected Bandeja bandeja2 = new Bandeja(50, 100);
    protected Bandeja bandeja3 = new Bandeja(20,150);
    protected Bandeja bandeja4 = new Bandeja(10,100);

    public CaixaEletronico(String codigo, String logradouro, int numero, String cidade, String estado){

        if(codigo.length() == 5) {
            if (codeChecker("[A-Za-z]{2,2}+[0-9]{3,3}", codigo)) {
                this.codigo = codigo;
            } else {
                codeNeg();
            }
        }else{
            codeNeg();
        }
        this.logradouro=logradouro;
        this.numero=numero;
        this.cidade=cidade;
        this.estado=estado;
    }

    public String getCodigo(){return this.codigo;}
    public String getLogradouro(){return this.logradouro;}
    public int getNumero(){return this.numero;}
    public String getCidade(){return this.cidade;}
    public String getEstado(){return this.estado;}

    double saldoCaixa;
    public double saldoCaixa(){
        //Saldo total de todas as bandejas.
        saldoCaixa = (bandeja1.valorDeFace * bandeja1.quantidadeCelula)+
                (bandeja2.valorDeFace * bandeja2.quantidadeCelula)+
                (bandeja3.valorDeFace * bandeja3.quantidadeCelula)+
                (bandeja4.valorDeFace * bandeja4.quantidadeCelula);
        return saldoCaixa;
    }

    public double saldoBandejaX(int bx){
        double saldoBandeja = 0;

        //Saldo total de cada bandeja.
        switch (bx) {
            case 1:
                saldoBandeja = bandeja1.saldoBandeja();
                if(erlaubtx) {
                    saldoBandeja -= (bandeja1.cedulaX * bandeja1.valorDeFace);
                }
                break;
            case 2:
                saldoBandeja = bandeja2.saldoBandeja();
                if(erlaubtx) {
                    saldoBandeja -= (bandeja2.cedulaX * bandeja2.valorDeFace);
                }
                break;
            case 3:
                saldoBandeja = bandeja3.saldoBandeja();
                if(erlaubtx) {
                    saldoBandeja -= (bandeja3.cedulaX * bandeja3.valorDeFace);
                }
                break;
            case 4:
                saldoBandeja = bandeja4.saldoBandeja();
                if(erlaubtx) {
                    saldoBandeja -= (bandeja4.cedulaX * bandeja4.valorDeFace);
                }
                break;
            default:
                break;
        }
        return saldoBandeja;
    }

    public static boolean codeChecker(String theRegEx, String code2Check) {
        boolean check=false;
        Pattern codeCheck = Pattern.compile(theRegEx);
        Matcher codeMatcher = codeCheck.matcher(code2Check);
        while (codeMatcher.find()) {
            if (codeMatcher.group().length() != 0) {
                check = true;
            }else{
                check=false;
            }
        }
        return check;
    }

    private boolean erlaubt=true;
    private boolean erlaubtx = true;
    private double sobra;
    private double saldoB1 = bandeja1.saldoBandeja();
    private double saldoB2 = bandeja2.saldoBandeja();
    private double saldoB3 = bandeja3.saldoBandeja();
    private double saldoB4 = bandeja4.saldoBandeja();


    public double sacar(double valorSaque) {
        double saldoCaixa = saldoCaixa();
        valorSaque = init_Saque();
        double valorSacado = valorSaque;
        //Possível sacar.
        if(saldoCaixa>0) {
            if (valorSaque <= saldoCaixa) {
                saldoCaixa-=valorSacado;

                erlaubt = true;
                erlaubtx = true;
                int countB1 = 0, countB2 = 0, countB3 = 0, countB4 = 0;
                if (saldoCaixa > 0) {
                    while (erlaubt) {
                        //Verificando saque bandeja_(X) | Não exceder quantidade de cédulas na bandeja_(X).
                        if ((valorSaque >= bandeja1.valorDeFace) && (bandeja1.cedulaX < bandeja1.quantidadeCelula)) {
                            //Quantidade de cédulas (cedulaX)
                            bandeja1.cedulaX++;
                            countB1++;
                            valorSaque -= bandeja1.valorDeFace;
                        } else if ((valorSaque >= bandeja2.valorDeFace) && (bandeja2.cedulaX < bandeja2.quantidadeCelula)) {
                            bandeja2.cedulaX++;
                            countB2++;
                            valorSaque -= bandeja2.valorDeFace;
                        } else if ((valorSaque >= bandeja3.valorDeFace) && (bandeja3.cedulaX < bandeja3.quantidadeCelula)) {
                            bandeja3.cedulaX++;
                            countB3++;
                            valorSaque -= bandeja3.valorDeFace;
                        } else if ((valorSaque >= bandeja4.valorDeFace) && (bandeja4.cedulaX < bandeja4.quantidadeCelula)) {
                            bandeja4.cedulaX++;
                            countB4++;
                            valorSaque -= bandeja4.valorDeFace;
                        } else if (valorSaque > 0) {
                            sobra = valorSaque;
                            saqueNeg(bandeja1.valorDeFace, bandeja2.valorDeFace, bandeja3.valorDeFace, bandeja4.valorDeFace);
                            erlaubt = false;
                            erlaubtx = false;
                            novoSaque();
                            break;
                        } else {
                            sobra = valorSaque;
                            erlaubt = false;
                        }
                    }
                    double valor = valorSaque;
                    saldoCaixa -= valorSaque;
                    if (valor == 0) {
                        if (bandeja1.cedulaX > 0) {
                            saldoB1 -= (bandeja1.cedulaX * bandeja1.valorDeFace);
                            ced_100(countB1, bandeja1.valorDeFace);
                        }
                        if (bandeja2.cedulaX > 0) {
                            saldoB2 -= (bandeja2.cedulaX * bandeja2.valorDeFace);
                            ced_50(countB2, bandeja2.valorDeFace);
                        }
                        if (bandeja3.cedulaX > 0) {
                            saldoB3 -= (bandeja3.cedulaX * bandeja3.valorDeFace);
                            ced_20(countB3, bandeja3.valorDeFace);
                        }
                        if (bandeja4.cedulaX > 0) {
                            saldoB4 -= (bandeja4.cedulaX * bandeja4.valorDeFace);
                            ced_10(countB4, bandeja4.valorDeFace);
                        }
                        valorSacado(valorSacado);
                        
                        if (saldoCaixa > 0) {
                            novoSaque();
                        }

                    } else if (saldoCaixa == 0) {
                        saqueInv();
                        caixaVazio();
                    } else {
                        saqueNeg(bandeja1.valorDeFace, bandeja2.valorDeFace, bandeja3.valorDeFace, bandeja4.valorDeFace);
                    }
                } else if (valorSaque > saldoCaixa()) {
                    infoCaixa(saldoCaixa);
                    novoSaque();
                }
            } else if (valorSaque > saldoCaixa()) {
                saqueNeg(bandeja1.valorDeFace, bandeja2.valorDeFace, bandeja3.valorDeFace, bandeja4.valorDeFace);
                novoSaque();
            } else {
                caixaVazio();
            }
        }else{
            caixaVazio();
        }
        return sobra;
    }

    public void bandejasFinal(){
        for(int row=1;row<5;row++){
            if(saldoBandejaX(row)>0){
                show(row, saldoBandejaX(row));
            }
        }
    }
    public void novoSaque(){
        String novo = repetirSaque();
        if(novo.equals("S")){
            sacar(0);
        }else{
            saqueEncerrado();
        }
    }

}
