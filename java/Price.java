import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;

import com.datastax.oss.driver.api.core.cql.ColumnDefinition;
import com.datastax.oss.driver.api.core.cql.ColumnDefinitions;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import com.datastax.oss.driver.api.core.CqlSession;

public class Price {
    public String InstrumentName;
    public String Date;
    public String Currency;
    public String AVGPrice;
    public String NetAmountPerDay;

    public static void Show(Price obj) {
        System.out.printf(obj.InstrumentName);
        System.out.printf(",");
        System.out.printf(obj.Date);
        System.out.printf(",");
        System.out.printf(obj.Currency);
        System.out.printf(",");
        System.out.printf(obj.AVGPrice);
        System.out.printf(",");
        System.out.printf(obj.NetAmountPerDay);
        System.out.println();
    }

    public static List<Price> ParsePriceCsv(String filePath) throws IOException {
        //Загружаем строки из файла
        List<Price> pricelist = new ArrayList<Price>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            String[] LineText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < LineText.length; i++) {
                //Если колонка начинается на кавычки или заканчиваеться на кавычки

                columnList.add(LineText[i]);


            }
            Price prices = new Price();
            prices.InstrumentName = columnList.get(0);
            //System.out.println(product.TransactionID);
            prices.Date = columnList.get(1);
            //System.out.println(product.ExecutionEntityName);
            prices.Currency = columnList.get(2);
            //System.out.println(product.InstrumentName);
            prices.AVGPrice = columnList.get(3);
            //System.out.println(product.Quantity);
            prices.NetAmountPerDay = columnList.get(4);
            //System.out.println(product.Price);

            pricelist.add(prices);
        }
        return pricelist;
    }

    public static String InsertPriceTable(Price obj, String name_table) {
        return "INSERT INTO " + name_table +
                " (TransactionID, ExecutionEntityName, InstrumentName, InstrumentClass, Quantity, Price, Currency, Datestamp, NetAmount)" +
                " VALUES (" + obj.InstrumentName + ", '" + obj.Date + "'" +
                ", '" + obj.Currency + "', '" + obj.AVGPrice +
                "', " + obj.NetAmountPerDay + ");";
    }

//    public static List<Price> Select_All_From_Price(CqlSession session, String NAME_TABLE) {
//        ResultSet result_select_price = session.execute("SELECT * FROM " + NAME_TABLE);
//        ColumnDefinitions col_price = result_select_price.getColumnDefinitions();
//        List<Price> read_price_list = new ArrayList<>();
//
//        for (Row row : result_select_price) {
//            int index = 0;
//            Price read_Price = new Price();
//            for (ColumnDefinition col : col_price) {
//                CqlIdentifier name = col.getName();
//
//            }
//            return read_price_list;
//        }
//    }
}
