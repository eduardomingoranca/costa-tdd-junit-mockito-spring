package com.brazil.erudio.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;

import java.util.Map;
import java.util.stream.Stream;

import static org.testcontainers.lifecycle.Startables.deepStart;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
class AbstractIntegrationTest {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        // define um container em runtime usando a docker image
        static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.28");

        private static void startContainers() {
            deepStart(Stream.of(mysql)).join();
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of("spring.datasource.url", mysql.getJdbcUrl(),
                    "spring.datasource.username", mysql.getUsername(),
                    "spring.datasource.password", mysql.getPassword());
        }


        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public void initialize(ConfigurableApplicationContext applicationContext) {
            // inicializa o container, obtem as configuracoes de ambiente e configuracoes
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontainers = new MapPropertySource("testcontainers",
                    (Map) createConnectionConfiguration());

            environment.getPropertySources().addFirst(testcontainers);
        }
    }
}
