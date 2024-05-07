package lib;
/**
 * @author Petar Veljovic
 */
public class No <T extends Comparable<T>>
{
    private T           valor;
    private No<T>       direito;
    private No<T>       esquerdo;
    private int         altura;

    public No(T valor)
    {
        this.direito  = null;
        this.esquerdo = null;
        this.valor    = valor;
        this.altura = 0;
    }

    /* Get */
    public int getAltura()                  {   return altura;              }
    public T getValor()                     {   return valor;               }
    public No<T> getDireito()               {   return direito;             }
    public No<T> getEsquerdo()              {   return esquerdo;            }
    /* Set */
    public void setValor(T valor)           {   this.valor = valor;         }
    public void setDireito(No<T> direito)   {   this.direito = direito;     }
    public void setEsquerdo(No<T> esquerdo) {   this.esquerdo = esquerdo;   }
    public void setAltura(int altura)       {   this.altura = altura;       }
    /**
     * Este método tem como objetivo calcular a altura
     *
     */
    private int calculoAltura(No<T> nos)
    {
        /* Caso não exista, retorna */
        if (nos == null) { return -1; }
        /* Altura do direito */
        int da = calculoAltura(nos.getDireito());
        /* Altura do esquerdo */
        int ea = calculoAltura(nos.getEsquerdo());
        /* Verifica qual subárvore é maior, assim realiza a soma com a aresta da raiz */
        if (da > ea)
        {
            return da + 1;
        }
        else
        {
            return ea + 1;
        }
    }

    public int calculoAltura()
    {
        return calculoAltura(this);
    }

    /**
     * Este método tem como objetivo realizar o fator balanco
     *
     */
    public int fatorBalanco()
    {
        if (this.getDireito() != null && this.getEsquerdo() != null)
        {
            return (this.getDireito().getAltura() - this.getEsquerdo().getAltura());
        }
        else
        {
            if (this.getDireito() != null)
            {
                return (this.getDireito().getAltura() + 1);
            }
            else
            {
                if (this.getEsquerdo() != null)
                {
                    return (-1 - this.getEsquerdo().getAltura());
                }
                else
                {
                    return 0;
                }
            }
        }
    }
}
