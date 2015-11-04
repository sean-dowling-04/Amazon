package main;
import com.csvreader.CsvReader;
import products.Product;
import utilities.ParseCsvToProduct;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public enum Cart {
    INSTANCE();
    Cart(){ productList = new ArrayList<>(); 
    }

    private List<Product> productList;
    public Cart add(Product product)
    {
        productList.add(product);
        return this;
    }
    public Cart remove(Product product)
    {
        productList.remove(product);
        return this;
    }

    private static final String savePath = System.getProperty("user.dir");
    public boolean backup()
    {
        try {
            FileWriter fileWriter = new FileWriter(savePath + "//backup.cvs", true);
            for (Product product : productList)
            {
                fileWriter.write(product.getCsvStr() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean recover()
    {
        CsvReader csvReader;
        try {
            csvReader = new CsvReader(savePath + "//backup.cvs", ',', Charset.forName("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        List<Product> productList = new ArrayList<>();
        try {
            csvReader.readHeaders();
            while (csvReader.readRecord()) productList.add(ParseCsvToProduct.parse(csvReader));
        } catch (IOException e) {
            return false;
        }
        csvReader.close();
        return true;
    }

    public List<Product> asList()
    {
        List<Product> result = new ArrayList<>();
        result.addAll(productList);
        return result;
    }
}