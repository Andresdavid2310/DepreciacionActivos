package com.technicaltest.TechnicalTest.config;

import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SwaggerConfigTest {

    @Test
    public void testApiDocketCreation() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        Docket apiDocket = swaggerConfig.api();

        assertNotNull(apiDocket);
    }
}
