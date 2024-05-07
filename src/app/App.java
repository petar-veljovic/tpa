package app;

import lib.*;
import java.io.*;

/**
 *
 * @author Petar Veljovic
 *
 */

public class App < T > {
    protected ArvoreBinaria < Aluno > AlunosPorNome;
    protected ArvoreBinaria < Aluno > AlunosPorMatricula;
    protected ArvoreBinaria < Disciplina > DisciplinasPorNome;
    protected ArvoreBinaria < Disciplina > DisciplinasPorMatricula;
    protected Console con;

    public App(
            Console con,
            ArvoreBinaria < Aluno > AlunosPorNome,
            ArvoreBinaria < Aluno > AlunosPorMatricula,
            ArvoreBinaria < Disciplina > DisciplinasPorNome,
            ArvoreBinaria < Disciplina > DisciplinasPorMatricula
    ) {
        this.AlunosPorMatricula = AlunosPorMatricula;
        this.AlunosPorNome = AlunosPorNome;
        this.DisciplinasPorMatricula = DisciplinasPorMatricula;
        this.DisciplinasPorNome = DisciplinasPorNome;
        this.con = con;
    }

    public void BemVindo() {
        System.out.println("\n" +
                "\tBem-vindo! ao ");
        System.out.println("" +
                "\t         /$$                         /$$                                     /$$                           /$$      \n" +
                "\t                                    | $$                        |__/                                    | $$                          | $$      \n" +
                "\t  /$$$$$$   /$$$$$$$  /$$$$$$   /$$$$$$$  /$$$$$$  /$$$$$$/$$$$  /$$  /$$$$$$$  /$$$$$$                 | $$$$$$$   /$$$$$$   /$$$$$$$| $$$$$$$ \n" +
                "\t |____  $$ /$$_____/ |____  $$ /$$__  $$ /$$__  $$| $$_  $$_  $$| $$ /$$_____/ /$$__  $$                | $$__  $$ |____  $$ /$$_____/| $$__  $$\n" +
                "\t  /$$$$$$$| $$        /$$$$$$$| $$  | $$| $$$$$$$$| $$ \\ $$ \\ $$| $$| $$      | $$  \\ $$                | $$  \\ $$  /$$$$$$$|  $$$$$$ | $$  \\ $$\n" +
                "\t /$$__  $$| $$       /$$__  $$| $$  | $$| $$_____/| $$ | $$ | $$| $$| $$      | $$  | $$                | $$  | $$ /$$__  $$ \\____  $$| $$  | $$\n" +
                "\t|  $$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$$| $$ | $$ | $$| $$|  $$$$$$$|  $$$$$$/       /$$      | $$$$$$$/|  $$$$$$$ /$$$$$$$/| $$  | $$\n" +
                "\t \\_______/ \\_______/ \\_______/ \\_______/ \\_______/|__/ |__/ |__/|__/ \\_______/ \\______/       |__/      |_______/  \\_______/|_______/ |__/  |__/" +
                "\n\n");
    }

    public void MostraOpcao() {
        System.gc();
        System.out.println("" +
                "\t%%% Menu de opções" +
                "\n\n" +
                "\t[1]\tCadastrar Aluno\n" +
                "\t\t\t[Sobre]\tCria o registro de um aluno;\n" +
                "\t[2]\tCadastrar Disciplina\n" +
                "\t\t\t[Sobre]\tCria o registro de uma disciplina;\n" +
                "\t[3]\tInformar Pré-Requisito\n" +
                "\t\t\t[Sobre]\tDadas duas disciplinas previamente cadastradas,\n" +
                "\t\t\t\tregistra que a primeira é pré-requisito da segunda.\n" +
                "\t[4]\tInformar Disciplina cursada\n" +
                "\t\t\t[Sobre]\tDado um aluno e uma disciplina previamente cadastrados,\n" +
                "\t\t\t\tverifica se o aluno cursou todos os pré-requisitos da disciplina dada e,\n" +
                "\t\t\t\tcaso positivo, registra que o aluno cursou tal disciplina.\n" +
                "\t\t\t\tCaso contrário exibe uma mensagem informando as disciplinas não cursadas.\n" +
                "\t[5]\tConsultar Aluno por Nome\n" +
                "\t\t\t[Sobre]\tDado o nome do aluno imprime todos os dados do mesmo,\n" +
                "\t\t\t\tinclusive as disciplinas já cursadas (código e nome de cada disciplina cursada).\n" +
                "\t[6]\tConsultar Aluno por Matrícula\n" +
                "\t\t\t[Sobre]\tDada a matrícula do aluno imprime todos os dados do mesmo.\n" +
                "\t\t\t\tinclusive as disciplinas já cursadas (código e nome de cada disciplina cursada).\n" +
                "\t[7]\tConsultar Disciplina por Nome\n" +
                "\t\t\t[Sobre]\tDado o nome do Disciplina imprime todos os dados do mesmo\n" +
                "\t[8]\tConsultar Disciplina por Matrícula\n" +
                "\t\t\t[Sobre]\tDada a matrícula da disciplina imprime todos os dados do mesmo.\n" +
                "\t[9]\tExcluir Aluno por Matrícula\n" +
                "\t\t\t[Sobre]\tDada a matrícula, exclui o aluno.\n" +
                "\t[10]\tExcluir Disciplina por Matrícula\n" +
                "\t\t\t[Sobre]\tDada a matrícula, exclui a disciplina.\n" +
                "\n" +
                "\t. . .\n" +
                "\n" +
                "\t[0]\tSair\n" +
                "\n"
        );
    }

