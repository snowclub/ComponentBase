package ComponentBase.config;

import ComponentBase.i18n.SerializableResourceBundleMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Created by waiti on 5/2/2016.
 */

@Configuration
@ComponentScan(basePackages = {"ComponentBase"})

public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public LocaleResolver localeResolver(){
        final SessionLocaleResolver ret = new SessionLocaleResolver();
        ret.setDefaultLocale(new Locale("en"));
        return ret;
    }

    @Bean
    public MessageSource messageSource(){
        final SerializableResourceBundleMessageSource ret = new SerializableResourceBundleMessageSource();
        ret.setBasename("classpath:message");
        ret.setDefaultEncoding("UTF-8");
        return ret;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public HandlerInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localChangeInterceptor = new LocaleChangeInterceptor();
        localChangeInterceptor.setParamName("lang");
        return localChangeInterceptor;
    }
    //auth
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/views/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/views/js/").setCachePeriod(31556926);
        registry.addResourceHandler("/bower_components/**").addResourceLocations("/views/bower_components/");
    }

}
