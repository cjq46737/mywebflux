/*
 * @projectName mywebflux
 * @package com.cjq.webflux.mywebflux.service
 * @className com.cjq.webflux.mywebflux.service.AjService
 * @copyright Copyright 2022 Thuisoft, Inc. All rights reserved.
 */
package com.cjq.webflux.mywebflux.service;

import com.cjq.webflux.mywebflux.bean.Aj;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * AjService
 * @description 案件service
 * @author chenjunqi
 * @date 2022年02月28日 9:16 下午
 * @version 3.0.0
 */
@Service
public class AjService {

    private Map<Integer,Aj> ajMap = new HashMap<>();

    public AjService() {
        this.ajMap.put(1,new Aj("张三偷盗","案号1","J00"));
        this.ajMap.put(2,new Aj("李四抢劫","案号2","J10"));
        this.ajMap.put(3,new Aj("王五斗殴","案号3","J20"));
    }

    /**
     * AjService
     * @description 根据id获取案件
     * @param id id
     * @return Mono<Aj>
     * @author chenjunqi
     * @date 2022/3/1 10:21 上午
     * @version 3.0.0
     */
    public Mono<Aj> getAjById(Integer id){
        return Mono.justOrEmpty(this.ajMap.get(id));
    }

    /**
     * AjService
     * @description 获取所有的案件
     * @return Flux<Aj>
     * @author chenjunqi
     * @date 2022/3/1 10:23 上午
     * @version 3.0.0
     */
    public Flux<Aj> getAllAj(){
        return Flux.fromIterable(this.ajMap.values());
    }

    /**
     * AjService
     * @description 新增案件
     * @param monoAj monoAj
     * @return Mono<Void>
     * @author chenjunqi
     * @date 2022/3/1 10:24 上午
     * @version 3.0.0
     */
    public Mono<Void> addAj(Mono<Aj> monoAj){
        // thenEmpty相当于清空流，给出终止信号
        return monoAj.doOnNext(item-> this.ajMap.put(this.ajMap.size() + 1,item)).thenEmpty(Mono.empty());
    }
}
