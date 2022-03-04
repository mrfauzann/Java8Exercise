import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaExercise {
    public static void main(String[] args) {
        List<Integer> number = Arrays.asList(1, 2, 9, 3, 10, 6, 8, 4, 5, 7);

        System.out.println("--- No. 1 ---");
        number.stream().filter(num -> num % 2 == 0)
                .forEach(System.out::println);

        System.out.println("--- No. 2 ---");
        number.stream().map(num -> ((Integer)num).toString())
                .filter(num -> num.startsWith("1"))
                .forEach(System.out::println);

        System.out.println("--- No. 3 ---");
        long sum = number.stream().count();
        System.out.println(sum);

        System.out.println("--- No. 4 ---");
        number.stream().sorted().forEach(System.out::println);

        System.out.println("---- Refactor -----");
        List<Invoice> invoices = new ArrayList<>();
        List<Integer> firstFiveIds = new ArrayList<>();

        Stream<Invoice> oracleAndTrainInvoices = invoices.stream()
                .filter(invoice -> invoice.getCustomer() == Customer.ORACLE)
                .filter(invoice -> invoice.getTitle().contains("Training"));

        Stream<Invoice> compareAmountInvoice = oracleAndTrainInvoices
                .sorted(Comparator.comparingDouble(Invoice::getAmount));

        Stream<Integer> ids = compareAmountInvoice.map(Invoice::getId);

        firstFiveIds = (List<Integer>) invoices.stream()
                .filter(invoice -> invoice.getCustomer() == Customer.ORACLE)
                .filter(invoice -> invoice.getTitle().contains("Training"))
                .sorted(Comparator.comparingDouble(Invoice::getAmount))
                .map(Invoice::getId)
                .limit(5)
                .collect(Collectors.toList());
    }
}
