import entities.Funcionario;
import entities.Pessoa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static List<Funcionario> funcionarios = new ArrayList<>();

    public static Map<String, List<Funcionario>> listMap = new TreeMap<>();

    public static String[][] listFuncionarios = {
            {"Maria","18/10/2000","2009.44","Operador"},
            {"João","12/05/1990","2284.38","Operador"},
            {"Caio","02/05/1961","9836.14","Coordenador"},
            {"Miguel","14/10/1988","19119.88","Diretor"},
            {"Alice","05/01/1995","2234.68","Recepcionista"},
            {"Heitor","19/11/1999","1582.72","Operador"},
            {"Arthur","31/03/1993","4071.84","Contador"},
            {"Laura","08/07/1994","3017.45","Gerente"},
            {"Heloísa","24/05/2003","1606.85","Eletricista"},
            {"Helena","02/09/1996","2799.93","Gerente"}
    };
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        char controle;

        do {
            System.out.println("---------- Escolha uma das opções ----------");
            System.out.println("""
                    1- Inserir todos os funcionarios na mesma ordem;
                    2- Imprimir todos os funcionarios;
                    3- Aumentar salário de funcionários em 10%;
                    4- Imprimir funcionarios por funcao;
                    5- Imprimir os funcionários que fazem aniversário no mês 10 e 12;
                    6- Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade;
                    7- Imprimir a lista de funcionários por ordem alfabética;
                    8- Imprimir o total dos salários dos funcionários;
                    9- Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00;
                    0- SAIR;
                    """);
            System.out.print("Escolha: ");
            controle = sc.next().charAt(0);
            switch (controle) {
                case '1' -> inserirFuncionarios();
                case '2' -> imprimirFuncionarios();
                case '3' -> aumentarSalario();
                case '4' -> imprimirFuncionariosPorFuncao();
                case '5' -> imprimirAniversariantes();
                case '6' -> imprimirFuncionarioMaiorIdade();
                case '7' -> imprimirFuncionariosOrdemAlfabetica();
                case '8' -> imprimirTotalSalarios();
                case '9' -> imprimirSalariosMinimos();
                case '0' -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (controle != '0');
    sc.close();
    }

    public static void inserirFuncionarios() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (String[] func : listFuncionarios) {
            funcionarios.add(new Funcionario(func[0], LocalDate.parse(func[1], formatter), new BigDecimal(func[2]), func[3]));
        }
        System.out.println("Funcionários inseridos com sucesso.");
    }
    public static void imprimirFuncionarios() {
        System.out.printf("%-20s%-25s%-20s%-20s\n", "Nome", "Data de Nascimento", "Salário", "Função");
        for (Funcionario func : funcionarios) {
            System.out.printf("%-20s%-25sR$%-18s%-20s\n", func.getNome(), func.getDataNascimentoFormatada(), func.getSalarioFormatado(), func.getFuncao());
        }
    }

    public static void aumentarSalario() {
        for (Funcionario func : funcionarios) {
            func.setSalario(func.getSalario().multiply(BigDecimal.valueOf(1.1)).setScale(2, RoundingMode.HALF_UP));
        }
        System.out.println("Salários aumentados em 10%.");
    }

    public static void imprimirFuncionariosPorFuncao() {
        listMap = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
        for (Map.Entry<String, List<Funcionario>> entry : listMap.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            entry.getValue().forEach(System.out::println);
        }
    }

    public static void imprimirAniversariantes() {
        funcionarios.stream()
                .filter(func -> func.getDataDeNascimento().getMonthValue() == 10 || func.getDataDeNascimento().getMonthValue() == 12)
                .forEach(System.out::println);
    }

    public static void imprimirFuncionarioMaiorIdade() {
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparingInt(Funcionario::getIdade));
        System.out.println("Funcionário com maior idade: " + maisVelho.getNome() + ", " + maisVelho.getIdade() + " anos");
    }

    public static void imprimirFuncionariosOrdemAlfabetica() {
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Pessoa::getNome));
        funcionariosOrdenados.forEach(System.out::println);
    }

    public static void imprimirTotalSalarios() {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        DecimalFormat df = new DecimalFormat("###,##0.00");
        System.out.println("Total dos salários: R$" + df.format(totalSalarios).replace(".", ",").replaceFirst(",", "."));
    }

    public static void imprimirSalariosMinimos() {
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        for (Funcionario func : funcionarios) {
            BigDecimal quantidadeSalariosMinimos = func.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(func.getNome() + ": " + df.format(quantidadeSalariosMinimos).replace(".", ",").replaceFirst(",", "."));
        }
    }
}