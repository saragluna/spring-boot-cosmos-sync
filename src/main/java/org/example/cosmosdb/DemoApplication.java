package org.example.cosmosdb;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCosmosRepositories
public class DemoApplication extends AbstractCosmosConfiguration {
	@Value("${azure.cosmosdb.uri}")
	private String databaseUri;

	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	@Value("${azure.cosmosdb.key}")
	private String databaseKey;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CosmosClientBuilder cosmosClientBuilder() {
		return new CosmosClientBuilder().directMode().endpoint(databaseUri).key(databaseKey);
	}

	@Override
	protected String getDatabaseName() {
		return databaseName;
	}
}
