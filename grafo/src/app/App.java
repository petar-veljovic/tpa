package app;
import lib.*;
import java.io.*;
import java.util.List;

/**
 * @author Petar Veljovic
 * Essa é a classe App
 */
public class App {
    Grafo grafo;
    Console con;

    public App(Grafo grafo, Console con)
    {
        this.grafo = grafo;
        this.con = con;
    }

    public void tela(){
        String logo = "\n\n" +
                "" +
                "\tBem-vindo! ao\n\n" +
                "\t                              ██████\\                         ██\\                           ██\\       \n" +
                "\t                             ██  __██\\                        ██ |                          ██ |      \n" +
                "\t ██████\\   ██████\\  ██████\\  ██ /  \\__|██████\\   ███████\\     ███████\\   ██████\\   ███████\\ ███████\\  \n" +
                "\t██  __██\\ ██  __██\\ \\____██\\ ████\\    ██  __██\\ ██  _____|    ██  __██\\  \\____██\\ ██  _____|██  __██\\ \n" +
                "\t██ /  ██ |██ |  \\__|███████ |██  _|   ██ /  ██ |\\██████\\      ██ |  ██ | ███████ |\\██████\\  ██ |  ██ |\n" +
                "\t██ |  ██ |██ |     ██  __██ |██ |     ██ |  ██ | \\____██\\     ██ |  ██ |██  __██ | \\____██\\ ██ |  ██ |\n" +
                "\t\\███████ |██ |     \\███████ |██ |     \\██████  |███████  |██\\ ███████  |\\███████ |███████  |██ |  ██ |\n" +
                "\t \\____██ |\\__|      \\_______|\\__|      \\______/ \\_______/ \\__|\\_______/  \\_______|\\_______/ \\__|  \\__|\n" +
                "\t██\\   ██ |                                                                                            \n" +
                "\t\\██████  |                                                                                            \n" +
                "\t \\______/                                                                                             \n" +
                "" +
                "\n                                                                                        @author Petar Veljovic\n\n" +
                "" +
                "";

        System.out.println(logo);
        System.out.println(
                "\t%%%\tMenu de opcoes\n" +
                "\n" +
                "\t[1]\tAcrescentar cidade\n" +
                "\t\t[Sobre]\tCria uma cidade, solicita o nome e, adiciona ao grafo um vértice representando a cidade, caso nao exista.\n" +
                "\n" +
                "\t[2]\tAcrescentar rota\n" +
                "\t\t[Sobre]\tCria uma rota, solicita o nome da cidade de origem, o nome da cidade de destino e a distância da rota.\n" +
                "\t\t\tDeve-se acrescentar ao grafo vértices representando as cidades, caso nao existam, e acrescentar uma\n" +
                "\t\t\taresta com o peso dado para representar a rota entre as duas cidades.\n" +
                "\n" +
                "\t[3]\tCalcular árvore geradora mínima (AGM): \n" +
                "\t\t[Sobre]\tGera a árvore geradora mínima, imprime na tela cada uma das arestas (vértice de origem, vértice de destino e peso) \n" +
                "\t\t\tque vao compor a árvore geradora mínima e a soma total dos pesos das arestas da árvore geradora mínima. \n" +
                "\n" +
                "\t[4]\tCalcular caminho mínimo entre duas cidades\n" +
                "\t\t[Sobre]\tDado o nome da cidade de origem e o nome da cidade de destino. Faz o calculo do caminho mínimo,\n" +
                "\t\t\timprime o caminho mínimo e a distância total do caminho. \n" +
                "\t\t\tNesta opcao o método de calcular o caminho mínimo deve ser executado sobre o grafo original.\n" +
                "\n" +
                "\t[5]\tCalcular caminho mínimo entre duas cidades considerando apenas a AGM\n" +
                "\t\t[Sobre]\tSemelhante à opcao anterior mas, nesta opcao, calcula a AGM primeiro e depois executa o método de calcular \n" +
                "\t\t\to caminho mínimo sobre o grafo resultante do cálculo de AGM. \n" +
                "\n" +
                "\t. . .\n" +
                "\n" +
                "\n" +
                "\t[0]\tGravar e Sair\n" +
                "\t\t[Sobre]\tGrava dois arquivos, ambos seguindo o mesmo formato do arquivo “entrada.txt”.\n" +
                "\t\t\t i. grafoCompleto.txt, contendo o grafo completo construído durante a execucao;\n" +
                "\t\t\tii. agm.txt, contendo a árvore geradora mínima do grafo completo.\n" +
                "\n" +
                "\n");
    }

