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
