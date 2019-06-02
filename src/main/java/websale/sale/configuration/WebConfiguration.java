package websale.sale.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import websale.sale.interceptor.LoginInterceptor;

public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loginInterceptor).addPathPatterns("/cart/**");
            }
        };
    }

}
