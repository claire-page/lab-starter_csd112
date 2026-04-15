package part2;

public record BigMac(
        int year,
        String currency,
        String country,
        double localPrice,
        double exchangeRate,
        double usdPrice) {
}