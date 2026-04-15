package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    static void main(String[] args) {
        var bigMacs = loadData();

//2.2.1 - finding BigMac entry for Canada 2022.
        bigMacs.stream()
                .filter(b -> b.country().equals("Canada")&& b.year()==2022); //getting bigmacs w country of canada + year of 2022.

//2.2.2 - making List containing Canadian data.
      var canData = (bigMacs.stream()
              .filter(b -> b.country().equals("Canada"))
              .toList());

//2.2.3 - making List matching country to currency, sorted alphabetically.
      var countryCurrency =   bigMacs.stream()
                .map(b -> b.country() + " : " +b.currency()) //mapping every BigMac entry to a string containing its country and currency
                .distinct() //only unique elements
                .sorted() //default is alphabetical so don't have to mess with it.
                .toList();//listification

//2.2.4 printing last 5 years of data from Canada.
        bigMacs.stream()
                .filter(b -> (b.year() > ( bigMacs.stream() //building the predicate from another stream
                                                .map(BigMac::year)//map every bigmac to their year, which returns a Stream of java.lang.Integer....
                                                .mapToInt(Integer::intValue) //mapping the Integers to integers...
                                                .max() //returns OptionalInt...what even
                                                .getAsInt()-5 //getting the int value of said OptionalInt (aka MOST RECENT YEAR)!, subtracting 5.
                                                                ) && b.country().equals("Canada"))) //aaaaaaaaaaand country is canada.
                                                                                                     .forEach(System.out::println);

// 2.2.5 - printing data for countries with 2022 BigMac price < $2 USD

        bigMacs.stream()
                .filter( b1 -> ( bigMacs.stream()
                                                         .filter(b -> b.usdPrice() < 2 && b.year()==2022) //burger price is less than 2, year is 2022
                                                         .map(b2 -> b2.country()) //map to country
                                                          .distinct()//distinct
                                                          .toList()) //turn it into a list
                                                          .contains(b1.country()) ) //end of predicate, here we're filtering the initial BigMac stream to get the data points whose country is present in the generated list above.
                                                            .forEach(System.out::println);

//2.2.6 -  calculating the average USD price of BigMacs in 2022 over all countries

        Double avgUSD2022=  bigMacs.stream().filter(b -> b.year()==2022)
                                         .map(b -> b.usdPrice()) //get all bigmac prices from this set
                                         .mapToDouble(Double::doubleValue) //turns the Stream<Double> into a DoubleStream
                                          .average()//gets the average of the DoubleStream (as Optional)
                                          .getAsDouble();

    }

    public static List<BigMac> loadData() {
        try(var lines = Files.lines(Path.of("BigMacPrices.csv"))) {
            var list = (lines.map(Main::parseCsvLine).toList());
            return(list);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parse a CSV line into a BigMac record. The expected format is:
     * year,currency,country,localPrice,exchangeRate,usdPrice
     * @param line the CSV line to parse
     * @return a BigMac record containing the data from the CSV line
     */
    public static BigMac parseCsvLine(String line) {
        var values = line.split(",");
        return new BigMac(
                Integer.parseInt(values[0].substring(0,4)),
                values[1],
                values[2],
                Double.parseDouble(values[3]),
                Double.parseDouble(values[4]),
                Double.parseDouble(values[5]));
    }



}
