/*
 * @projectName mywebflux
 * @package com.cjq.webflux.mywebflux.handler
 * @className com.cjq.webflux.mywebflux.handler.AjHandler
 * @copyright Copyright 2022 Thuisoft, Inc. All rights reserved.
 */
package com.cjq.webflux.mywebflux.handler;

import com.cjq.webflux.mywebflux.bean.Aj;
import com.cjq.webflux.mywebflux.service.AjService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * AjHandler
 * @description webflux的aj的handler
 * @author chenjunqi
 * @date 2022年03月02日 7:51 下午
 * @version 3.0.0
 */
public class AjHandler {

    private AjService ajService;

    public AjHandler(AjService ajService) {
        this.ajService = ajService;
    }

    /**
     * AjHandler
     * @description 根据id查询aj
     * @param serverRequest 请求
     * @return Mono<ServerResponse>
     * @author chenjunqi
     * @date 2022/3/2 8:00 下午
     * @version 3.0.0
     */
    public Mono<ServerResponse> getAjById(ServerRequest serverRequest){

        // 获取路径参数id
        Integer id =Integer.valueOf(serverRequest.pathVariable("id"));
        // 控制处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        // 查询案件
        Mono<Aj> aj = ajService.getAjById(id);
        return aj.flatMap(item->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(item))).switchIfEmpty(notFound);
    }

    /**
     * AjHandler
     * @description 获取所有的aj
     * @return Mono<ServerResponse>
     * @author chenjunqi
     * @date 2022/3/2 8:03 下午
     * @version 3.0.0
     */
    public Mono<ServerResponse> getAllAj(ServerRequest serverRequest){
        Flux<Aj> allAj = ajService.getAllAj();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(allAj,Aj.class);
    }

    /**
     * AjHandler
     * @description 保存aj
     * @param serverRequest 请求
     * @return Mono<ServerResponse>
     * @author chenjunqi
     * @date 2022/3/2 8:05 下午
     * @version 3.0.0
     */
    public Mono<ServerResponse> saveAj(ServerRequest serverRequest){
        Mono<Aj> ajMono = serverRequest.bodyToMono(Aj.class);
        return ServerResponse.ok().build(ajService.addAj(ajMono));
    }
}
