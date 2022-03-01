/*
 * @projectName mywebflux
 * @package com.cjq.webflux.mywebflux.controller
 * @className com.cjq.webflux.mywebflux.controller.AjController
 * @copyright Copyright 2022 Thuisoft, Inc. All rights reserved.
 */
package com.cjq.webflux.mywebflux.controller;

import com.cjq.webflux.mywebflux.bean.Aj;
import com.cjq.webflux.mywebflux.service.AjService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * AjController
 * @description 案件controller
 * @author chenjunqi
 * @date 2022年03月01日 10:27 上午
 * @version 3.0.0
 */
@RestController
@RequestMapping("/cjq")
public class AjController {

    @Resource
    private AjService ajService;

    /**
     * AjController
     * @description 根据id获取案件
     * @param id id
     * @return Mono<Aj>
     * @author chenjunqi
     * @date 2022/3/1 10:31 上午
     * @version 3.0.0
     */
    @GetMapping("/aj/{id}")
    @ResponseBody
    public Mono<Aj> getAjById(@PathVariable("id") Integer id){
        return ajService.getAjById(id);
    }

    /**
     * AjController
     * @description 获取所有案件
     * @return Flux<Aj>
     * @author chenjunqi
     * @date 2022/3/1 10:56 上午
     * @version 3.0.0
     */
    @GetMapping("/allAj")
    public Flux<Aj> getAllAj(){
        return ajService.getAllAj();
    }

    /**
     * AjController
     * @description 保存案件信息
     * @param aj 案件
     * @return Mono<Void>
     * @author chenjunqi
     * @date 2022/3/1 10:59 上午
     * @version 3.0.0
     */
    @PostMapping("/aj")
    public Mono<Void> saveAj(@RequestBody Aj aj){
        return ajService.addAj(Mono.just(aj));
    }
}