    public void OpcaoEscolha(int option) {

        switch (option) {
            case 0:
                //end = "1";
                GeraArquivo();
                System.out.println("\tTerminando o App!");
                break;
            case 1:
                AdicionaAluno();
                break;
            case 2:
                AdicionaDisciplina();
                break;
            case 3:
                PreRequisitoDisciplina();
                break;
            case 4:
                InformaDisciplinaCursada();
                break;
            case 5:
                BuscaAluno(0);
                break;
            case 6:
                BuscaAluno(1);
                break;
            case 7:
                BuscaDisciplina(0);
                break;
            case 8:
                BuscaDisciplina(1);
                break;
            case 9:
                RemovaAluno();
                break;
            case 10:
                RemovaDisciplina();
                break;
            default:
                System.out.println("\tOpção Selecionada Invalida!");
                break;

        }
    }
    public void PreRequisitoDisciplina() {
        try {
            boolean cnd = false;
            System.out.println("\n\n\t%%%%% Inserir Pré-Requisito na Disciplina\n\n");
            for (; !cnd;) {
                Integer matricula = Integer.parseInt(con.readLine("\tInsira o numero da matricula da disciplina: "));
                var discObtida = DisciplinasPorMatricula.pesquisar(new Disciplina(matricula, "", "", "", "", "", 0));

                if (discObtida == null) {
                    System.out.println("\tDisciplina não encontrada!\n\n");
                } else {
                    cnd = !cnd;
                    System.out.println("\tDisciplina encontrada!\n\n");
                    String PreRequisito = con.readLine("\tInsira os nomes dos cursos Pre-Requisitos da disciplina separados por ,: ");
                    DisciplinasPorMatricula.pesquisar(new Disciplina(matricula, "", "", "", "", "", 0))
                            .setPreRequisito(
                                    PreRequisito
                                            .trim()
                                            .replace("  ", " ")
                                            .replace(" ,", ",").replace(", ", ",")
                            );
                    System.out.println("\n\tRegistrado com sucesso!\n\n");
                }
            }
        } catch (Exception e) {
            System.out.println("\tDados invalidos!");
        }
    }
    public void InformaDisciplinaCursada() {
        try {
            boolean cnd = false;
            System.out.println("\n\n\t%%%%% Registrar Aluno na Disciplina\n\n");
            for (; !cnd;) {
                Integer matriculaaluno = Integer.parseInt(con.readLine("\tInsira o numero da matricula do aluno: "));
                Integer matriculadiscip = Integer.parseInt(con.readLine("\tInsira o numero da matricula da disciplina: "));
                cnd = !cnd;
                var bam = AlunosPorMatricula.buscaElemento(new Aluno(matriculaaluno, "", ""));
                var bdm = DisciplinasPorMatricula.buscaElemento(new Disciplina(matriculadiscip, "", "", "", "", "", 0));
                String cursosCursados = bam.getCursoCursado();
                String PreRequisitos = bdm.getPreRequisito();
                String[] listaCursoCursado = cursosCursados.split(",");
                String[] listaPreRequisitos = PreRequisitos.split(",");

                for (int i = 0; i != listaPreRequisitos.length; i++) {
                    PreRequisitos = PreRequisitos.replace(listaCursoCursado[i], "");
                }
                if (PreRequisitos
                        .replaceAll("\\R+", " ")
                        .replace(",", "")
                        .replace("\n", "")
                        .replace("\r", "")
                        .replace("\t", "")
                        .replace(" ", "")
                        .trim()
                        .equals("")) {
                    String tmpcursos = bam.getCursoCursado() + bdm.getNome() + ",";
                    bam.setCursoCursado(tmpcursos);
                    System.out.println("%%%%%\tO Aluno de Matricula=" + bam.getMatricula() + " e Nome=" + bam.getNome() + " atende aos Pré-Requisitos, Registrado na Disciplina com sucesso!");
                } else {
                    System.out.println("%%%%%\tO Aluno de Matricula=" + bam.getMatricula() + " e Nome=" + bam.getNome() + " não atende aos Pré-Requisitos\nPré-Requisitos que tem que ser atendidos:\n");
                    System.out.println(PreRequisitos.replace(",", "\n"));
                }
            }
        } catch (Exception e) {
            System.out.println("\tDados invalidos!");
        }
    }

