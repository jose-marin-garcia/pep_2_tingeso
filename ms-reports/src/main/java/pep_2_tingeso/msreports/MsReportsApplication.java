package pep_2_tingeso.msreports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReportsApplication.class, args);
	}

}
