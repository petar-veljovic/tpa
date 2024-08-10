import app.*;
import lib.*;
import app.App;
import java.io.*;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
/**
 * @author Petar Veljovic
 * Essa é a classe Main
 */
public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Console con = System.console();
        int opcao = -10;
        Grafo grafo = new Grafo<>();
        String caminho = "./entrada.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminho)))
        {
            // Fara a leitura da primeira linha (quantidade de elementos)
            Integer qtd = Integer.parseInt(br.readLine());
            System.out.println("\n\t[OK]\tEncontrado " + qtd + " Cidade(s)!");
            // Fara as leituras dos vertices
            for (int i = 1; i < qtd+1; i++)
            {
                String cidades = br.readLine();
                Cidade cidade = new Cidade(i,cidades);
                grafo.addVertice(cidade);
            }
            //Fara as leituras das arestas
            for (int i = 1; i <= qtd; i++)
            {
                String ListAresta[] = br.readLine().split(",");
                for (int j = 1; j <= qtd; j++)
                {
                    double peso =  Double.parseDouble(ListAresta[j-1]);
                    if (peso != 0) { grafo.addAresta(new Cidade(i), new Cidade(j), peso); }
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("[Erro]\tFalha na leitura do arquivo!");
        }

        App menu = new App(grafo, con);

        for (;opcao != 0;)
        {
            menu.tela();
            try
            {
                opcao = Integer.parseInt(con.readLine("\n\tEscolha a opcao: "));

            }
            catch(Exception e)
            {
                System.out.println("\t[ERRO]\t" + e.toString());
                opcao = 1000;
            }

            menu.limpaTela();
            menu.leOpcao(opcao);
        }
    }
}