package nl.inholland.guitarshopapi.filter;


import jakarta.servlet.ServletException;
import nl.inholland.guitarshopapi.LargeRequestFilter;
import nl.inholland.guitarshopapi.configuration.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

class FilterTest {

    @Value("${application.max.content.size}")
    private int maxSize;

    @Test
    void testLargeRequestFilter() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContent(TestHelper.returnStringOfSize(maxSize).getBytes());
        LargeRequestFilter filter = new LargeRequestFilter();
        MockFilterChain chain = new MockFilterChain();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Exception exception = Assertions.assertThrows(ServletException.class,
                () -> filter.doFilter(request, response, chain));
        Assertions.assertEquals("Request too large", exception.getMessage());
    }
}
