package com.rthym.trafficanalyzer;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.util.concurrent.TimeUnit;

public class TestMockServer {
    private void createExpectationForIntersection() {
            new MockServerClient("127.0.0.1", 1080)
                    .when(HttpRequest.request().withMethod("GET").withPath("/intersection1/date/05122018")
                                    .withHeader("\"Content-type\", \"application/json\"")
                                    .withBody("[{\n" +
                                            "\"direction\" : \"WestBound\",\n" +
                                            "\"lanes\": [\n" +
                                            "    {\"id\" : 0,\n" +
                                            "     \"phase\" : \"WBT\",\n" +
                                            "     \"vehicles\" : [\n" +
                                            "        {\"id\": 0, \"type\": \"Car\", \"speed\": 80, \"intersectionLeft\": 1537356125}, \n" +
                                            "        {\"id\": 1, \"type\": \"Truck\", \"speed\": 45, \"intersectionLeft\":1537356425}]\n" +
                                            "    },\n" +
                                            "    {\"id\" : 1,\n" +
                                            "     \"phase\" : \"WBL\",\n" +
                                            "     \"vehicles\" : [\n" +
                                            "        {\"id\": 2, \"type\": \"Car\", \"speed\": 80, \"intersectionLeft\": 1537356325}, \n" +
                                            "        {\"id\": 3, \"type\": \"Truck\", \"speed\": 45, \"intersectionLeft\": 1537356325}]\n" +
                                            "    }]\n" +
                                            "},\n" +
                                            "{\n" +
                                            "\"direction\" : \"EastBound\",\n" +
                                            "\"lanes\": [\n" +
                                            "    {\"id\" : 2,\n" +
                                            "     \"phase\" : \"EBT\",\n" +
                                            "     \"vehicles\" : [\n" +
                                            "        {\"id\": 4, \"type\": \"Car\", \"speed\": 80, \"intersectionLeft\": 1537356535}, \n" +
                                            "        {\"id\": 5, \"type\": \"Cycle\", \"speed\": 45, \"intersectionLeft\": 1537356545}]\n" +
                                            "    },\n" +
                                            "    {\"id\" : 3,\n" +
                                            "     \"phase\" : \"EBT\",\n" +
                                            "     \"vehicles\" : [\n" +
                                            "        {\"id\": 6, \"type\": \"Car\", \"speed\": 80, \"intersectionLeft\": 1537356525}, \n" +
                                            "        {\"id\": 7, \"type\": \"Truck\", \"speed\": 45, \"intersectionLeft\": 1537353525}]\n" +
                                            "    }]\n" +
                                            "}]"),
                            Times.exactly(1))
                .respond(HttpResponse.response()
                .withStatusCode(200)
                .withHeaders(
                    new Header("Content-Type", "application/json; charset=utf-8"),
                    new Header("Cache-Control", "public, max-age=86400"))
                .withBody("{ message: 'incorrect username and password combination' }")
                .withDelay(TimeUnit.SECONDS,1)
                );
    }
// ...
}