package com.moksha.todof;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodofApplicationTest {

    @Autowired
    private CommandLineRunner commandLineRunner;

    @Test
    void contextLoads() {
        // This test ensures that the Spring application context loads successfully
    }

    @Test
    void testCommandLineRunnerRuns() throws Exception {
        assertNotNull(commandLineRunner);
        // Optionally simulate run (but this prints to console)
        commandLineRunner.run(new String[]{});
    }
}
