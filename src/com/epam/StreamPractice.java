package com.epam;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice {

    public static void main(String[] args) {
        String[] input = new String[]{"10.00", "20.00", "67.12", "89.56", "12.32"};

        printAverageOfElements(input);
        printMinElement(input);
        printMinElementConvertingToInteger(input);

        List<List<Integer>> numbers = Arrays.asList(
                Arrays.asList(10, 8, 9, 9, 100),
                Arrays.asList(90, 100, 3, 17),
                Arrays.asList(6, 4, 19, 89)
        );

        minUsingFlatMap(numbers);

        distinctUsingFlatMap(numbers);

        String[][] array = new String[][]{{"a", "b", "c"}, {"c", "d"}, {"e", "f"}};

        filterAndPrint(array);

        employeeOperations();

        testSummarizingOperations(numbers);
    }

    private static void testSummarizingOperations(List<List<Integer>> numbers) {
        System.out.println("+++++summary statistics Testing +++++");

        IntSummaryStatistics intSummaryStatistics = numbers.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.summarizingInt(number -> number));

        DoubleSummaryStatistics doubleSummaryStatistics = numbers.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.summarizingDouble(Integer::doubleValue));

        DoubleSummaryStatistics longSummaryStatistics = numbers.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.summarizingDouble(Integer::longValue));

        System.out.println("+++++ Int summary statistics are +++++ " + intSummaryStatistics);
        System.out.println("+++++ Double summary statistics are +++++ " + doubleSummaryStatistics);
        System.out.println("+++++ Long summary statistics are +++++ " + longSummaryStatistics);

        List<BigDecimal> balances = Arrays.asList(BigDecimal.ONE, new BigDecimal("100.23"), BigDecimal.TEN,
                BigDecimal.valueOf(56.00), new BigDecimal("8989.7665"));

        DoubleSummaryStatistics collect = balances.stream()
                .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));
        System.out.println("+++++ Big decimal summary statistics are +++++ " + collect);
    }

    private static void filterAndPrint(String[][] array) {

        List<String> filteredElements = Stream.of(array)
                .flatMap(Stream::of)
                .filter(filterElementPredicate())
                .collect(Collectors.toList());

        System.out.println("Filtered element list is " + filteredElements);
    }

    private static void minUsingFlatMap(List<List<Integer>> numbers) {
        int min = numbers.stream().
                flatMap(Collection::stream)
                .mapToInt(Integer::valueOf)
                .distinct()
                .min()
                .orElse(0);

        System.out.println("Minimum value is " + min);

    }

    private static void distinctUsingFlatMap(List<List<Integer>> numbers) {
        List<Integer> distinctElements = numbers.stream().
                flatMap(Collection::stream)
                .sorted()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Distinct elements using flatmap " + distinctElements);
    }

    private static void printAverageOfElements(String[] input) {
        double average = Stream.of(input)
                .mapToDouble(Double::valueOf)
                .average()
                .orElse(0.00);

        System.out.println("Average values of input array is " + average);
    }

    private static void printMinElement(String[] input) {
        double average = Stream.of(input)
                .mapToDouble(Double::valueOf)
                .min()
                .orElse(0.00);

        System.out.println("Minimum values of input array is " + average);
    }

    private static void printMinElementConvertingToInteger(String[] input) {
        int average = Stream.of(input)
                .mapToDouble(Double::valueOf)
                .mapToInt(value -> (int) value)
                .min()
                .orElse(0);

        System.out.println("Minimum values of after converting input array to integer is " + average);
    }

    private static Predicate<String> filterElementPredicate() {
        return element -> !element.equalsIgnoreCase("a");
    }

    private static void employeeOperations() {
        Employee employee1 = new Employee("abc", "sales", 150000.00);
        Employee employee2 = new Employee("def", "tech", 180000.00);
        Employee employee3 = new Employee("xyz", "support", 80000.00);
        Employee employee4 = new Employee("mno", "tech", 120000.00);

        List<Employee> employees = Arrays.asList(employee1, employee2, employee3, employee4);

        printAverageSalaryOfAllEmployees(employees);
        printEmployeeWithMinimumSalary(employees);
        printEmployeeAvgSalaryByDepartment(employees);

        int[] array = {1, 2, 3, 4, 5, 6};
        Integer[] array1 = {1, 2, 3, 4, 5, 6};

        Stream.of(array).flatMapToInt(Arrays::stream).forEach(System.out::print);
        System.out.println("----------");
        Stream.of(array1).forEach(System.out::print);
    }

    private static void printAverageSalaryOfAllEmployees(List<Employee> employees) {
        double averageSalary = employees.stream()
                .mapToDouble(employee -> employee.salary)
                .average().orElse(0.00);

        System.out.println("Average salary of all employees -> " + averageSalary);
    }

    private static void printEmployeeWithMinimumSalary(List<Employee> employees) {
        List<Employee> employeesSorted = employees.stream()
                .sorted(Comparator.comparing(e -> e.salary))
                //.sorted((e1, e2) -> e1.salary.compareTo(e2.salary))
                .collect(Collectors.toList());

        System.out.println("Employee with minimum salary is -> " + employeesSorted.get(0).name);
    }

    private static void printEmployeeAvgSalaryByDepartment(List<Employee> employees) {
        Map<String, DoubleSummaryStatistics> averageSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.department, Collectors.summarizingDouble(Employee::getSalary)));

        Map<String, Set<Double>> collect = employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.department, Collectors.mapping(Employee::getSalary, Collectors.toSet())));

        System.out.println("Employees average salary by Department -> " + averageSalaryByDept);
    }

    private static class Employee {
        private final String name;
        private final String department;
        private final Double salary;

        private Employee(String name, String deptName, Double salary) {
            this.name = name;
            this.department = deptName;
            this.salary = salary;
        }

        private Double getSalary() {
            return this.salary;
        }
    }

}
