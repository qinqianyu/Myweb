package com.jxk.myWeb.controller.password;

import com.jxk.myWeb.core.RandomPSWd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/password")
@ResponseBody
@Api(tags = "密码接口集合")
public class PasswordController {

    @Autowired
    RandomPSWd randomPSWd;

    @GetMapping("/commons")
    @ApiOperation("混合密码")
    public String commons(@RequestParam("length") int length) {
        return randomPSWd.GernaratePWD(RandomPSWd.PasswordType.Mixed, length);
    }
    @GetMapping("/noSymbol")
    @ApiOperation("非符号密码")
    public String noSymbol(@RequestParam("length") int length) {
        return randomPSWd.noSymbol(length);
    }

}
