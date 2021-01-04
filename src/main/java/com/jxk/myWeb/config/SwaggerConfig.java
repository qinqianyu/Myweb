package com.jxk.myWeb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author jiaxiangkai
 * @date 2020/12/21 15:05
 */
@EnableSwagger2WebMvc
@Configuration
public class SwaggerConfig {

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
                .apis(RequestHandlerSelectors.basePackage("com.jxk.myWeb.controller.password"))
                //过滤，any表示接收所有的
                //.paths(PathSelectors.any())
                .build();
    }

   /* @Bean
    public Docket createRestApi4a(Environment environment) {
        Profiles acceptsProfiles = Profiles.of("!prod");
        boolean enableSwagger = environment.acceptsProfiles(acceptsProfiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("A组")
                .enable(enableSwagger)
                .apiInfo(apiInfo())
                .select()
                //扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("cn.les.commons.knif4j.controller"))
                //过滤，ant,表示过滤的路径选择
                .paths(PathSelectors.ant("/api/a/**"))
                .build();
    }*/

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
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
