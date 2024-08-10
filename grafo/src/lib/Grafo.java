package lib;
import app.Cidade;

import java.util.*;

/**
 * @author Petar Veljovic
 * Essa é a classe Grafo
 * modo: Listas de adjacência
 */
public class Grafo <T>
{
    private ArrayList<Vertice<T>> vertices = new ArrayList<>();

    public ArrayList<Vertice<T>> getVertices() { return vertices; }
    public Vertice<T> addVertice(T valor)
    {
        Vertice<T> novoVertice = new Vertice<T>(valor);
        vertices.add(novoVertice);
        return novoVertice;
    }

    public Vertice getVertice(T valor)
    {
        // Percorra a lista de vertice até encontrar o valor desejado,
        // caso contrário será retornado null
        for (int i = 0; i < vertices.size(); i++) {
            Vertice atual = vertices.get(i);
            if (atual.getValor().equals(valor)) {
                return atual;
            }
        }
        return null;
    }

    public Vertice getCidade(String nome)
    {
        // Percorre a lista de vertice com a cidade até encontrar o valor desejado,
        // caso contrário será retornado null
        for (int i = 0; i < vertices.size(); i++) {
            Vertice atual = vertices.get(i);
            Cidade obj = (Cidade) atual.getValor();
            if (obj.getNome().toString().equals(nome)) {
                return atual;
            }
        }
        return null;
    }

    public void addAresta(T origem, T destino, Double peso)
    {
        Vertice origemObj, destinoObj;

        // Fara a busca pelo vertice de origem
        origemObj = getVertice(origem);
        // Caso o vertice de origem não exista, o mesmo será criado
        if (origemObj == null) { origemObj = addVertice(origem); }
        // Fara a busca pelo vertice de destino
        destinoObj = getVertice(destino);
        // Caso o vertice de destino não exista, o mesmo será criado
        if (destinoObj == null) { destinoObj = addVertice(destino); }
        // Fara a adicao da aresta na lista de adjacência do vertice de origem
        origemObj.addDestino(new Aresta(destinoObj, peso));
    }
    public Grafo<T> prim(int c)
    {
        String format = "%-20s %-40s %-40s %-20s %-20s %-40s %-40s %-40s";
        String texto = "\n";

        Grafo       <T>             grafo       = new Grafo<>();
        ArrayList   <Vertice<T>>    marcado     = new ArrayList<>();
        // Fara a selecao do primeiro no do grafo
        Vertice     <T>             atual       = vertices.get(0);
        // Fara a adicao da primeira cidade como um novo vertice no grafo
        grafo.addVertice(atual.getValor());
        // Fara a marcacao do vértice atual como visitada
        marcado.add(atual);
        // Usara o marcado para ciclo

        // Elaboracao do retorno da tela
        texto = texto + (String.format(format, "\t%%% origem" , "\t" , "\t", "<--->", "\t%%% destino" , "\t" , "\t", "\t" )) .toString();
        texto = texto  + "\n";
        texto = texto + (String.format(format, "\tindice" , "\tuid" , "\tnome da cidade", "<--->", "\tindice" , "\tuid" , "\tnome da cidade", "\tpeso" )) .toString();
        texto = texto + "\n";

        for (;marcado.size() < vertices.size();)
        {
            // Definira uma aresta como referencia para encontrar a menor aresta
            Aresta<T> menor = new Aresta<T>(Double.MAX_VALUE);
            // Ciclo para percorrer os vértices marcados
            for (int i = 0; i < marcado.size(); i++)
            {
                // Recebera as arestas do vertice
                ArrayList<Aresta> arestas = marcado.get(i).getDestinos();
                // Verifica se existe arestas no vértice atual
                if (arestas.size() > 0)
                {
                    for (int j = 0; j < arestas.size(); j++)
                    {
                        if (!marcado.contains(arestas.get(j).getDestino()) && menor.getPeso() > arestas.get(j).getPeso())
                        {
                            menor = arestas.get(j);
                            atual = marcado.get(i);
                        }
                    }   
                }
            }
            marcado.add(menor.getDestino());
            //Adiciona a cidade vizinha do vértice atual como um novo vértice no grafo
            grafo.addVertice(menor.getDestino().getValor());
            //Adicionar uma nova aresta
            grafo.addAresta(atual.getValor(), menor.getDestino().getValor(), menor.getPeso());
            grafo.addAresta(menor.getDestino().getValor(), atual.getValor(), menor.getPeso());

            texto = texto + (String.format(format, "\t" +((Cidade) atual.getValor()).getId() , "\t" + ((Cidade) atual.getValor()).getUid() ,
                            "\t"+ ((Cidade) atual.getValor()).getNome() , "<--->",
                            "\t" +((Cidade) menor.getDestino().getValor()).getId() , "\t" + ((Cidade) menor.getDestino().getValor()).getUid() ,
                            "\t"+ ((Cidade) menor.getDestino().getValor()).getNome() , "\t"+ menor.getPeso() ) .toString());

            texto = texto + "\n";
        }
        texto = texto +"\n\n\tSoma total dos pesos da aresta: \n\t" + grafo.arestasSoma();
        texto = texto.replace(".00", "").replace(".0", "").toString();
        // Fara a visualizacao da tela o conteudo, caso solicitado
        if (c==1) { System.out.println(texto); System.out.println("\n\n");}

        return grafo;
        // O(V^2)
    }

