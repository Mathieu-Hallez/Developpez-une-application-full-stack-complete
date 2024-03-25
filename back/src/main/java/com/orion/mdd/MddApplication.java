package com.orion.mdd;

import com.orion.mdd.configurations.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class MddApplication {

	public static void main(String[] args) {
		SpringApplication.run(MddApplication.class, args);
	}

}
