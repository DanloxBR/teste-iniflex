package br.com.iniflex;

import br.com.iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat FORMATO_NUMERO = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();
//Inserir todos os funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("1919.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
//Remover o funcionário João.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase("João"));
// Imprimir todos os funcionários
        System.out.println("==============================================");
        System.out.println("FUNCIONÁRIOS");
        System.out.println("==============================================");

        imprimirFuncionarios(funcionarios);
//aumento de 10%.
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(new BigDecimal("0.10"));
            funcionario.setSalario(salarioAtual.add(aumento));
        });

        System.out.println();
        System.out.println("==============================================");
        System.out.println("FUNCIONÁRIOS APÓS AUMENTO DE 10%");
        System.out.println("==============================================");

        imprimirFuncionarios(funcionarios);

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors
                .groupingBy(Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));
//Agrupar os funcionários por função.
        System.out.println();
        System.out.println("==============================================");
        System.out.println("FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO");
        System.out.println("==============================================");

        funcionariosPorFuncao.forEach((funcao, lista) -> {

            System.out.println();
            System.out.println("Função: " + funcao);

            lista.forEach(funcionario -> System.out.println("Nome: " + funcionario.getNome() + " | Salário: " + formatarNumero(funcionario.getSalario())));
        });
//Imprimir funcionários que fazem
        System.out.println();
        System.out.println("==============================================");
        System.out.println("ANIVERSARIANTES DOS MESES 10 E 12");
        System.out.println("==============================================");

        funcionarios.stream().filter(funcionario -> {

                    int mes = funcionario.getDataNascimento().getMonthValue();

                    return mes == 10 || mes == 12;
                })
                .forEach(funcionario -> System.out.println(
                        funcionario.getNome() + " - " + formatarData(funcionario.getDataNascimento())));
//Imprimir o funcionário com maior idade.
        System.out.println();
        System.out.println("==============================================");
        System.out.println("FUNCIONÁRIO COM MAIOR IDADE");
        System.out.println("==============================================");

        Funcionario maisVelho = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).orElse(null);

        if (maisVelho != null) {

            int idade = calcularIdade(maisVelho.getDataNascimento());

            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade + " anos");
        }
//Imprimir lista por ordem alfabética.
        System.out.println();
        System.out.println("==============================================");
        System.out.println("FUNCIONÁRIOS EM ORDEM ALFABÉTICA");
        System.out.println("==============================================");

        funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(funcionario -> System.out.println(funcionario.getNome()));
//Imprimir o total dos salários.
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println();
        System.out.println("==============================================");
        System.out.println("TOTAL DOS SALÁRIOS");
        System.out.println("==============================================");

        System.out.println("Total: R$ " + formatarNumero(totalSalarios));
//Imprimir quantos salários mínimos ganha cada funcionário.
        System.out.println();
        System.out.println("==============================================");
        System.out.println("SALÁRIOS MÍNIMOS POR FUNCIONÁRIO");
        System.out.println("==============================================");

        funcionarios.forEach(funcionario -> {

            BigDecimal quantidade = funcionario
                    .getSalario()
                    .divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);

            System.out.println(funcionario.getNome()
                            + " ganha aproximadamente "
                            + quantidade
                            + " salário(s) mínimo(s).");
        });
    }
//imprimir os funcionários.
    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {

        funcionarios.forEach(funcionario -> {

            System.out.println("Nome: " + funcionario.getNome()
                            + " | Data de nascimento: "
                            + formatarData(funcionario.getDataNascimento())
                            + " | Salário: R$ "
                            + formatarNumero(funcionario.getSalario())
                            + " | Função: "
                            + funcionario.getFuncao());
        });
    }
//Formata LocalDate para dd/MM/yyyy.
    private static String formatarData(LocalDate data) {
        return data.format(FORMATO_DATA);
    }
//Formata números no padrão br: 1.234,56
    private static String formatarNumero(BigDecimal valor) {

        FORMATO_NUMERO.setMinimumFractionDigits(2);
        FORMATO_NUMERO.setMaximumFractionDigits(2);

        return FORMATO_NUMERO.format(valor);
    }
//Calcula a idade atual.
    private static int calcularIdade(LocalDate dataNascimento) {

        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}