    private Double arestasSoma()
    {
        Double total = 0.0;
        // Lista de arestas
        ArrayList<Aresta> ListaAresta = new ArrayList<>();
        for (int i = 0; i < this.vertices.size(); i++)
        {
            Vertice vertex = this.vertices.get(i);
            ArrayList<Aresta> arestas = vertex.getDestinos();
            for (int j = 0; j < arestas.size(); j++)
            {
                Aresta aresta = arestas.get(j);
                if (!ListaAresta.contains(aresta)) { ListaAresta.add(aresta); }
            }
        }
        // Soma dos pesos das arestas
        for (int i = 0; i < ListaAresta.size(); i++)
        {
            Aresta aresta = ListaAresta.get(i);
            total += aresta.getPeso();
        }
        total = (total / 2);
        return total;
    }


    public String dijkstra(T origem, T destino)
    {
        String format = "%-20s %-40s %-40s %-40s %-40s %-40s";
        String texto = "";

        // Mapeamento da lista de distâncias e predecessores
        Map<Vertice, Double> distancias = new HashMap<>();
        Map<Vertice, Vertice> anterior = new HashMap<>();

        // Fila de prioridade para selecionar o vértice com a menor distância
        PriorityQueue<Vertice> filaPrioridade = new PriorityQueue<>(Comparator.comparing(distancias::get));

        // Inicialização: define todas as distâncias como infinito, menos a origem
        for (int i = 0; i < vertices.size(); i++)
        {
            Vertice vertice = vertices.get(i);
            distancias.put(vertice, Double.MAX_VALUE);
            anterior.put(vertice, null);
        }

        Vertice origemVertice = getVertice(origem);
        distancias.put(origemVertice, 0.0);
        filaPrioridade.add(origemVertice);
        // Ciclo principal
        for (;!filaPrioridade.isEmpty();)
        {
            Vertice atual = filaPrioridade.poll();

            // Se chegou ao destino, termina o ciclo

            //if (atual.equals(getVertice(destino))) { break; }

            // Processa cada aresta adjacente
            for (int i = 0; i < atual.getDestinos().size(); i++)
            {
                Aresta aresta = (Aresta) atual.getDestinos().get(i);
                Vertice vizinho = aresta.getDestino();
                double novaDistancia = distancias.get(atual) + aresta.getPeso();

                if (novaDistancia < distancias.get(vizinho))
                {
                    distancias.put(vizinho, novaDistancia);
                    anterior.put(vizinho, atual);
                    // Reinsere na fila de prioridade com a nova distância
                    filaPrioridade.add(vizinho);
                }
            }
        }

        // Reconstituir o caminho da origem ao destino
        List<Vertice> caminho = new LinkedList<>();
        Vertice passo = getVertice(destino);

        if (anterior.get(passo) != null || passo.equals(origemVertice))
        {
            for (;passo != null;)
            {
                caminho.add(0, passo);
                passo = anterior.get(passo);
            }
        }

        texto = texto + "\n" + String.format(format, "\tindice", "\tuid", "\tnome da cidade", "\tdistância", "indice predecessor", "predecessor cidade");

        // Adiciona detalhes de distâncias e predecessores
        for (int i = 0; i < vertices.size(); i++)
        {
            Double cidPredessor = -1.0;
            String nomeCidPred  = "";
            //String nomeCidPred= ((Cidade) vertices.get(i).getValor()).getNome();

            Cidade cidade = (Cidade) vertices.get(i).getValor();

            if (anterior.get(vertices.get(i)) != null)
            {
                cidPredessor = vertices.indexOf(anterior.get(vertices.get(i)))+0.0;
                nomeCidPred= ((Cidade) vertices.get(vertices.indexOf(anterior.get(vertices.get(i)))).getValor()).getNome();
            }

            String cidadeInfo = String.format(
                    format,
                    "\t" + i,
                    "\t" + cidade.getUid().toString(),
                    "\t" + cidade.getNome().toString(),
                    "\t" + distancias.get(vertices.get(i)).toString(),
                    "\t" + cidPredessor.toString(),
                    "\t " + nomeCidPred.toString()

            );
            texto = texto + "\n" + cidadeInfo;
        }

        texto = texto + "\n\n\tTrajeto:\n\t     ";
        for (int i = 0; i < caminho.size(); i++)
        {
            Cidade cidade = (Cidade) caminho.get(i).getValor();
            texto = texto + cidade.getNome();
            if (i < caminho.size() - 1) {
                texto = texto + "    --->    ";
            }
        }

        texto = texto + "\n\n\tDistância total:\n\t" + distancias.get(getVertice(destino)).toString() + "\n\n";
        texto = texto.replace(".00", "").replace(".0", "").toString();

        return texto;
        // O((V+E)logV)
    }


    public String mostrarMatrizAdjacencia()
    {
        String texto = "";
        int tamanho = vertices.size();
        texto += tamanho +"\n";
        for (int i = 0; i < vertices.size(); i++) {
            Vertice vert = vertices.get(i);
            texto += ((Cidade) vert.getValor()).getNome() + "\n";
        }
        Double[][] matriz = new Double[tamanho][tamanho];

        // Inicializa a matriz com definicao
        for (int i = 0; i < tamanho; i++) { for (int j = 0; j < tamanho; j++) { matriz[i][j] = 0.0; } }
        // Preenche a matriz com os pesos das arestas
        for (int i = 0; i < tamanho; i++)
        {
            Vertice<T> vertice = vertices.get(i);
            ArrayList<Aresta> arestas = vertice.getDestinos();
            for (int j = 0; j < arestas.size(); j++) {
                Aresta aresta = arestas.get(j);
                int destinoIndex = vertices.indexOf(aresta.getDestino());
                matriz[i][destinoIndex] = aresta.getPeso();
            }
        }
        // Exibe a matriz de adjacência
        for (int i = 0; i < tamanho; i++)
        {
            for (int j = 0; j < tamanho; j++)
            {
                texto += (matriz[i][j]);
                if (j != tamanho-1) { texto +=  ","; }
            }
            texto += "\n";
        }
        texto = texto.replace(".00","").replace(".0","");
        return texto;
    }
}