    public void AdicionaAluno() {
        try {
            boolean cnd = false;
            System.out.println("\n\n\t%%%%% Cadastrar Aluno\n\n");
            for (; !cnd;) {
                Integer matricula = Integer.parseInt(con.readLine("\tInsira o numero da matricula do aluno: "));
                String name = con.readLine("\tInsira o nome do aluno: ");
                cnd = !cnd;
                Aluno obj = new Aluno(matricula, name, "");

                if (!((AlunosPorNome.pesquisar(obj) == null) || (AlunosPorMatricula.pesquisar(obj) == null))) {
                    System.out.println("\tNão foi possivel registrar pois o registro do Aluno já existe!");
                } else {
                    AlunosPorNome.adicionar(obj);
                    AlunosPorMatricula.adicionar(obj);
                    System.out.println("\tCadastrado com sucesso!");
                }
            }
        } catch (Exception e) {
            System.out.println("\tDados invalidos!");
        }
    }

    public void AdicionaDisciplina() {
        try {
            boolean cnd = false;
            System.out.println("\n\n\t%%%%% Cadastrar Disciplina\n\n");
            for (; !cnd;) {

                Integer matricula = Integer.parseInt(con.readLine("\tInsira o numero da matricula da Disciplina: "));
                String name = con.readLine("\tInsira o nome do Disciplina: ");
                Integer ch = Integer.parseInt(con.readLine("\tInsira a carga horária da Disciplina: "));

                cnd = !cnd;

                Disciplina obj = new Disciplina(matricula, name, "", "", "", "", ch);

                if (!((DisciplinasPorNome.pesquisar(obj) == null) || (DisciplinasPorMatricula.pesquisar(obj) == null))) {
                    System.out.println("\tNão foi possivel registrar pois o registro da disciplina já existe!");
                } else {
                    DisciplinasPorNome.adicionar(obj);
                    DisciplinasPorMatricula.adicionar(obj);
                    System.out.println("\tCadastrado com sucesso!");
                }

            }
        } catch (Exception e) {
            System.out.println("\tDados invalidos!");
        }
    }

