package com.jxk.myWeb.controller.demoB;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/b")
@ResponseBody
@Api(tags = "测试组a的接口集合",value = "ss",description = "des")
public class DemoBController {

    @PostMapping("/hello1")
    @ApiOperation("b组hello1测试")
    public String hello1(@RequestParam("id") String id){
        return "hello";
    }


}