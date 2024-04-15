package lib;
/**
 * @author Petar Veljovic
 */
public class No <T extends Comparable<T>>
{
    private T        valor;
    private No<T>    direito;
    private No<T>    esquerdo;

    private int             altura;

    public No(T valor)
    {
        this.direito  = null;
        this.esquerdo = null;
        this.valor    = valor;
    }
    /* Get */
    public T getValor()                      {   return valor;               }
    public No<T> getDireito()                {   return direito;             }
    public No<T> getEsquerdo()               {   return esquerdo;            }
    /* Set */
    public void setValor(T valor)            {   this.valor = valor;         }
    public void setDireito(No<T> direito)    {   this.direito = direito;     }
    public void setEsquerdo(No<T> esquerdo)  {   this.esquerdo = esquerdo;   }

}
