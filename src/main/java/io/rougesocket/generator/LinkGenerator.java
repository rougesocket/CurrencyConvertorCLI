package io.rougesocket.generator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LinkGenerator {
    private static final String BASE_URL;
    private static final String API_KEY;
    private static final String CURRENCY_ENDPOINT;
    private static final String STATUS_ENDPOINT;

    static {
        try(InputStream is = new FileInputStream("src/main/resources/config.properties")){
            Properties props = new Properties();
            props.load(is);
            BASE_URL=props.getProperty("BASE_URL");
            API_KEY=props.getProperty("API_KEY");
            CURRENCY_ENDPOINT=props.getProperty("CONVERSION_ENDPOINT");
            STATUS_ENDPOINT=props.getProperty("STATUS_ENDPOINT");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBaseCurrencyUrl(String currency){

        return BASE_URL+CURRENCY_ENDPOINT+API_KEY+"&base_currency="+currency;
    }

    public static String getBasePlusTargetUrl(String base,String target){
        return getBaseCurrencyUrl(base)+"&currencies="+target;
    }

    public static String getStatusEndpoint(){
        return BASE_URL+STATUS_ENDPOINT+API_KEY;
    }
}
