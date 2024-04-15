package lib;

import app.Aluno;

import java.util.*;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Comparator;


/**
 * @author Petar Veljovic
 *
 * Implementação da Arvore Binaria
 */
public class ArvoreBinaria <T extends Comparable<T>> implements IArvoreBinaria<T>
{
    private No<T>           raiz;
    private Comparator<T>   comparator;
    public ArvoreBinaria (Comparator<T> comparator)
    {
        this.raiz = null;
        this.comparator = comparator;
    }
    @Override
    public void adicionar(T novoValor)
    {
        No<T> novoNo    = new No<T>(novoValor);
        raiz            = adiciona(raiz, novoNo);
    }
    @Override
    public T remover(T valor)                             {     return remova(raiz, valor);     }
    @Override
    public T pesquisar(T valor)                           {     return buscaElemento(valor);    }
    @Override
    public T pesquisar(T valor, Comparator comparator)    {     return buscaElementoComparator(valor, comparator);    }
    @Override
    public String caminharEmOrdem()                       {     return mostraEmOrdem();         }
    @Override
    public String caminharEmNivel()                       {     return mostraEmNivel();         }
    @Override
    public int altura()                                   {     return altura(raiz);            }
    @Override
    public int quantidadeNos()                            {     return qntNos(raiz);            }

    /**
     * Este método tem como objetivo inserir elemento na árvore
     *
     */
    private No<T> adiciona (No<T> raiz, No<T> novo)
    {
        // Se o nó raiz não existir, o novo nó será retornado
        if (raiz == null)   {   return novo;    }
        // Se não, irá colocar na posição devida
        No<T> lido = raiz;
        // Verificará até que seja adicionado o nó.
        for (;true;)
        {
            if (comparator.compare(novo.getValor(),lido.getValor()) < 0)
            {
                if (lido.getEsquerdo() != null)
                {
                    lido = lido.getEsquerdo();
                }
                else
                {
                    lido.setEsquerdo(novo);
                    // Sai da função for
                    break;
                }
            }
            else
            {
                if (comparator.compare(novo.getValor(),lido.getValor()) > 0)
                {
                    if (lido.getDireito() != null)
                    {
                        lido = lido.getDireito();
                    }
                    else
                    {
                        lido.setDireito(novo);
                        // Sai da função for
                        break;
                    }
                }
                else
                {
                    lido.setValor(novo.getValor());
                    // Sai da função for
                    break;
                }
            }
        }
        // Retorna a arvore com o elemento adicionado
        return raiz;
    }


    /**
     * Este método tem como objetivo achar o pai de um nó
     *
     */
    private No<T> achaPai(No<T> nos, No<T> filho)
    {
        No<T> pai = null;
        // Verificará até o terminio da arvore
        for (;(nos != null);)
        {
            if(comparator.compare(nos.getValor(), filho.getValor()) == 0)
            {
                return pai;
            }
            pai = nos;
            if (comparator.compare(nos.getValor(), filho.getValor()) > 0)
            {
                nos = nos.getEsquerdo();
            }
            else
            {
                nos = nos.getDireito();
            }
        }
        return nos;
    }

    /**
     * Este método tem como objetivo remover um elemento da árvore
     *
     */
    private T remova(No<T> nos, T alvo)
    {
        No<T> pai = null;

        // Verificara até achar o nó alvo para remover, se não tiver, pegará null
        for (;((nos != null) && (comparator.compare(nos.getValor(), alvo) != 0));)
        {
            pai = nos;
            if (comparator.compare(nos.getValor(), alvo) > 0) {   nos = nos.getEsquerdo();    }
            else                                                {   nos = nos.getDireito();     }
        }
        // Se não encontrou o nó alvo para remover, retornará null e termina
        if (nos == null) {  return null;  }

        // Se encontrou o nó alvo para remover, começará o processo de remoção
        T removido = nos.getValor();

        // Se o nó para remover for uma folha, logo não tem filhos,
        if ((nos.getEsquerdo() == null) && (nos.getDireito() == null))
        {
            // Verificará em que posição o nó está para ser removido em relação ao pai
            if (pai != null)
            {
                // Esquerdo
                if (comparator.compare(nos.getValor(), pai.getValor()) < 0)     {  pai.setEsquerdo(null);   }
                // Direito
                else                                                            {  pai.setDireito(null);    }
            }
            else                                                                {  raiz.setValor(null);     }
            // Após isso, o nó é removido
        }
        // Se não, o nó tem filho, é verificado a posição desse filho em relação aos nós
        else
        {
            if ((nos.getEsquerdo() == null) || (nos.getDireito() == null))
            {
                // Será colocado no filho
                No<T> filho = null;
                // É lida a posição do nó que será removido perante ao nó pai e o nó filho a ser removido
                if  (nos.getEsquerdo() != null)                                     {   filho = nos.getEsquerdo();    }
                else                                                                {   filho = nos.getDireito();     }
                if  (pai != null)
                {
                    // Nó filho é armazenado no pai.
                    if (comparator.compare(nos.getValor(), pai.getValor()) < 0)     {   pai.setEsquerdo(filho);       }
                    else                                                            {   pai.setDireito(filho);        }
                }
                else                                                                {   raiz = filho;                 }
            }
            // Se não, nó tem dois filhos
            else
            {
                // É buscado o maior nó na sub árvore da esquerda,
                No<T> maiorNo     = maiorNo       (nos.getEsquerdo());
                No<T> maiorNoPai  = achaPai       (nos, maiorNo);

                nos.setValor(maiorNo.getValor());
                // O nó buscado trocará o nó que será removido da árvore, mantendo coerente a árvore
                if (comparator.compare(maiorNoPai.getDireito().getValor(), maiorNo.getValor()) == 0)  {   maiorNoPai.setDireito (maiorNo.getEsquerdo());    }
                else                                                                                  {   maiorNoPai.setEsquerdo(maiorNo.getEsquerdo());    }
            }
        }
        // Retorna o nó removido
        return removido;
    }

