package countries;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

public class Homework1 {

    private List<Country> countries;

    public Homework1() {
        countries = new CountryRepository().getAll();
    }

    /**
     * Returns whether there is at least one country with the word "island" in its name ignoring case.
     */
    public boolean streamPipeline1() {

        return countries.stream().anyMatch(Country -> Country.getName().toLowerCase().contains("island"));
    }

    /**
     *  Returns the first country name that contains the word "island" ignoring case.
     */
    public Optional<String> streamPipeline2() {
        return
                countries.stream()
                        .filter(Country -> Country.getName().toLowerCase().contains("island"))
                        .findFirst()
                        .map(Country -> Country.getName());
    }

    /**
     * Prints each country name in which the first and the last letters are the same ignoring case.
     */
    public void streamPipeline3() {
        countries.stream()
                .filter(Country -> Country.getName().toLowerCase().charAt(0) == Country.getName().charAt(Country.getName().length()-1))
                .map(Country -> Country.getName())
                .forEach(System.out::println);
    }

    /**
     * Prints the populations of the first ten least populous countries.
     */
    public void streamPipeline4() {
        countries.stream()
                .mapToLong(Country -> Country.getPopulation())
                .sorted()
                .limit(10)
                .forEach(System.out::println);

    }

    /**
     * Prints the names of the first ten least populous countries.
     */
    public void streamPipeline5() {
        countries.stream()
                .sorted(Comparator.comparing(Country -> Country.getPopulation()))
                .limit(10)
                .map(Country -> Country.getName())
                .forEach(System.out::println);

    }

    /**
     * Returns summary statistics about the number of country name translations associated with each country.
     */
    public IntSummaryStatistics streamPipeline6() {
        return countries.stream()
                .mapToInt(Country -> Country.getTranslations().size())
                .summaryStatistics();
    }

    /**
     * Prints the names of countries in the ascending order of the number of timezones.
     */
    public void streamPipeline7() {
        countries.stream()
                .sorted(Comparator.comparingInt(Country -> Country.getTimezones().size()))
                .map(Country -> Country.getName())
                .forEach(System.out::println);

    }

    /**
     * Prints the number of timezones for each country in the form {@code name:timezones}, in the ascending order of the number of timezones.
     */
    public void streamPipeline8() {
        countries.stream()
                .sorted(Comparator.comparingInt(Country -> Country.getTimezones().size()))
                .forEach(Country -> System.out.printf("%s: %d\n", Country.getName(), Country.getTimezones().size()));

    }

    /**
     * Returns the number of countries with no Spanish country name translation (the Spanish language is identifi
     ed by the language code "es").
     */
    public long streamPipeline9() {
        return countries.stream()
                .filter(Country -> !Country.getTranslations().containsKey("es"))
                .count();
    }

    /**
     * Prints the names of countries with null area.
     */
    public void streamPipeline10() {
        countries.stream()
                .filter(Country -> Country.getArea() == null)
                .map(Country ->Country.getName())
                .forEach(System.out::println);

    }

    /**
     * Prints all distinct language tags of country name translations sorted in alphabetical order.
     */
    public void streamPipeline11() {
        countries.stream()
                .flatMap(Country -> Country.getTranslations().keySet().stream())
                .distinct()
                .sorted()
                .forEach(System.out::println);

    }

    /**
     * Returns the average length of country names.
     */
    public double streamPipeline12() {

        return countries.stream()
                .mapToInt(Country -> Country.getName().length())
                .average()
                .getAsDouble();
    }

    /**
     * Prints all distinct regions of the countries with null area.
     */
    public void streamPipeline13() {
        countries.stream()
                .filter(Country -> Country.getArea() == null)
                .map(Country -> Country.getRegion())
                .distinct()
                .forEach(System.out::println);

    }

    /**
     * Returns the largest country with ncdc
     public Optional<Country> streamPipeline14() {
     return countries.stream()
     .filter(Country-> Country.getArea() != null)
     .max(Comparator.comparing(Country->Country.getArea()))
     .get();
     }
     /**
     * Prints the names of countries with a non-null area below 1.
     */
    public void streamPipeline15() {
        countries.stream()
                .filter(Country -> Country.getArea() != null && Country.getArea().compareTo(new BigDecimal(1)) < 1)
                .map(Country -> Country.getName())
                .forEach(System.out::println);

    }

    /**
     * Prints all distinct timezones of European and Asian countries.
     */
    public void streamPipeline16() {
        countries.stream()
                .filter(Country -> Country.getRegion() == Region.EUROPE || Country.getRegion() == Region.ASIA)
                .flatMap(Country -> Country.getTimezones().stream())
                .distinct()
                .forEach(System.out::println);

    }

}