package entities;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;

public class Funcionario extends Pessoa{
    private BigDecimal salario;
    private String funcao;

    public Funcionario(){

    }

    public Funcionario(String name, LocalDate dataDeNascimento, BigDecimal salario, String funcao) {
        super(name, dataDeNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(salario, that.salario) && Objects.equals(funcao, that.funcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salario, funcao);
    }

    public String getSalarioFormatado() {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(salario).replace(".", ",").replaceFirst(",", ".");
    }

    @Override
    public String toString() {
        return getNome() + ", " +
                getSalarioFormatado() + ", " +
                getSalarioFormatado() + ", " +
                getFuncao();
    }
}
