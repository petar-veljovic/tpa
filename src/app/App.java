package app;

import lib.*;
import java.io.*;

/**
 *
 * @author Petar Veljovic
 *
 */

public class App < T > {
    private ArvoreBinaria < Aluno > AlunosPorNome;
    private ArvoreBinaria < Aluno > AlunosPorMatricula;
    private ArvoreBinaria < Disciplina > DisciplinasPorNome;
    private ArvoreBinaria < Disciplina > DisciplinasPorMatricula;
    public Console con;

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
        System.out.println("\n%\n" +
                "\n\t\tBem-vindo! ao ");
        System.out.println("\n\n" +
                "\t\t  /$$$$$$                         /$$                      /$$                                   /$$                        /$$      \n" +
                "\t\t /$$__  $$                       | $$                     |__/                                  | $$                       | $$      \n" +
                "\t\t| $$  \\ $$ /$$$$$$$ /$$$$$$  /$$$$$$$ /$$$$$$ /$$$$$$/$$$$ /$$ /$$$$$$$ /$$$$$$                 | $$$$$$$  /$$$$$$  /$$$$$$| $$$$$$$ \n" +
                "\t\t| $$$$$$$$/$$_____/|____  $$/$$__  $$/$$__  $| $$_  $$_  $| $$/$$_____//$$__  $$                | $$__  $$|____  $$/$$_____| $$__  $$\n" +
                "\t\t| $$__  $| $$       /$$$$$$| $$  | $| $$$$$$$| $$ \\ $$ \\ $| $| $$     | $$  \\ $$                | $$  \\ $$ /$$$$$$|  $$$$$$| $$  \\ $$\n" +
                "\t\t| $$  | $| $$      /$$__  $| $$  | $| $$_____| $$ | $$ | $| $| $$     | $$  | $$                | $$  | $$/$$__  $$\\____  $| $$  | $$\n" +
                "\t\t| $$  | $|  $$$$$$|  $$$$$$|  $$$$$$|  $$$$$$| $$ | $$ | $| $|  $$$$$$|  $$$$$$/       /$$      | $$$$$$$|  $$$$$$$/$$$$$$$| $$  | $$\n" +
                "\t\t|__/  |__/\\_______/\\_______/\\_______/\\_______|__/ |__/ |__|__/\\_______/\\______/       |__/      |_______/ \\_______|_______/|__/  |__/" +
                "\n\n%\n\n");
    }

    public void MostraOpcao() {
        System.gc();
        System.out.println("" +
                "%%% Menu de opções" +
                "\n\n" +
                "[1]\tCadastrar Aluno\n" +
                "\t\t[Sobre]\tCria o registro de um aluno;\n" +
                "[2]\tCadastrar Disciplina\n" +
                "\t\t[Sobre]\tCria o registro de uma disciplina;\n" +
                "[3]\tInformar Pré-Requisito\n" +
                "\t\t[Sobre]\tDadas duas disciplinas previamente cadastradas,\n" +
                "\t\t\tregistra que a primeira é pré-requisito da segunda.\n" +
                "[4]\tInformar Disciplina cursada\n" +
                "\t\t[Sobre]\tDado um aluno e uma disciplina previamente cadastrados,\n" +
                "\t\t\tverifica se o aluno cursou todos os pré-requisitos da disciplina dada e,\n" +
                "\t\t\tcaso positivo, registra que o aluno cursou tal disciplina.\n" +
                "\t\t\tCaso contrário exibe uma mensagem informando as disciplinas não cursadas.\n" +
                "[5]\tConsultar Aluno por Nome\n" +
                "\t\t[Sobre]\tDado o nome do aluno imprime todos os dados do mesmo,\n" +
                "\t\t\tinclusive as disciplinas já cursadas (código e nome de cada disciplina cursada).\n" +
                "[6]\tConsultar Aluno por Matrícula\n" +
                "\t\t[Sobre]\tDada a matrícula do aluno imprime todos os dados do mesmo.\n" +
                "\t\t\tinclusive as disciplinas já cursadas (código e nome de cada disciplina cursada).\n" +
                "[7]\tConsultar Disciplina por Nome\n" +
                "\t\t[Sobre]\tDado o nome do Disciplina imprime todos os dados do mesmo\n" +
                "[8]\tConsultar Disciplina por Matrícula\n" +
                "\t\t[Sobre]\tDada a matrícula da disciplina imprime todos os dados do mesmo.\n" +
                "[9]\tExcluir Aluno por Matrícula\n" +
                "\t\t[Sobre]\tDada a matrícula, exclui o aluno.\n" +
                "[10]\tExcluir Disciplina por Matrícula\n" +
                "\t\t[Sobre]\tDada a matrícula, exclui a disciplina.\n" +
                "\n" +
                ". . .\n" +
                "\n" +
                "[0]\tSair\n" +
                "\n"
        );
    }

    public void OpcaoEscolha(int option) {

        switch (option) {
            case 0:
                //end = "1";
                GeraArquivo();
                System.out.println("Terminando o App!");
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
                System.out.println("Opção Selecionada Invalida!");
                break;

        }
    }
    public void PreRequisitoDisciplina() {
        try {
            boolean condition = false;
            System.out.println("\n\n%%%%% Inserir Pré-Requisito na Disciplina\n\n");
            for (; !condition;) {
                Integer matricula = Integer.parseInt(con.readLine("Insira o numero da matricula da disciplina: "));
                var discObtida = DisciplinasPorMatricula.pesquisar(new Disciplina(matricula, "", "", "", "", "", 0));

                if (discObtida == null) {
                    System.out.println("Disciplina não encontrada!\n\n");
                } else {
                    condition = !condition;
                    System.out.println("Disciplina encontrada!\n\n");
                    String PreRequisito = con.readLine("Insira os nomes dos cursos Pre-Requisitos da disciplina separados por ,: ");
                    DisciplinasPorMatricula.pesquisar(new Disciplina(matricula, "", "", "", "", "", 0))
                            .setPreRequisito(
                                    PreRequisito
                                            .trim()
                                            .replace("  ", " ")
                                            .replace(" ,", ",").replace(", ", ",")
                            );
                    System.out.println("\nRegistrado com sucesso!\n\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Dados invalidos!");
        }
    }
    public void InformaDisciplinaCursada() {
        try {
            boolean condition = false;
            System.out.println("\n\n%%%%% Registrar Aluno na Disciplina\n\n");
            for (; !condition;) {
                Integer matriculaaluno = Integer.parseInt(con.readLine("Insira o numero da matricula do aluno: "));
                Integer matriculadiscip = Integer.parseInt(con.readLine("Insira o numero da matricula da disciplina: "));
                condition = !condition;
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
                    System.out.println("%%%%% O Aluno de Matricula=" + bam.getMatricula() + " e Nome=" + bam.getNome() + " atende aos Pré-Requisitos, Registrado na Disciplina com sucesso!");
                } else {
                    System.out.println("%%%%% O Aluno de Matricula=" + bam.getMatricula() + " e Nome=" + bam.getNome() + " não atende aos Pré-Requisitos\nPré-Requisitos que tem que ser atendidos:\n");
                    System.out.println(PreRequisitos.replace(",", "\n"));
                }
            }
        } catch (Exception e) {
            System.out.println("Dados invalidos!");
        }
    }

    public void AdicionaAluno() {
        try {
            boolean condition = false;
            System.out.println("\n\n%%%%% Cadastrar Aluno\n\n");
            for (; !condition;) {
                Integer matricula = Integer.parseInt(con.readLine("Insira o numero da matricula do aluno: "));
                String name = con.readLine("Insira o nome do aluno: ");
                condition = !condition;
                Aluno obj = new Aluno(matricula, name, "");

                if (!((AlunosPorNome.pesquisar(obj) == null) || (AlunosPorMatricula.pesquisar(obj) == null))) {
                    System.out.println("Não foi possivel registrar pois o registro do Aluno já existe!");
                } else {
                    AlunosPorNome.adicionar(obj);
                    AlunosPorMatricula.adicionar(obj);
                }
            }
        } catch (Exception e) {
            System.out.println("Dados invalidos!");
        }
    }

    public void AdicionaDisciplina() {
        try {
            boolean condition = false;
            System.out.println("\n\n%%%%% Cadastrar Disciplina\n\n");
            for (; !condition;) {

                Integer matricula = Integer.parseInt(con.readLine("Insira o numero da matricula da Disciplina: "));
                String name = con.readLine("Insira o nome do Disciplina: ");
                Integer ch = Integer.parseInt(con.readLine("Insira a carga horária da Disciplina: "));

                condition = !condition;

                Disciplina obj = new Disciplina(matricula, name, "", "", "", "", ch);

                if (!((DisciplinasPorNome.pesquisar(obj) == null) || (DisciplinasPorMatricula.pesquisar(obj) == null))) {
                    System.out.println("Não foi possivel registrar pois o registro da disciplina já existe!");
                } else {
                    DisciplinasPorNome.adicionar(obj);
                    DisciplinasPorMatricula.adicionar(obj);
                }

            }
        } catch (Exception e) {
            System.out.println("Dados invalidos!");
        }
    }

    public void BuscaAluno(int organizacao) {
        try {

            switch (organizacao) {
                case 0:
                    System.out.println("\n\n%%%%% Consultar Aluno por Nome\n\n");

                    String name = con.readLine("Insira o nome do aluno a ser buscado: ");
                    var ban = AlunosPorNome.buscaElemento(new Aluno(0, name, ""));

                    if (ban == null) {
                        System.out.println("Aluno não encontrado!\n\n");
                    } else {
                        System.out.println("Aluno encontrado!\n\n");
                        System.out.println("Matricula: " + (ban.getMatricula()));
                        System.out.println("Nome: " + (ban.getNome()));
                        System.out.println("Cursos Cursados: \n\n");

                        String listaCursos = ban.getCursoCursado();

                        if (listaCursos != null && !(listaCursos.trim().replace(",", "").replace(" ", "").equals(""))) {
                            System.out.println("n/a");
                            String[] obterCursos = listaCursos.split(",");

                            String cursosCursados = "";

                            for (int i = 0; i < obterCursos.length; i++) {
                                String nomecurso = obterCursos[i];
                                var cursoObt = DisciplinasPorNome.pesquisar(new Disciplina(0, nomecurso, "", "", "", "", 0));
                                cursosCursados += "\n[" + "Disciplina; " + "Matricula=" + cursoObt.getMatricula() + "; Nome=" + cursoObt.getNome() + "; CargaHoraria=" + cursoObt.getCargaHoraria() + "]";
                            }
                            System.out.println(cursosCursados);
                        }
                    }
                    break;
                case 1:
                    System.out.println("\n\n%%%%% Consultar Aluno por Matricula\n\n");
                    Integer matricula = Integer.parseInt(con.readLine("Insira o numero da matricula do aluno a ser buscado: "));
                    var bam = AlunosPorMatricula.buscaElemento(new Aluno(matricula, "", ""));

                    if (bam == null) {
                        System.out.println("Aluno não encontrado!\n\n");
                    } else {
                        System.out.println("Aluno encontrado!\n\n");

                        System.out.println("Matricula: " + (bam.getMatricula()));
                        System.out.println("Nome: " + (bam.getNome()));
                        System.out.println("Cursos Cursados: \n\n");

                        String listaCursos = bam.getCursoCursado();
                        if (listaCursos != null && !(listaCursos.trim().replace(",", "").replace(" ", "").equals(""))) {
                            System.out.println("n/a");
                            String[] obterCursos = listaCursos.split(",");

                            String cursosCursados = "";

                            for (int i = 0; i < obterCursos.length; i++) {
                                String nomecurso = obterCursos[i];
                                var cursoObt = DisciplinasPorNome.pesquisar(new Disciplina(0, nomecurso, "", "", "", "", 0));
                                cursosCursados += "\n[" + "Disciplina; " + "Matricula=" + cursoObt.getMatricula() + "; Nome=" + cursoObt.getNome() + "; CargaHoraria=" + cursoObt.getCargaHoraria() + "]";
                            }
                            System.out.println(cursosCursados);
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Dados invalidos!");
        }
    }
    public void BuscaDisciplina(int organizacao) {
        try {
            switch (organizacao) {
                case 0:
                    System.out.println("\n\n%%%%% Consultar Disciplina por Nome\n\n");
                    String nome = con.readLine("Insira o nome da Disciplina a ser buscado: ");
                    var bdn = DisciplinasPorNome.buscaElemento(new Disciplina(0, nome, "", "", "", "", 0));
                    if (bdn == null) {
                        System.out.println("%%%%% Aluno não encontrado!\n\n");
                    } else {
                        System.out.println("Aluno encontrado!\n\n");
                        System.out.println("Matricula: " + (bdn.getMatricula()));
                        System.out.println("Nome: " + (bdn.getNome()));
                        System.out.println("Carga Horária: " + (bdn.getCargaHoraria()));
                        System.out.println("Pré-Requisitos: \n\n");

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
                                cursosCursados += "\n[" + "Disciplina; " + "Matricula=" + cursoObt.getMatricula() + "; Nome=" + cursoObt.getNome() + "; CargaHoraria=" + cursoObt.getCargaHoraria() + "; Pré-Requisitos=" + cursoObt.getPreRequisito() + "]";
                            }
                        }
                        System.out.println(cursosCursados);
                    }
                    break;
                case 1:
                    System.out.println("\n\n%%%%% Consultar Disciplina por Matricula\n\n");
                    Integer matricula = Integer.parseInt(con.readLine("Insira o numero da matricula da Disciplina a ser buscado: "));
                    var bdm = DisciplinasPorMatricula.buscaElemento(new Disciplina(matricula, "", "", "", "", "", 0));

                    if (bdm == null) {
                        System.out.println("%%%%% Disciplina não encontrado!\n\n");
                    } else {
                        System.out.println("Disciplina encontrado!\n\n");
                        System.out.println("Matricula: " + (bdm.getMatricula()));
                        System.out.println("Nome: " + (bdm.getNome()));
                        System.out.println("Carga Horária: " + (bdm.getCargaHoraria()));
                        System.out.println("Pré-Requisitos: \n\n");

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
                                cursosCursados += "\n[" + "Disciplina; " + "Matricula=" + cursoObt.getMatricula() + "; Nome=" + cursoObt.getNome() + "; CargaHoraria=" + cursoObt.getCargaHoraria() + "; Pré-Requisitos=" + cursoObt.getPreRequisito() + "]";
                            }
                        }
                        System.out.println(cursosCursados);
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Dados invalidos!");
        }
    }

    public void RemovaAluno() {
        try {
            System.out.println("\n\n%%%%% Remover Aluno por Matricula\n\n");
            Integer matricula = Integer.parseInt(con.readLine("Insira o numero da matricula do aluno a ser removido: "));
            Aluno obj = AlunosPorMatricula.remover(new Aluno(matricula, "", ""));
            if (obj != null) {
                System.out.println("Removido com sucesso!: " + obj);
                AlunosPorNome.remover(obj);
            } else {
                System.out.println("Não foi possivel remover o Aluno!");
            }
        } catch (Exception e) {
            System.out.println("Dados invalidos!");
        }
    }

    public void RemovaDisciplina() {
        try {
            System.out.println("\n\n%%%%% Remover Disciplina por Matricula\n\n");
            Integer matricula = Integer.parseInt(con.readLine("Insira o numero da matricula da disciplina a ser removido: "));
            Disciplina obj = DisciplinasPorMatricula.remover(new Disciplina(matricula, "", "", "", "", "", 0));
            if (obj != null) {
                System.out.println("Removido com sucesso!: " + obj);
                DisciplinasPorNome.remover(obj);
            } else {
                System.out.println("Não foi possivel remover a Disciplina!");
            }
        } catch (Exception e) {
            System.out.println("Dados invalidos!");
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
            System.out.println("Erro ao gravar arquivo: " + e.getMessage());
        }
        System.out.println("Arquivo '20241tpapetarvalunos.txt' gerado com sucesso em " + arqTotalAluno);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(disciplinas))) {
            DisciplinasPorMatricula.geraArquivoOrdem("Disciplina", DisciplinasPorMatricula, bw);
        } catch (IOException e) {
            System.out.println("Erro ao gravar arquivo: " + e.getMessage());
        }
        System.out.println("Arquivo '20241tpapetarvdisciplinas.txt' gerado com sucesso em " + arqTotalDisciplinas);
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