package app;

/**
 *
 * @author victoriocarvalho
 * 
 * Essa é a classe Aluno que será utilizada como tipo do conteúdo das árvores nos 
 * programas de teste para redigir os relatórios.
 */

public class Aluno implements Comparable<Aluno> {
    private int matricula;
    private String nome;

    public Aluno(int matricula, String nome){
        this.matricula = matricula;
        this.nome = nome;        
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