    /**
     * Este método tem como objetivo buscar um elemento na árvore
     *
     */
    private T buscaElemento(T alvo)
    {
        No<T> atual = raiz;
        //int qntnoslidos = 0;

        // É feito um loop para varrer a árvore.
        for (;(atual != null);)
        {
            // Se o elemento alvo é igual o nó, é achado o elemento, retornado, e termina
            if (comparator.compare(atual.getValor(), alvo) == 0) { /* System.out.println("\n\n"+"O n° total de nós percorridos foi: "+qntnoslidos+"\n\n"); */
                return atual.getValor();
            }
            // Se não, continua a busca
            else
            {
                if (comparator.compare(atual.getValor(), alvo) > 0)
                {
                    atual = atual.getEsquerdo();
                }
                else
                {
                    if (comparator.compare(atual.getValor(), alvo) < 0)
                    {
                        atual = atual.getDireito();
                    }
                }
            }
            //qntnoslidos++;
        }
        //System.out.println("\n\n"+"total nos percorridos: "+qntnoslidos+"\n\n");
        return null;
    }

    /**
     * Este método tem como objetivo achar o elemento com comparator
     *
     */
    private T buscaElementoComparator(T alvo, Comparator comparator )
    {
        // É utilizado o algoritmo de pós-ordem

        // Se a arvore for null, retorne null
        if (raiz == null) { return null;}

        Stack<No<T>> pilha = new Stack<No<T>>();
        No<T> no = raiz;

        for (;(no != null) || !(pilha.isEmpty());)
        {
            // Verificar se o nó é o mais esquerdo
            for (; no != null; )
            {
                // Insere um elemento ao topo na pilha
                pilha.push(no);
                no = no.getEsquerdo();
            }
            // Remove um elemento do topo da pilha e recebe o valor
            no = pilha.pop();

            // Verifica se o elemento é o mesmo que o buscado
            if (comparator.compare(no.getValor(), alvo) == 0)
            {
                // Retorna o elemento encontrado
                return no.getValor();
            }
            // Se não vai para a próxima subarvore
            no = no.getDireito();
        }
        // Se não for encontrado, retorne null
        return null;
    }

    /**
     * Este método tem como objetivo percorrer a arvore em ordem
     *
     */
    private String mostraEmOrdem()
    {
        No<T> nos = raiz;
        String delt = "\n ";//"\n";
        String retornoOrdem = "";

        if (nos == null)
        {
            retornoOrdem = "[\n]";
            return retornoOrdem;
        }
        // Cria uma pilha
        Stack<No<T>> pilha = new Stack<No<T>>();
        retornoOrdem +="[";
        // Verifique até que a arvore termine ou a pilha fique vazia
        for (;((nos != null) || (!pilha.isEmpty()));)
        {
            for (;(nos != null);)
            {
                // Adiciona na pilha
                pilha.push(nos);
                // Busca o nó esquerdo
                nos = nos.getEsquerdo();
            }
            // Busca o valor no topo da pilha e retira
            nos = pilha.pop();

            Aluno aluno = (Aluno)nos.getValor();

            //retornoOrdem = retornoOrdem + (nos.getValor() +delt;
            retornoOrdem = retornoOrdem + delt;
            //retornoOrdem = retornoOrdem + "(";
            //retornoOrdem = retornoOrdem + delt;
            retornoOrdem = retornoOrdem + aluno.getMatricula();
            retornoOrdem = retornoOrdem + delt;
            retornoOrdem = retornoOrdem + aluno.getNome();
            //retornoOrdem = retornoOrdem + delt;
            //retornoOrdem = retornoOrdem + ")";
            retornoOrdem = retornoOrdem + delt;

            nos = nos.getDireito();
        }
        retornoOrdem +="]";
        //retornoOrdem = retornoOrdem.replace("), ]", ")]");
        return retornoOrdem;
    }

