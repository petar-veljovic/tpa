import app.     *;
import lib.     *;
import java.io. *;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        ArvoreBinaria<Aluno>        AlunosPorMatricula          = new ArvoreBinaria<Aluno>(new ComparadorAlunoPorMatricula());
        ArvoreBinaria<Aluno>        AlunosPorNome               = new ArvoreBinaria<Aluno>(new ComparadorAlunoPorNome());
        ArvoreBinaria<Disciplina>   DisciplinasPorMatricula     = new ArvoreBinaria<Disciplina>(new ComparadorDisciplinaPorMatricula());
        ArvoreBinaria<Disciplina>   DisciplinasPorNome          = new ArvoreBinaria<Disciplina>(new ComparadorDisciplinaPorNome());

        // Caminhos
        String workingDirectory     = System.getProperty("user.dir");
        String arqAluno             = "20241tpapetarvalunos.txt";
        String arqDisciplinas       = "20241tpapetarvdisciplinas.txt";
        String arqTotalAluno        = workingDirectory + File.separator + arqAluno;
        String arqTotalDisciplinas  = workingDirectory + File.separator + arqDisciplinas;

        String cAluno               = arqTotalAluno;
        String cDisciplina          = arqTotalDisciplinas;

        try (BufferedReader br = new BufferedReader(new FileReader(cAluno)))
        {
            String lineAluno = br.readLine();
            for (;lineAluno != null;) {
                String dataAluno[] = lineAluno
                        .replaceAll("\\R+", " ")
                        .replace(",", "")
                        .replace("\n", "")
                        .replace("\r", "")
                        .replace("\t", "")
                        .replace("[", "")
                        .replace("]", "")
                        .replace("Aluno; ", "")
                        .replace("Matricula=", "")
                        .replace("Nome=", "")
                        .replace("CursosCursados=", "")
                        .trim()
                        .split(";");

                if (dataAluno.length >= 3)
                {
                        Aluno aluno = new Aluno(
                        Integer.parseInt(dataAluno[0]),
                        dataAluno[1],
                        dataAluno[2]
                        );
                    AlunosPorMatricula.adicionar(aluno);
                    AlunosPorNome.adicionar(aluno);
                }
                //}

                lineAluno               = br.readLine();
            }
        }    catch (IOException e)   {     //System.out.println("Erro: "+ e.getMessage());
             }

        try (BufferedReader br = new BufferedReader(new FileReader(cDisciplina)))
        {
            String lineDisciplina = br.readLine();
            lineDisciplina = br.readLine();
            for (;lineDisciplina != null;)
            {
                String  dataDisciplina[]     = lineDisciplina
                        .replaceAll("\\R+", " ")
                        .replace(",", "")
                        .replace("\n", "")
                        .replace("\r", "")
                        .replace("\t", "")
                        .replace("[","")
                        .replace("]","")
                        .replace("Disciplina;","")
                        .replace("Matricula=","")
                        .replace("Nome=","")
                        .replace("PreRequisito=","")
                        .replace("CargaHoraria=","")
                        .trim()
                        .split(";");
                if (dataDisciplina.length >= 4)
                {
                    Disciplina disciplina = new Disciplina(
                            Integer.parseInt(dataDisciplina[0]),
                            dataDisciplina[1],
                            dataDisciplina[2],
                            "",
                            "",
                            "",
                            Integer.parseInt(dataDisciplina[3])
                    );
                    DisciplinasPorMatricula.adicionar(disciplina);
                    DisciplinasPorNome.adicionar(disciplina);
                }
                lineDisciplina                  = br.readLine();
            }
        }  catch (IOException e)  {  /*System.out.println("Erro: "+ e.getMessage());*/ }

        // Interface com o usuário via terminal
        Console con = System.console();
        App     menu    = new   App
                                (   con,
                                    AlunosPorNome,
                                    AlunosPorMatricula,
                                    DisciplinasPorNome,
                                    DisciplinasPorMatricula
                                );
        int     opcao  = -1;

        for (;opcao != 0;)
        {
            System.gc();
            menu.BemVindo();
            menu.MostraOpcao();
            opcao = Integer.parseInt(con.readLine("\t=> Opção desejada: "));
            App.LimpaMenu();
            menu.OpcaoEscolha(opcao);
        }
    }
}