package com.arul.csvservice.config;
import org.springframework.context.annotation.*;
import com.arul.csvservice.util.CSVParser;
import com.arul.csvservice.util.CSVParserImpl;

@Configuration
public class CSVBeansConfig {
   @Bean 
   public CSVParser cSVParser(){
      return new CSVParserImpl();
   }
}