    /**
     * Este método tem como objetivo percorrer a arvore em nivel
     *
     */
    private String mostraEmNivel()
    {
        No<T> nos = raiz;
        String delt = "\n ";
        // Cria uma Lista Linkada
        LinkedList<No<T>> lista = new LinkedList<>();
        // Adiciona na lista
        lista.add(raiz);

        String retornoNivel = "";

        // Verifica se a arvore é vazia, se for, retorne que não existe
        if (nos == null)
        {
            retornoNivel = "[\n]";
            return retornoNivel;
        }

        // Execute até que a lista fique vazia
        retornoNivel += "[";
        for (;!(lista.isEmpty());)
        {
            // Pega o primeiro elemento da lista
            No<T>           lido = lista.getFirst();
            Aluno           aluno = (Aluno)lido.getValor();

            //retornoNivel += lido.getValor() + delt;
            retornoNivel = retornoNivel + delt;
            //retornoNivel = retornoNivel + "(";
            //retornoNivel = retornoNivel + delt;
            retornoNivel = retornoNivel + aluno.getMatricula();
            retornoNivel = retornoNivel + delt;
            retornoNivel = retornoNivel + aluno.getNome();
            //retornoNivel = retornoNivel + delt;
            //retornoNivel = retornoNivel + ")";
            retornoNivel = retornoNivel + delt;


            // Pega o primeiro elemento da lista e o remove
            lista.removeFirst();
            // Verifica se existe subarvores, se sim, adiciona na lista
            if(lido.getEsquerdo() != null)  { lista.add(lido.getEsquerdo());  }
            if(lido.getDireito() != null)   { lista.add(lido.getDireito());   }
        }
        retornoNivel += "]";
        //retornoNivel = retornoNivel.replace("), ]", ")]");
        return retornoNivel;
    }

    /**
     * Este método tem como objetivo retornar a altura de uma arvore
     *
     */
    private int altura (No<T> nos)
    {
        // Se a arvore não existir, retorne null
        if (nos == null)          {   return 0;   }

        // Cria uma fila
        Queue <No<T>> fila = new LinkedList<>();
        fila.add(nos);

        for (int altura = 0; true; altura++)
        {
            // Verifica se a fila está vazia
            if (fila.isEmpty())
            {
                // Retorna a altura da árvore
                return (altura - 1);
            }
            for (int i = fila.size(); i > 0; i--)
            {
                // Faz a leitura do primeiro que está na fila e recebe como valor
                No<T> novoNo = fila.peek();
                //  Retira o primeiro que está na fila
                fila.remove();
                // Verifica se tem mais nós para serem atendidos
                if (novoNo.getEsquerdo()    != null)    {   fila.add(novoNo.getEsquerdo());   }
                if (novoNo.getDireito()     != null)    {   fila.add(novoNo.getDireito());    }
            }
        }
    }

    /**
     * Este método tem como objetivo retornar a quantidade de nos de uma arvore
     *
     */
    private int qntNos(No<T> nos)
    {
        // Se não existir a arvore, logo terá 0 nós
        if (nos == null ) {  return 0; }
        // Se não, será buscado a quantidade de nós de uma arvore
        // Cria uma pilha
        Stack <No<T>> pilha = new Stack<>();
        // insere a arvore na pilha
        pilha.push(nos);
        int qntNos = 0;

        // continuara até que a pilha fique vazia
        for (;!(pilha.isEmpty());)
        {
            // remove um elemento do topo da pilha e recebe o valor
            No<T> topo = pilha.pop();
            for (;topo != null;)
            {
                if (topo.getDireito() != null)
                {
                    // insere o elemento no topo da pilha e recebe o valor
                    pilha.push(topo.getDireito());
                }
                topo = topo.getEsquerdo();
                // Conta os nós
                qntNos++;
            }
        }
        // Retorna a quantidade de nós
        return qntNos;
    }

    /**
     * Este método tem como objetivo retornar o menor no de uma árvore
     *
     */
    private No<T> menorNo(No<T> nos)
    {
        // Faz o loop para varrer a sub árvore da esquerda até encontrar o último nó da qual será o menor
        for (;nos.getEsquerdo() != null;)   { nos = nos.getEsquerdo();  }
        // Retorna o menor nó
        return nos;
    }

    /**
     * Este método tem como objetivo retornar o maior nó de uma árvore
     *
     */
    private No<T> maiorNo(No<T> nos)
    {
        // Faz o loop para varrer a sub árvore da direita até encontrar o último nó da qual será o maior
        for (;nos.getDireito() != null;)     {  nos = nos.getDireito(); }
        // Retorna o maior nó
        return nos;
    }
}
