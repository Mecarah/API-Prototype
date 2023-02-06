package com.csc340.restapidemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mecarah Son
 * API Protoype
 * /pokemon gets a Pokemon
 * 02/05/2023
 * I followed  the  UNCG  Academic  Integrity  Policy.
 */
@RestController
public class RestApiController {

    /**
     * Hello World API endpoint.
     *
     * @return response string.
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World";
    }

    /**
     * Get a Pokemon
     * @return 
     */
    @GetMapping("/pokemon")
    public String getPokemon(){
        try{
            String url = "https://pokeapi.co/api/v2/pokemon/";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            
            String jSonPoke = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonPoke);
            
            //Print the whole response to console.
            System.out.println(root);
            
            //Parse out the most important info from the response.
            String pokeName = root.get("name").asText();
            String pokeurl = root.get("url").asText();
        System.out.println("Pokemon: " + pokeName + "\n url:" + pokeurl);
        return String.format("Pokemon: " + pokeName);
    } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /quote";
        }
    }

    /**
     * Get a quote from quotable and it available at this endpoint.
     *
     * @return The quote json response
     */
    @GetMapping("/quote")
    public Object getQuote() {
        try {
            String url = "https://api.quotable.io/random";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            //Print the whole response to console.
            System.out.println(root);

            //Parse out the most important info from the response.
            String quoteAuthor = root.get("author").asText();
            String quoteContent = root.get("content").asText();
            System.out.println("Author: " + quoteAuthor);
            System.out.println("Quote: " + quoteContent);

            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /quote";
        }
    }
}
    

    