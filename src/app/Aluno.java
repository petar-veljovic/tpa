package app;

import lib.No;

import java.io.BufferedWriter;

/**
 *
 * @author Petar Veljovic
 * 
 * Essa é a classe Aluno que será utilizada como tipo do conteúdo das árvores nos 
 * programas de teste para redigir os relatórios.
 */

public class Aluno implements Comparable<Aluno> {
    private int matricula;
    private String nome;

    public void setCursoCursado(String cursoCursado) {
        this.cursoCursado = cursoCursado;
    }

    public String getCursoCursado() {
        return cursoCursado;
    }

    private String cursoCursado;

    public Aluno(int matricula, String nome, String cursoCursado){
        this.matricula = matricula;
        this.nome = nome;
        this.cursoCursado = cursoCursado;
    }


    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public int compareTo(Aluno o) {
        return 0;
    }


}
