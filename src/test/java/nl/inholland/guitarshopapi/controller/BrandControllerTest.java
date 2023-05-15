package nl.inholland.guitarshopapi.controller;

import nl.inholland.guitarshopapi.model.Brand;
import nl.inholland.guitarshopapi.service.BrandService;
import nl.inholland.guitarshopapi.util.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@WebMvcTest(BrandController.class)
class BrandControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BrandService brandService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Value("${application.max.content.size}")
    private int maxSize;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void callingBrandControllerShouldReturnListOfOneBrand() throws Exception {
        Mockito.when(brandService.getAllBrands())
                .thenReturn(List.of(
                        new Brand("Fender", "USA"
                        )
                ));

        mvc.perform(MockMvcRequestBuilders.get("/brands"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
