package utilities;

import com.csvreader.CsvReader;
import products.Book;
import products.Phone;
import products.Product;


import java.io.IOException;

public class ParseCsvToProduct {
    public static Product parse(CsvReader csvReader) throws IOException {
        switch (csvReader.get("Sort"))
        {
            case "Book":
                return new Book(
                        Integer.valueOf(csvReader.get("ID")),
                        csvReader.get("name"),
                        Double.valueOf(csvReader.get("price")),
                        csvReader.get("author"),
                        Long.valueOf(csvReader.get("ISBN")));
            case "Phone":
                return new Phone(
                        Integer.valueOf(csvReader.get("ID")),
                        csvReader.get("name"),
                        Double.valueOf(csvReader.get("price")),
                        csvReader.get("model"),
                        csvReader.get("OS"));
}
        return null;
    }
}
