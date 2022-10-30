package org.fsq.security;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestProperties {

    @Autowired
    private AppProperties appProperties;

    private static Logger LOG = LoggerFactory.getLogger(TestProperties.class);

    @Test
    public void testPropsLoaded() {
        assertNotNull(appProperties);
        assertNotNull(appProperties.getAuth());
    }


}