    public void BuscaAluno(int organizacao) {
        try {

            switch (organizacao) {
                case 0:
                    System.out.println("\n\n\t%%%%% Consultar Aluno por Nome\n\n");

                    String name = con.readLine("\tInsira o nome do aluno a ser buscado: ");
                    var ban = AlunosPorNome.buscaElemento(new Aluno(0, name, ""));

                    if (ban == null) {
                        System.out.println("\tAluno não encontrado!\n\n");
                    } else {
                        System.out.println("\tAluno encontrado!\n\n");
                        System.out.println("\tMatricula: " + (ban.getMatricula()));
                        System.out.println("\tNome: " + (ban.getNome()));
                        System.out.println("\tCursos Cursados: \n\n");

                        String listaCursos = ban.getCursoCursado();

                        if (listaCursos != null && !(listaCursos.trim().replace(",", "").replace(" ", "").equals(""))) {
                            System.out.println("\tn/a");
                            String[] obterCursos = listaCursos.split(",");

                            String cursosCursados = "";

                            for (int i = 0; i < obterCursos.length; i++) {
                                String nomecurso = obterCursos[i];
                                var cursoObt = DisciplinasPorNome.pesquisar(new Disciplina(0, nomecurso, "", "", "", "", 0));
                                cursosCursados += "\n\t[" + "Disciplina; " + "Matricula=" + cursoObt.getMatricula() + "; Nome=" + cursoObt.getNome() + "; CargaHoraria=" + cursoObt.getCargaHoraria() + "]";
                            }
                            System.out.println(cursosCursados);
                        }
                    }
                    break;
                case 1:
                    System.out.println("\n\n\t%%%%% Consultar Aluno por Matricula\n\n");
                    Integer matricula = Integer.parseInt(con.readLine("\tInsira o numero da matricula do aluno a ser buscado: "));
                    var bam = AlunosPorMatricula.buscaElemento(new Aluno(matricula, "", ""));

                    if (bam == null) {
                        System.out.println("\tAluno não encontrado!\n\n");
                    } else {
                        System.out.println("\tAluno encontrado!\n\n");

                        System.out.println("\tMatricula: " + (bam.getMatricula()));
                        System.out.println("\tNome: " + (bam.getNome()));
                        System.out.println("\tCursos Cursados: \n\n");

                        String listaCursos = bam.getCursoCursado();
                        if (listaCursos != null && !(listaCursos.trim().replace(",", "").replace(" ", "").equals(""))) {
                            System.out.println("\tn/a");
                            String[] obterCursos = listaCursos.split(",");

                            String cursosCursados = "";

                            for (int i = 0; i < obterCursos.length; i++) {
                                String nomecurso = obterCursos[i];
                                var cursoObt = DisciplinasPorNome.pesquisar(new Disciplina(0, nomecurso, "", "", "", "", 0));
                                cursosCursados += "\n\t[" + "Disciplina; " + "Matricula=" + cursoObt.getMatricula() + "; Nome=" + cursoObt.getNome() + "; CargaHoraria=" + cursoObt.getCargaHoraria() + "]";
                            }
                            System.out.println(cursosCursados);
                        }
                    }
                    break;
            }
            System.out.println("\tBuscado com sucesso!");
        } catch (Exception e) {
            System.out.println("\tDados invalidos!");
        }
    }
    public void BuscaDisciplina(int organizacao) {
        try {
            switch (organizacao) {
                case 0:
                    System.out.println("\n\n\t%%%%% Consultar Disciplina por Nome\n\n");
                    String nome = con.readLine("\tInsira o nome da Disciplina a ser buscado: ");
                    var bdn = DisciplinasPorNome.buscaElemento(new Disciplina(0, nome, "", "", "", "", 0));
                    if (bdn == null) {
                        System.out.println("\t%%%%% Aluno não encontrado!\n\n");
                    } else {
                        System.out.println("\tAluno encontrado!\n\n");
                        System.out.println("\tMatricula: " + (bdn.getMatricula()));
                        System.out.println("\tNome: " + (bdn.getNome()));
                        System.out.println("\tCarga Horária: " + (bdn.getCargaHoraria()));
                        System.out.println("\tPré-Requisitos: \n\n");

                        String listaPreRequisitos = bdn.getPreRequisito();
                        String[] obterCursos = listaPreRequisitos.split(",");

                        String cursosCursados = "";

                        if ((!(listaPreRequisitos
                                .replaceAll("\\R+", " ")
                                .replace(",", "")
                                .replace("\n", "")
                                .replace("\r", "")
                                .replace("\t", "")
                                .replace(" ", "")
                                .trim()
                                .equals(""))))
                        {
                            for (String nomecurso : obterCursos) {
                                var cursoObt = DisciplinasPorNome.pesquisar(new Disciplina(0, nomecurso, "", "", "", "", 0));
                                cursosCursados += "\n\t[" + "Disciplina; " + "Matricula=" + cursoObt.getMatricula() + "; Nome=" + cursoObt.getNome() + "; CargaHoraria=" + cursoObt.getCargaHoraria() + "; Pré-Requisitos=" + cursoObt.getPreRequisito() + "]";
                            }
                        }
                        System.out.println(cursosCursados);
                    }
                    break;
                case 1:
                    System.out.println("\n\n\t%%%%% Consultar Disciplina por Matricula\n\n");
                    Integer matricula = Integer.parseInt(con.readLine("\tInsira o numero da matricula da Disciplina a ser buscado: "));
                    var bdm = DisciplinasPorMatricula.buscaElemento(new Disciplina(matricula, "", "", "", "", "", 0));

                    if (bdm == null) {
                        System.out.println("\t%%%%% Disciplina não encontrado!\n\n");
                    } else {
                        System.out.println("\tDisciplina encontrado!\n\n");
                        System.out.println("\tMatricula: " + (bdm.getMatricula()));
                        System.out.println("\tNome: " + (bdm.getNome()));
                        System.out.println("\tCarga Horária: " + (bdm.getCargaHoraria()));
                        System.out.println("\tPré-Requisitos: \n\n");

                        String listaPreRequisitos = bdm.getPreRequisito();
                        String[] obterCursos = listaPreRequisitos.split(",");

                        String cursosCursados = "";

                        if ((listaPreRequisitos != null) && !(listaPreRequisitos
                                .replaceAll("\\R+", " ")
                                .replace(",", "")
                                .replace("\n", "")
                                .replace("\r", "")
                                .replace("\t", "")
                                .replace(" ", "")
                                .trim()
                                .equals("")))
                        {
                            for (String nomecurso : obterCursos) {
                                var cursoObt = DisciplinasPorNome.pesquisar(new Disciplina(0, nomecurso, "", "", "", "", 0));
                                cursosCursados += "\n\t[" + "Disciplina; " + "Matricula=" + cursoObt.getMatricula() + "; Nome=" + cursoObt.getNome() + "; CargaHoraria=" + cursoObt.getCargaHoraria() + "; Pré-Requisitos=" + cursoObt.getPreRequisito() + "]";
                            }
                        }
                        System.out.println(cursosCursados);
                    }
                    break;
            }
            System.out.println("\tBuscado com sucesso!");
        } catch (Exception e) {
            System.out.println("\tDados invalidos!");
        }
    }

