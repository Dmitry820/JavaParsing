import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //Основной метод приложения
    public static void main(String[] args) throws IOException {
        String fileTransaction = "C:\\transaction.csv";
        String filePrice = "C:\\csv2.csv";
        List<Transaction> transactions = Transaction.ParseTransactionCsv(fileTransaction);
        List<Price> prices = Price.ParsePriceCsv(filePrice);

        for(int i = 0; i  < transactions.size(); i++) {
            Transaction.Show(transactions.get(i));
        }
        System.out.println("--------------------------");
        for(int i = 0; i  < prices.size(); i++) {
            Price.Show(prices.get(i));
        }
    }

}