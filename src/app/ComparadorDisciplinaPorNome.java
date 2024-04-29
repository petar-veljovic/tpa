package app;

import java.util.Comparator;

/**
 *
 * @author Petar Veljovic
 *
 */

public class ComparadorDisciplinaPorNome implements Comparator<Disciplina>
{
    @Override
    public int compare(Disciplina o1, Disciplina o2)
    {
        return o1.getNome().compareTo(o2.getNome());
    }
}
