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
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }

        return Integer.compare(o1.getMatricula(), o2.getMatricula());
    }

}
