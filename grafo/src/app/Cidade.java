package app;

import lib.Vertice;

import java.util.UUID;

/**
 * @author Petar Veljovic
 * Essa é a classe Cidade
 */
public class Cidade {

    private Integer id;
    private String uid = String.valueOf(UUID.randomUUID());
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cidade(Integer id, String nome) {
        this.nome = nome;
        this.id = id;
    }

    public Cidade(Integer id) {
        this.id = id;
    }

    public Cidade(String nome) {
        this.nome = nome;
    }

    public String getUid() {
        return uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        Cidade outro = (Cidade) obj;
        if (id == null)
        {
            if (outro.id != null)
            {
                return false;
            }
        }
        else
        {
            if (!id.equals(outro.id))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString() +"; "+ uid.toString() + "; "+ nome.toString();
    }
}
