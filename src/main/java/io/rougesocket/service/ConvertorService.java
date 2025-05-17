package io.rougesocket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.rougesocket.generator.LinkGenerator;
import io.rougesocket.model.CurrencyModel;
import io.rougesocket.model.CurrencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ConvertorService {

    private final static Logger logger = LoggerFactory.getLogger(ConvertorService.class);


    public ConvertorService() {
    }

    public static String getServiceStatus(){
        String url = LinkGenerator.getStatusEndpoint();
        return makeGetRequest(url).isEmpty()? "❌DOWN":"✅UP";
    }
    public static void getAllConversionRate(String code){
        logger.info("Retrieval in progress");
        String url = LinkGenerator.getBaseCurrencyUrl(code);
        Optional<String> resp = makeGetRequest(url);
        if(!resp.isEmpty()){
            Map<String,CurrencyModel> currencyData = parseData(resp.get());
            printer(currencyData);
        }
    }
    public static void getConversionRate(String srcCode,String targetCode){
        logger.info("Retrieval in progress");

        String url = LinkGenerator.getBasePlusTargetUrl(srcCode,targetCode);
        Optional<String> resp = makeGetRequest(url);
        if(!resp.isEmpty()){
            Map<String,CurrencyModel> currencyData = parseData(resp.get());
            printer(currencyData);
        }
        else{
            System.out.println("Please Try again");
        }
    }
    private static Optional<String> makeGetRequest(String url){
        try{
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return Optional.of(response.body());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    private static Map<String,CurrencyModel> parseData(String response){
        Map<String,CurrencyModel> data = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        CurrencyResponse currencyResponse = null;
        try {
            currencyResponse = objectMapper.readValue(response, CurrencyResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return (currencyResponse!=null) ? currencyResponse.getData():data;
    }

    public static  void printer(Map<String,CurrencyModel> data){
        System.out.println("=================================");
        System.out.println("|\tCode\t|\t\tRate\t\t|");
        System.out.println("=================================");
        for (var curr : data.entrySet()) {
            System.out.println("|\t"+curr.getValue().getCode() + "\t\t|\t"
                    + curr.getValue().getValue()+"\t|");
            System.out.println("=================================");
        }
    }

}

