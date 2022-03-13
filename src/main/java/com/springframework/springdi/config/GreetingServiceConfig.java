package com.springframework.springdi.config;

import com.springframework.springdi.datasource.FakeDataSource;
import com.springframework.springdi.repositories.EnglishGreetingRepository;
import com.springframework.springdi.repositories.EnglishGreetingRepositoryImpl;
import com.springframework.springdi.services.*;
import guru.springframework.pets.PetService;
import guru.springframework.pets.PetServiceFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@EnableConfigurationProperties(SpringDiConstructorConfig.class)
@ImportResource("classpath:springdi.xml")
@Configuration
public class GreetingServiceConfig {
    @Bean
    FakeDataSource fakeDataSource(SpringDiConstructorConfig springDiConstructorConfig){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(springDiConstructorConfig.getUsername());
        fakeDataSource.setPassword(springDiConstructorConfig.getPassword());
        fakeDataSource.setJdbcURL(springDiConstructorConfig.getJdbcURL());
        return fakeDataSource;
    }

    @Bean
    PetServiceFactory petServiceFactory(){
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory petServiceFactory){
        return petServiceFactory.getPetService("dog");
    }

    @Profile("cat")
    @Bean
    PetService catPetService(PetServiceFactory petServiceFactory){
       return petServiceFactory.getPetService("cat");
    }


    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService() {
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService() {
        return new SetterInjectedGreetingService();
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18nSpanishGreetingService i18nSpanishGreetingService() {
        return new I18nSpanishGreetingService();
    }
}
