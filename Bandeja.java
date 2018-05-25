public class Bandeja {
    public double valorDeFace;
    public int quantidadeCelula;
    public int cedulaX=0;

    public Bandeja(double valorDeFace, int quantidadeCelula){
        this.valorDeFace=valorDeFace;
        this.quantidadeCelula=quantidadeCelula;
    }
    public double saldoBandeja(){
        double saldoBandeja = valorDeFace * quantidadeCelula;
        return saldoBandeja;
    }
}
