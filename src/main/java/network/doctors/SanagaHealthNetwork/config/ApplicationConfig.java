package network.doctors.SanagaHealthNetwork.config;

//import doctornetwork.sanagahealth.convertors.StringToEnumConvertor;

import network.doctors.SanagaHealthNetwork.convertors.DateConvertor;
import network.doctors.SanagaHealthNetwork.convertors.StringToEnumConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;


@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = "network.doctors.SanagaHealthNetwork")
@EntityScan(basePackages = "network.doctors.SanagaHealthNetwork.entity")
    public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private final MessageSource messageSource;

    public ApplicationConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


//    public ApplicationConfig(MessageSource messageSource) {
//        this.messageSource = messageSource;
//    }

    @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("css/**", "images/**","js/**")
                    .addResourceLocations("classpath:/static/css/", "classpath:/static/js/","classpath:/static/images/"
                    ,"classpath:/resources/js/","classpath:/static/json/","classpath:messages")
                    .resourceChain(true);
        }

        // resolves the jsp files
        @Bean
        public InternalResourceViewResolver jspViewResolver() {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setPrefix("/WEB-INF/jsp/");
            viewResolver.setSuffix(".jsp");
            viewResolver.setViewClass(JstlView.class);
            viewResolver.setOrder(2);
            return viewResolver;
        }

         @Override
         protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConvertor());
        registry.addConverter(new DateConvertor());
//        registry.addConverter(new TimeConvertor());
         }

//         @Bean
//         @Lazy
//         MessageSource messageSource(){
//             ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//             messageSource.setBasename("classpath:/messages");
//             messageSource.setDefaultEncoding("UTF-8");
//             return messageSource;
//         }


         @Bean
         public Validator getValidator() {
            LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
            localValidatorFactoryBean.setValidationMessageSource(messageSource);
            return localValidatorFactoryBean;
         }

        @Override
        protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
            configurer.setDefaultTimeout(5000);
            configurer.setTaskExecutor(mvcTaskExecutor());
        }

        @Bean
        public AsyncTaskExecutor mvcTaskExecutor() {
            ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
            threadPoolTaskExecutor.setThreadNamePrefix("sanaga-thread-");
            return threadPoolTaskExecutor;
        }


        @Bean
        public ThemeResolver themeResolver() {
            CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
            cookieThemeResolver.setCookieName("theme");
            cookieThemeResolver.setDefaultThemeName("client-theme1");
            return cookieThemeResolver;
        }

        public void setMessageSource(){
            ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
            reloadableResourceBundleMessageSource.setBasename("classpath:messages");
        }


        @Bean
        public LocaleResolver localeResolver(){
            CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
            cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
            cookieLocaleResolver.setCookieName("locale");
            return cookieLocaleResolver;
        }

    }

