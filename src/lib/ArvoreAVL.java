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
    @Override
    protected No<T> adiciona(No<T> atual, No<T> novo)
    {
        //adiciona o nó normalmente na árvore
        //current = super.adiciona(current, novo);
        atual = super.adicionaRecursiva(atual, novo);
        //calculo do fator de balanceamento do nó atual
        int fatbal = atual.fatorBalanco();
        //deve-se balancear a árvore sempre que o FB for > 1 ou < -1
        if (fatbal > 1)
        {
            //verificação para retoção à esquerda
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
                //verificação para retoção à direita
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

    @Override
    protected T remova(No<T> nos, T alvo)
    {
        //adiciona o nó normalmente na árvore
        //current = super.adiciona(current, novo);
        T norm = super.remova(nos, alvo);
        No <T> arvrm = this.raiz;
        //calculo do fator de balanceamento do nó atual
        int fatbal = arvrm.fatorBalanco();
        //deve-se balancear a árvore sempre que o FB for > 1 ou < -1
        if (fatbal > 1)
        {
            //verificação para retoção à esquerda
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


    public No<T> rotacionaEsquerda(No<T> nos){
        No<T> filho = nos.getDireito();
        nos.setDireito(filho.getEsquerdo());
        filho.setEsquerdo(nos);
        //altera os valores da altura dos nós
        nos.setAltura(nos.calculoAltura());
        filho.setAltura(filho.calculoAltura());
        return filho;
    }

    public No<T> rotacionaDireita(No<T> nos){
        No<T> filho = nos.getEsquerdo();
        nos.setEsquerdo(filho.getDireito());
        filho.setDireito(nos);
        //altera os valores da altura dos nós
        nos.setAltura(nos.calculoAltura());
        filho.setAltura(filho.calculoAltura());
        return filho;
    }

    public No<T> rotacionaEsquerdaParaDireita(No<T> nos){
        //rotaciona a esquerda o filho à esquerda do nó
        nos.setEsquerdo(rotacionaEsquerda(nos.getEsquerdo()));
        //rotaciona o nó à direita
        return rotacionaDireita(nos);
    }

    public No<T> rotacionaDireitaParaEsquerda(No<T> nos){
        /* É rotacionado a direita o filho para direita do nó */
        nos.setDireito(rotacionaDireita(nos.getDireito()));
        /* É rotaciona o nó à esquerda */
        return rotacionaEsquerda(nos);
    }
}
