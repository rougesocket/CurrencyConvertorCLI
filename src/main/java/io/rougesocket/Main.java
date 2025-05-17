package io.rougesocket;

import io.rougesocket.service.ConvertorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

            System.out.println("Welcome to Currency Convertor CLI");
            logger.info("Querying API Status.....");
            logger.info("API Status: "+ ConvertorService.getServiceStatus());
            //ConvertorService.printer(new HashMap<>());
            ConvertorService.getConversionRate("INR","USD");
            //ConvertorService.getAllConversionRate("INR");
            //ConvertorService.getConversion();

    }
}