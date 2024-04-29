package app;

import java.util.Comparator;

/**
 *
 * @author Petar Veljovic
 *
 */

public class ComparadorAlunoPorMatricula implements Comparator<Aluno>
{
    @Override
    public int compare(Aluno o1, Aluno o2)
    {
        return Integer.compare(o1.getMatricula(), o2.getMatricula());
    }

}
