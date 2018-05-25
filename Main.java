public class Main {
    public static void main(String[] args) {
        CaixaEletronico caixa = new CaixaEletronico("ap345", "Strobelallee", 50, "Dortmund", "Nordrhein-Westfalen");
        System.out.println("ATM: "+caixa.getCodigo()+
                "\nLogradouro: "+caixa.getLogradouro()+
                "\nNúmero: "+caixa.getNumero()+
                "\nCidade: "+caixa.getCidade()+
                "\nEstado: "+caixa.getEstado());
        caixa.sacar(0);
    }
}
