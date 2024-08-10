package lib;

import java.util.ArrayList;
/**
 * @author Petar Veljovic
 * Essa é a classe Vertice
 */
public class Vertice <T>
{
    private T valor;
    private ArrayList<Aresta> destinos = new ArrayList<>();

    public Vertice(T valor){ this.valor = valor; }

    public T getValor() { return valor; }

    public void addDestino(Aresta aresta) { destinos.add(aresta); }

    public ArrayList<Aresta> getDestinos() { return destinos; }

    @Override
    public String toString() { return "-> " + valor; }
}
