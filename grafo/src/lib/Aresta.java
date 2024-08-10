package lib;
/**
 * @author Petar Veljovic
 * Essa é a classe Aresta
 */
public class Aresta <T> implements Comparable <Aresta <T>>
{
    private Vertice<T> destino;
    private double peso;
    public Aresta(Vertice<T> destino, double peso)
    {
        this.destino = destino;
        this.peso = peso;
    }
    public Aresta(double peso) { this.peso = peso; }
    public Vertice<T> getDestino() { return destino; }
    public double getPeso() { return peso; }
    @Override
    public int compareTo(Aresta<T> aresta) { return Double.compare(this.peso, aresta.getPeso()); }
    @Override
    public String toString() { return  " -> " + destino + "; " + peso; }
}
