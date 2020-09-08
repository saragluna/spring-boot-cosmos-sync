package org.example.cosmosdb;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.models.PartitionKey;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/telemetry")
public class TelemetryController {
    private static final AtomicLong COUNTER = new AtomicLong();

    @Autowired
    private TelemetryRepository repository;

    @Autowired
    private CosmosAsyncClient cosmosAsyncClient;

    @Value("${azure.cosmosdb.database}")
    private String database;

    private Telemetry newRandomTelemetry() {
        Random random = ThreadLocalRandom.current();
        Telemetry telemetry = new Telemetry();
        telemetry.setId(UUID.randomUUID().toString());
        telemetry.setMachineId(UUID.randomUUID().toString());
        telemetry.setAmbientPressure(random.nextDouble());
        telemetry.setAmbientTemperature(random.nextDouble());
        telemetry.setPressure(random.nextDouble());
        telemetry.setTemperature(random.nextDouble());
        telemetry.setSpeed(random.nextDouble());
        telemetry.setSpeedDesired(random.nextDouble());
        return  telemetry;
    }

    @PostMapping("/random")
    public ResponseEntity<String> newRandomTelemetryItem() {
        Telemetry telemetry = newRandomTelemetry();
        repository.save(telemetry);
        return ResponseEntity.status(HttpStatus.OK).body(telemetry.getId());
    }

    @PostMapping("/random-sdk")
    public ResponseEntity<String> newRandomTelemetryItemWithSDK() {
        Telemetry telemetry = newRandomTelemetry();
        cosmosAsyncClient.getDatabase(database).getContainer("telemetry").createItem(telemetry).block();
        return ResponseEntity.status(HttpStatus.OK).body(telemetry.getId());
    }

    @PostMapping
    public ResponseEntity<String> newTelemetryItem(@RequestBody Telemetry telemetry) {
        repository.save(telemetry);
        return ResponseEntity.status(HttpStatus.OK).body(telemetry.getId());
    }

    @GetMapping("/{id}")
    public Telemetry getTelemetryItemById(@PathVariable String id) {
        Optional<Telemetry> telemetry = repository.findById(id, new PartitionKey(id));
        return telemetry.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
