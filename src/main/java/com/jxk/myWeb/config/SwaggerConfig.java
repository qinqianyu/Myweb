package com.jxk.myWeb.config;

import com.jxk.myWeb.constants.EnvironmentConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * @author jiaxiangkai
 * @date 2020/12/21 15:05
 */
@EnableSwagger2
@Configuration
@Profile({"!prod"})
@Slf4j
public class SwaggerConfig {
    /**
     * 服务端口
     */
    @Value("${server.port}")
    private String port;

    /**
     * 环境变量
     */
    private final Environment environment;

    public SwaggerConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * @description 项目启动后, 开发环境会自动打开浏览器
     */
    @EventListener({ApplicationReadyEvent.class})
    public void applicationReadyEvent() {
        //创建目标环境
        Profiles acceptsProfiles = Profiles.of(EnvironmentConfig.DEV);
        //判断是否处于目标环境
        boolean isOpenBrowser = environment.acceptsProfiles(acceptsProfiles);
        if (isOpenBrowser) {
            log.info("应用已经准备就绪 ... 启动浏览器");
            String url = "http://localhost:" + port + "/swagger-ui/index.html";
            //Headless模式是在缺少显示屏、键盘或者鼠标时的系统配置,不是false导致无法弹出指定窗口。
            System.setProperty("java.awt.headless", "false");
            if (Desktop.isDesktopSupported()) {
                // 创建一个URI实例
                URI uri = URI.create(url);
                // 获取当前系统桌面扩展
                Desktop dp = Desktop.getDesktop();
                // 判断系统桌面是否支持要执行的功能
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        // 获取系统默认浏览器打开链接
                        dp.browse(uri);
                    } catch (IOException e) {
                        log.error("打开浏览器失败");
                    }
                } else {
                    log.error("当前桌面不支持浏览器");
                }
            } else {
                log.error("当前系统不支持桌面扩展");
            }
        }
    }

    @Bean
    public Docket createRestApi4password(Environment environment) {
        Profiles acceptsProfiles = Profiles.of("!prod");
        boolean enableSwagger = environment.acceptsProfiles(acceptsProfiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .groupName("密码")
                .apiInfo(apiInfo())
                .select()
                //扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.jxk.myWeb.controller"))
                //过滤，any表示接收所有的
                //.paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("spring样例应用接口")
                //创建人
                //版本号
                .version("1.0")
                //描述
                .description("springboot的样例程序")
                .build();
    }
}
