import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class Transaction {
    public String TransactionID;
    public String ExecutionEntityName;
    public String InstrumentName;
    public String InstrumentClassification;
    public String Quantity;
    public String Price;
    public String Currency;
    public String Datestamp;
    public String NetAmount;

    public static void Show(Transaction obj){
        System.out.printf(obj.TransactionID);
        System.out.printf(",");
        System.out.printf(obj.ExecutionEntityName);
        System.out.printf(",");
        System.out.printf(obj.InstrumentName);
        System.out.printf(",");
        System.out.printf(obj.InstrumentClassification);
        System.out.printf(",");
        System.out.printf(obj.Quantity);
        System.out.printf(",");
        System.out.printf(obj.Price);
        System.out.printf(",");
        System.out.printf(obj.Currency);
        System.out.printf(",");
        System.out.printf(obj.Datestamp);
        System.out.printf(",");
        System.out.printf(obj.NetAmount);
        System.out.println();
    }
    public static List<Transaction> ParseTransactionCsv(String filePath) throws IOException {
        //Загружаем строки из файла
        List<Transaction> transactions = new ArrayList<Transaction>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            String[] LineText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < LineText.length; i++) {
                //Если колонка начинается на кавычки или заканчиваеться на кавычки

                columnList.add(LineText[i]);


            }
            Transaction transaction = new Transaction();
            transaction.TransactionID = columnList.get(0);
            //System.out.println(product.TransactionID);
            transaction.ExecutionEntityName = columnList.get(1);
            //System.out.println(product.ExecutionEntityName);
            transaction.InstrumentName = columnList.get(2);
            //System.out.println(product.InstrumentName);
            transaction.InstrumentClassification = columnList.get(3);
            //System.out.println(product.Quantity);
            transaction.Quantity = columnList.get(4);
            //System.out.println(product.Price);
            transaction.Price = columnList.get(5);
            transaction.Currency = columnList.get(6);
            transaction.Datestamp = columnList.get(7);
            transaction.NetAmount = columnList.get(8);
            transactions.add(transaction);
        }
        return transactions;
    }
}
