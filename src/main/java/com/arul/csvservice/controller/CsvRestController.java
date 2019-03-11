package com.arul.csvservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.FileReader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.arul.csvservice.util.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author Arul J
* This controller implements a REST endpoint to parse CSV Files
*/
@RestController
@Api(value = "Csv parse service", description = "parser")
public class CsvRestController {

@Autowired
CSVParser parser;

Logger logger = LoggerFactory.getLogger(CsvRestController.class);

/**
 * Runs parse by each line of uploaded file. The post will invoke CsvParser in util package to parse by line 
 *
 * @param file The csv file.
 * @return The text with [] surrounded column values.
 */
	@ApiOperation("Reads the file by line and uses CSVParser util to get [] surrounded columns")
	@RequestMapping(value = "/parse", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> parse(@RequestParam("file") MultipartFile file) {

		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();

		try{
			reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line = reader.readLine();
			logger.debug("Reading line:"+line);

			while(line!=null){
			
				String parsedLine = parser.parseLine(line);
				logger.debug("Parsed line:"+parsedLine);
				buffer.append(parsedLine+System.lineSeparator());
				line = reader.readLine();
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>("File parsing error", HttpStatus.INTERNAL_SERVER_ERROR);
		}finally{
			try{
				reader.close();
			}catch(Exception i){
				i.printStackTrace();
				logger.error("Exception when closing reader object");
				return new ResponseEntity<>("File parsing error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}	

		return new ResponseEntity<>(buffer.toString(), HttpStatus.OK);
		

	}

}
