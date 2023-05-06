package nl.inholland.guitarshopapi.controller;

import nl.inholland.guitarshopapi.model.Brand;
import nl.inholland.guitarshopapi.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BrandController.class)
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    private Brand brand;

    @BeforeEach
    void init() {
        brand = new Brand("Fender", "USA");
        brand.setId(1L);
    }


    @Test
    void getAllGuitarsShouldReturnAListOfOne() throws Exception {
        when(brandService.getAllBrands()).thenReturn(
                List.of(brand));

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/brands")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Fender"));
    }

    @Test
    void postGuitarShouldReturn201AndGuitarWithId1() throws Exception {
        when(brandService.addBrand(any(Brand.class))).thenReturn(brand);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/brands")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
}