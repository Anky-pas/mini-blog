package ankypas.miniblog.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //是否开启swagger，一般生产环境开启，正式环境关闭
    @Value(value = "${swagger.enabled}")
    boolean swaggerEnabled;

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("API接口文档")
                .description("")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                //是否开启
                .enable(swaggerEnabled).select()
                //扫描的路径包（API所在的包）
                .apis(RequestHandlerSelectors.basePackage("ankypas.miniblog.Api"))
                //指定路径处理 PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }
}
