package com.demo.websocket;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;

public class HTTPClientDemo {


    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpClient client = vertx.createHttpClient();

//        client.request(HttpMethod.GET,"https://127.0.0.1:8090/" ,response -> {
//            System.out.println("Received response with status code " + response);
//        });
        client.request(HttpMethod.GET, 3000, "localhost", "/").onSuccess(b->{
            System.out.println("success==="+b.getHost()+" URI==="+b.getURI()+" Port===="+b.getPort()) ;
        }).onFailure(Throwable::printStackTrace);
//                .compose(request -> {
//                            request.setChunked(true);
//                            for (int i = 0; i < 10; i++) {
//                                request.write("client-chunk-" + i);
//                            }
//                            request.end();
//                            return request.response().compose(resp -> {
//                                System.out.println("Got response " + resp.statusCode());
//                                return resp.body();
//                            });
//                        }
//                )
//                .onSuccess(body -> System.out.println("Got data " + body.toString("ISO-8859-1")))
//                .onFailure(Throwable::printStackTrace);
    }

//        client.request(HttpMethod.GET, "some-uri", response -> {
//            System.out.println("Received response with status code " + response.statusCode());
//        }).end();
//        client.request(response -> {
//            System.out.println("Received response with status code " + response.statusCode());
//        });

        // Send a GET request
//        client.headNow("/other-uri", response -> {
//            System.out.println("Received response with status code " + response.statusCode());
//        });
//    }
}
