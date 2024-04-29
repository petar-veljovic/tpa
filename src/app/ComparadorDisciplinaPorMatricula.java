package app;

import java.util.Comparator;

/**
 *
 * @author Petar Veljovic
 *
 */

public class ComparadorDisciplinaPorMatricula implements Comparator<Disciplina>
{
    @Override
    public int compare(Disciplina o1, Disciplina o2)
    {
        return Integer.compare(o1.getMatricula(), o2.getMatricula());
    }

}
