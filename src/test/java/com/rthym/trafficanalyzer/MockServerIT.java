package com.rthym.trafficanalyzer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrafficAnalyzerApplication.class)
@WebAppConfiguration
public class MockServerIT {

    private ClientAndServer mockServer;

    @BeforeClass
    public void startServer() {
        mockServer = ClientAndServer.startClientAndServer(1080);
    }

    @AfterClass
    public void stopServer() {
        mockServer.stop();
    }

}
