package com.example.bottomline;

import com.example.bottomline.service.NamesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BottomLineMissionApplication {

	public static void main(String[] args) {

		SpringApplication.run(BottomLineMissionApplication.class, args);
		NamesService service = new NamesService();
		service.insertData();
	}

}
