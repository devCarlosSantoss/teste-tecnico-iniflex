package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Pessoa {
    String nome;
    LocalDate dataDeNascimento;

    public Pessoa(){

    }

    public Pessoa(String name, LocalDate dataDeNascimento) {
        this.nome = name;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(nome, pessoa.nome) && Objects.equals(dataDeNascimento, pessoa.dataDeNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, dataDeNascimento);
    }

    public int getIdade() {
        return LocalDate.now().getYear() - dataDeNascimento.getYear();
    }
    public String getDataNascimentoFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataDeNascimento.format(formatter);
    }
}
