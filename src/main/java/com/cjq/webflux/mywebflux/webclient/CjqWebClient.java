/*
 * @projectName mywebflux
 * @package com.cjq.webflux.mywebflux.webclient
 * @className com.cjq.webflux.mywebflux.webclient.WebClient
 * @copyright Copyright 2022 Thuisoft, Inc. All rights reserved.
 */
package com.cjq.webflux.mywebflux.webclient;

import com.cjq.webflux.mywebflux.bean.Aj;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * WebClient
 * @description 测试通过webclient调用接口
 * @author chenjunqi
 * @date 2022年03月02日 8:40 下午
 * @version 3.0.0
 */
public class CjqWebClient {

    public static void main(String[] args) {
        WebClient webClient =  WebClient.create("http://127.0.0.1:60412");
//        Aj aj = webClient.get().uri("/user/{id}", 1).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Aj.class).block();
//        System.out.println(aj.getAh() + "-"+ aj.getAjmc() + "-" + aj.getJbfy());

        Flux<Aj> allAj = webClient.get().uri("/users").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Aj.class);
        allAj.map(item->item.getAjmc()).buffer().doOnNext(System.out::println).blockFirst();
    }
}
