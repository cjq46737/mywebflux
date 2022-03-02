/*
 * @projectName mywebflux
 * @package com.cjq.webflux.mywebflux.server
 * @className com.cjq.webflux.mywebflux.server.AjServer
 * @copyright Copyright 2022 Thuisoft, Inc. All rights reserved.
 */
package com.cjq.webflux.mywebflux.server;

import com.cjq.webflux.mywebflux.handler.AjHandler;
import com.cjq.webflux.mywebflux.service.AjService;
import jdk.nashorn.api.scripting.AbstractJSObject;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.*;
import reactor.netty.http.server.HttpServer;

import java.awt.*;
import java.io.IOException;

/**
 * AjServer
 * @description 自己创建的服务器
 * @author chenjunqi
 * @date 2022年03月02日 8:17 下午
 * @version 3.0.0
 */
public class AjServer {

    public static void main(String[] args) throws IOException {
        new AjServer().createReactorServer();
        System.out.println("666");
        System.in.read();
    }

    /**
     * AjServer
     * @description 创建RouterFunction
     * @return RouterFunction<ServerResponse>
     * @author chenjunqi
     * @date 2022/3/2 8:23 下午
     * @version 3.0.0
     */
    public RouterFunction<ServerResponse> routingFunction(){
        AjHandler ajHandler = new AjHandler(new AjService());

        return RouterFunctions.route(RequestPredicates.GET("/user/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                ajHandler::getAjById).andRoute(RequestPredicates.GET("/users").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                ajHandler::getAllAj);
    }

    /**
     * AjServer
     * @description 自己创建服务器
     * @author chenjunqi
     * @date 2022/3/2 8:27 下午
     * @version 3.0.0
     */
    public void createReactorServer(){
        // 创建routerfunction
        RouterFunction<ServerResponse> routerfunction = routingFunction();
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerfunction);
        // 创建reaction适配器
        ReactorHttpHandlerAdapter reactorHttpHandlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);

        // 创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(reactorHttpHandlerAdapter).bindNow();
    }
}
