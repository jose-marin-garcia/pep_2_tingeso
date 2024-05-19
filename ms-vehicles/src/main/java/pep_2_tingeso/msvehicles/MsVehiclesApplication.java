package pep_2_tingeso.msvehicles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsVehiclesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsVehiclesApplication.class, args);
	}

}
