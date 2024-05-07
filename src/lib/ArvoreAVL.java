package lib;

import java.util.Comparator;

/**
 * @author Petar Veljovic
 */
public class ArvoreAVL <T extends Comparable<T>> extends ArvoreBinaria<T> implements IArvoreBinaria<T>
{
    public ArvoreAVL(Comparator<T> comp) {
        super(comp);
    }

    /**
     * Este método tem como objetivo adicionar o no para uma arvore AVL
     *
     */
    @Override
    protected No<T> adiciona(No<T> atual, No<T> novo)
    {
        // Adiciona o nó normalmente na árvore
        atual = super.adicionaRecursiva(atual, novo);
        // É calculado o fator de balanceamento do nó atual
        int fatbal = atual.fatorBalanco();
        // Tem que balancear a árvore sempre que o FB for > 1 ou < -1
        if (fatbal > 1)
        {
            // Verificará para retoção à esquerda
            if (atual.getDireito().fatorBalanco() > 0)
            {
                atual = this.rotacionaEsquerda(atual);
            }
            else
            {
                atual = this.rotacionaDireitaParaEsquerda(atual);
            }
        }
        else
        {
            if (fatbal < -1)
            {
                // Verificará para retoção à direita
                if (atual.getEsquerdo().fatorBalanco() < 0)
                {
                    atual = this.rotacionaDireita(atual);
                }
                else
                {
                    atual = this.rotacionaEsquerdaParaDireita(atual);
                }
            }
        }
        return atual;
    }

    /**
     * Este método tem como objetivo remover o no para uma arvore AVL
     *
     */
    @Override
    protected T remova(No<T> nos, T alvo)
    {
        // Faz a remoção do nó normalmente na árvore
        T norm = super.remova(nos, alvo);
        No <T> arvrm = this.raiz;
        // É calculado o fator de balanceamento do nó atual
        int fatbal = arvrm.fatorBalanco();
        // Tem que balancear a árvore sempre que o FB for > 1 ou < -1
        if (fatbal > 1)
        {
            // Verificará  para retoção à esquerda
            if (arvrm.getDireito().fatorBalanco() > 0)
            {
                arvrm = this.rotacionaEsquerda(arvrm);
            }
            else
            {
                arvrm = this.rotacionaDireitaParaEsquerda(arvrm);
            }
        }
        else
        {
            if (fatbal < -1)
            {
                // Verificará para retoção à direita
                if (arvrm.getEsquerdo().fatorBalanco() < 0)
                {
                    arvrm = this.rotacionaDireita(arvrm);
                }
                else
                {
                    arvrm = this.rotacionaEsquerdaParaDireita(arvrm);
                }
            }
        }
        return norm;
    }
    /**
     * Este método tem como objetivo rotacionar para esquerda o no da arvore AVL
     *
     */
    public No<T> rotacionaEsquerda(No<T> nos)
    {
        No<T> filho = nos.getDireito();
        nos.setDireito(filho.getEsquerdo());
        filho.setEsquerdo(nos);
        // Muda os valores da altura dos nós
        nos.setAltura(nos.calculoAltura());
        filho.setAltura(filho.calculoAltura());
        return filho;
    }
    /**
     * Este método tem como objetivo rotacionar para direita o no da arvore AVL
     *
     */
    public No<T> rotacionaDireita(No<T> nos)
    {
        No<T> filho = nos.getEsquerdo();
        nos.setEsquerdo(filho.getDireito());
        filho.setDireito(nos);
        // Muda os valores da altura dos nós
        nos.setAltura(nos.calculoAltura());
        filho.setAltura(filho.calculoAltura());
        return filho;
    }
    /**
     * Este método tem como objetivo rotacionar o no esquerdo para direito da arvore AVL
     *
     */
    public No<T> rotacionaEsquerdaParaDireita(No<T> nos)
    {
        // É rotacionado a esquerda o filho à esquerda do nó
        nos.setEsquerdo(rotacionaEsquerda(nos.getEsquerdo()));
        // É rotacionado o nó à direita
        return rotacionaDireita(nos);
    }
    /**
     * Este método tem como objetivo rotacionar o no direito para esquerdo da arvore AVL
     *
     */
    public No<T> rotacionaDireitaParaEsquerda(No<T> nos)
    {
        /* É rotacionado a direita o filho para direita do nó */
        nos.setDireito(rotacionaDireita(nos.getDireito()));
        /* É rotacionado o nó à esquerda */
        return rotacionaEsquerda(nos);
    }
}