    public void leOpcao(Integer opcao)
    {
        switch (opcao)
        {
            case 0:
                System.out.println("\n\t[NOTE]\tTerminando o App!");
                GeraArquivo();
                break;
            case 1:
                AddCidade();
                System.out.println("\n\t. . .\n");
                break;
            case 2:
                AddRota();
                System.out.println("\n\t. . .\n");
                break;
            case 3:
                System.out.println("\n\t%%% [3]\tCalcular árvore geradora mínima (AGM)\n");
                AGM();
                System.out.println("\n\t. . .\n");
                break;
            case 4:
                System.out.println("\n\t%%% [4]\tCalcular caminho mínimo entre duas cidades\n");
                caminhoMinimo(false);
                System.out.println("\n\t. . .\n");
                break;
            case 5:
                System.out.println("\n\t%%% [5]\tCalcular caminho mínimo entre duas cidades considerando apenas a AGM\n");
                caminhoMinimo(true);
                System.out.println("\n\t. . .\n");
                break;
            default:
                System.out.println("\n\t[ERRO]\tOpcao Inválida! Favor escolher uma opcao válida.");
                break;
        }
    }
    public void AddCidade()
    {
        try
        {
            Integer pos = grafo.getVertices().size();
            System.out.println("\t%%% [1]\tAcrescentar cidade\n");
            String nome = con.readLine("\tNome da cidade a ser adicionada: ");

            Cidade cidade = new Cidade(pos+1, nome);

            Vertice obj = grafo.getCidade(nome);

            if (obj == null)
            {
                grafo.addVertice(cidade);
                System.out.println("\t[OK]\tCidade adicionada com sucesso: " + cidade.getId() + ", " + cidade.getNome());
            }
            else
            {
                System.out.println("\t[ERRO]\tCidade já existe!");
            }
        }
        catch(Exception e)
        {
            System.out.println("\t[ERRO]\t" + e.toString());
        }

    }
    public void AddRota()
    {
        try
        {
            System.out.println("\t%%% [2]\tAcrescentar rota\n");
            int idlist      = grafo.getVertices().size();

           String origem   = con.readLine("\tDigite o nome do Origem:                  ");

            Vertice objO    = grafo.getCidade(origem);
            int intO;
            Cidade cidO     = null;

            if (objO == null)
            {
                intO = ++idlist;
                cidO = new Cidade(intO, origem);
                grafo.addVertice(cidO);
            }
            else
            {
                cidO = (Cidade) objO.getValor();
                intO = cidO.getId();
            }

            String destino             = con.readLine("\tDigite o nome do Destino:                 ");
            Vertice objD = grafo.getCidade(destino);
            int intD;
            Cidade cidD = null;

            if (objD == null)
            {
                intD = ++idlist;
                cidD = new Cidade(intD, destino);
                grafo.addVertice(cidD);
            }
            else
            {
                cidD = (Cidade) objD.getValor();
                intD = cidD.getId();
            }


            int pesoV = Integer.parseInt(con.readLine("\tDigite o valor do Peso (valor inteiro):   "));
            double pesoD = pesoV;

            if (pesoV != 0)
            {
                grafo.addAresta(new Cidade(intO, cidO.getNome()), new Cidade(intD, cidD.getNome()), pesoD);
            }
            System.out.println("\t[OK]\tRota adicionada com sucesso!");
        }
        catch (Exception e)
        {
            System.out.println("\t[ERRO]\t" + e.getMessage());
        }
    }
                        
    
    public void AGM()
    {
        try
        {
            Grafo novoGrafo = grafo.prim(1);
        }
        catch(Exception e)
        {
            System.out.println("\t[ERRO]\t" + e.toString());
        }
    }

    public static void limpaTela()
            throws IOException, InterruptedException
    {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))     { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); }
        else                        { Runtime.getRuntime().exec("clear"); }
    }

    public void caminhoMinimo(Boolean AGM)
    {
        try
        {
            String origem  = con.readLine("\tDigite o nome do Origem:  ");
            String destino = con.readLine("\tDigite o nome do Destino: ");

            Vertice objO = grafo.getCidade(origem);
            Vertice objD = grafo.getCidade(destino);

            Cidade cidO = (Cidade) objO.getValor();
            Cidade cidD = (Cidade) objD.getValor();

            Integer intO = cidO.getId();
            Integer intD = cidD.getId();

            if (AGM)
            {
                Grafo grafoAGM = grafo.prim(0);
                System.out.println(grafoAGM.dijkstra(new Cidade(intO), new Cidade(intD)));
            }
            else
            {
                System.out.println(grafo.dijkstra(new Cidade(intO), new Cidade(intD)));
            }
        }
        catch(Exception e)
        {
            System.out.println("\t[ERRO]\t" + e.toString());
        }
    }

    public void GeraArquivo()
    {
        String texto ="";

        String workingDirectory = System.getProperty("user.dir");
        String fileGrafoCompleto = "grafoCompleto.txt";
        String fileAgm = "agm.txt";
        String fileTGrafoCompleto = workingDirectory + File.separator + fileGrafoCompleto;
        String fileTAgm = workingDirectory + File.separator + fileAgm;

        File grafocompleto = new File(fileTGrafoCompleto);
        File agms = new File(fileTAgm);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(grafocompleto)))
        {
            texto = grafo.mostrarMatrizAdjacencia();
            bw.write(texto);

        }
        catch (IOException e)
        {
            System.out.println("\t[ERRO]\tNao est possivel gravar arquivo ");
        }
        System.out.println("\t[OK]\tArquivo 'grafoCompleto.txt' gerado com sucesso em " + fileTGrafoCompleto);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(agms)))
        {
            Grafo grafoAGMs = grafo.prim(0);
            texto = grafoAGMs.mostrarMatrizAdjacencia();
            bw.write(texto);
        }
        catch (IOException e)
        {
            System.out.println("\t[ERRO]\tNao est possivel gravar arquivo ");
        }
        System.out.println("\t[OK]\tArquivo 'agm.txt' gerado com sucesso em " + fileTAgm);
    }
}
