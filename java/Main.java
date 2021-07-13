import com.datastax.oss.driver.api.core.CqlSession;

import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //Основной метод приложения
    public static void main(String[] args) throws IOException {
        //Расположение CSV файлов на ЖД
        String fileTransaction = "C:\\transaction.csv";
        String filePrice = "C:\\csv2.csv";
        //Считывание CSV файлов Через методы ParseTransactionCsv и ParsePriceCsv и запись объектов в List transactions и prices
        List<Transaction> transactions = Transaction.ParseTransactionCsv(fileTransaction);
        List<Price> prices = Price.ParsePriceCsv(filePrice);
        //чтение всех объектов через Transaction.Show и Price.Show (вывод на экран)
        for(int i = 0; i  < transactions.size(); i++) {
            Transaction.Show(transactions.get(i));
        }
        System.out.println("----------------------------");
        for(int i = 0; i  < prices.size(); i++) {
            Price.Show(prices.get(i));
        }
        //создание keyspacename
        CassandraConnector.Connect();
        //создание таблицы TransactionTable
        CreateTransactionTable.CreateCurrent();
        //создание таблицы PriceTable
        CreatePriceTable.CreatePriceTable();
        //Добавление полей в таблицу PriceTable
        try(CqlSession session = new CqlSessionBuilder()
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .withLocalDatacenter("datacenter1")
                .build()) {
            for (int i = 0; i < prices.size(); i++) {
                session.execute(Price.InsertPriceTable(prices.get(i), "transaction_price_table"));
            }
        }
        //Добавление полей в таблицу TransactionTable
        try(CqlSession session = new CqlSessionBuilder()
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .withLocalDatacenter("datacenter1")
                .build()) {
            for(int i = 0; i  < transactions.size(); i++) {
                session.execute(Transaction.InsertTransactionTable(transactions.get(i), "transaction_table"));
            }
        }
        //вывод информации из таблицы transaction_price_table
        try(CqlSession session = new CqlSessionBuilder()
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .withLocalDatacenter("datacenter1")
                .build()) {
            List<Price> priceListFromDB = Price.Select_All_From_Price(session, "transaction_price_table");
            for (Price a: priceListFromDB) {
                System.out.println(a.toString());
            }
        }
    }

}