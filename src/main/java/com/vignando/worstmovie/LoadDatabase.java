package com.vignando.worstmovie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	public static final String CSV_FILE = "src/main/resources/movielist.csv";
	private static final String COMMA_DELIMITER = ";";
	private static final String YES = "yes";

	@Bean
	CommandLineRunner initDatabase(MovieRepository repository) throws IOException {
		log.info("Load CSV file");

        try (Scanner scanner = new Scanner(new File(CSV_FILE));) {
        	scanner.nextLine();
            while (scanner.hasNextLine()) {
            	List<String> record = getRecordFromLine(scanner.nextLine());
            	
            	String year_str = getField(record, 0);
            	String title = getField(record, 1);
            	String studios = getField(record, 2);
            	String producers = getField(record, 3);
            	String winner = getField(record, 4);
            	
            	repository.save(new Movie(
            			Integer.parseInt(year_str), 
            			title, 
            			studios, 
            			producers, 
            			parseWinner(winner)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		return args -> {
			log.info("Load " + repository.findAll().size() + " records in CSV");     
		};
	}

	private Boolean parseWinner(String winner) {
		return winner.trim().equals(YES) ? true : false;
	}

	private String getField(List<String> record, int posix) {
		try {
			return record.get(posix);
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}
	
	private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
