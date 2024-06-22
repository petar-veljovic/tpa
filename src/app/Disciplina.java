package app;
/**
 *
 * @author Petar Veljovic
 *
 * Essa é a classe Aluno que será utilizada como tipo do conteúdo das árvores nos
 * programas de teste para redigir os relatórios.
 */


public class Disciplina implements Comparable<Disciplina>
{
    private Integer matricula;
    private String nome;
    private String preRequisito;
    private String turno;
    private String ano;
    private String semestre;
    private int cargaHoraria;

    public Disciplina(int matricula, String nome, String preRequisito, String turno, String ano, String semestre, int cargaHoraria) {
        this.matricula = matricula;
        this.nome = nome;
        this.preRequisito = preRequisito;
        this.turno = turno;
        this.ano = ano;
        this.semestre = semestre;
        this.cargaHoraria = cargaHoraria;
    }
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
// Getters

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getPreRequisito() {
        return preRequisito;
    }

    public String getTurno() {
        return turno;
    }

    public String getAno() {
        return ano;
    }

    public String getSemestre() {
        return semestre;
    }


    // Setters

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreRequisito(String preRequisito) {
        this.preRequisito = preRequisito;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }






    @Override
    public int compareTo(Disciplina o) {
        return 0;
    }



}