    private void RemovaAluno() {
        try {
            System.out.println("\n\n\t%%%%% Remover Aluno por Matricula\n\n");
            Integer matricula = Integer.parseInt(con.readLine("\tInsira o numero da matricula do aluno a ser removido: "));

            if (AlunosPorMatricula.quantidadeNos() < 1)
            {
                System.out.println("\tNão foi removido, para remover deve ter no minimo 1 registro!");
                return;
            }

            Aluno obj = AlunosPorMatricula.remover(new Aluno(matricula, "", ""));
            if (obj != null) {
                System.out.println("\tRemovido com sucesso!");
                //+ obj);
                AlunosPorNome.remover(obj);
            } else {
                System.out.println("\tNão foi possivel remover o Aluno!");
            }
        } catch (Exception e) {
            System.out.println("\tDados invalidos!");
        }
    }

    private void RemovaDisciplina() {
        try {
            System.out.println("\n\n\t%%%%% Remover Disciplina por Matricula\n\n");
            Integer matricula = Integer.parseInt(con.readLine("\tInsira o numero da matricula da disciplina a ser removido: "));

            if (DisciplinasPorMatricula.quantidadeNos() < 1)
            {
                System.out.println("\tNão foi removido, para remover deve ter no minimo 1 registro!: ");
                return;
            }

            Disciplina obj = DisciplinasPorMatricula.remover(new Disciplina(matricula, "", "", "", "", "", 0));

            if (obj != null) {
                System.out.println("\tRemovido com sucesso!");
                        //+ obj);
                DisciplinasPorNome.remover(obj);
            } else {
                System.out.println("\tNão foi possivel remover a Disciplina!");
            }
        } catch (Exception e) {
            System.out.println("\tDados invalidos!");
        }
    }

    public void GeraArquivo() {
        String workingDirectory = System.getProperty("user.dir");
        String arqAluno = "20241tpapetarvalunos.txt";
        String arqDisciplinas = "20241tpapetarvdisciplinas.txt";
        String arqTotalAluno = workingDirectory + File.separator + arqAluno;
        String arqTotalDisciplinas = workingDirectory + File.separator + arqDisciplinas;

        File alunos = new File(arqTotalAluno);
        File disciplinas = new File(arqTotalDisciplinas);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(alunos))) {
            AlunosPorMatricula.geraArquivoOrdem("Aluno", AlunosPorMatricula, bw);
        } catch (IOException e) {
            System.out.println("\tErro ao gravar arquivo: ");
                    //+ e.getMessage());
        }
        System.out.println("\tArquivo '20241tpapetarvalunos.txt' gerado com sucesso em " + arqTotalAluno);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(disciplinas))) {
            DisciplinasPorMatricula.geraArquivoOrdem("Disciplina", DisciplinasPorMatricula, bw);
        } catch (IOException e) {
            System.out.println("\tErro ao gravar arquivo: " );
                    //+ e.getMessage());
        }
        System.out.println("\tArquivo '20241tpapetarvdisciplinas.txt' gerado com sucesso em " + arqTotalDisciplinas);
    }
    public static void LimpaMenu() throws IOException,
            InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }
}