package org.example.cosmosdb;

import java.util.List;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRepository extends CosmosRepository<Telemetry, String> {
    public List<Telemetry> findByMachineId(String machineId);
}
