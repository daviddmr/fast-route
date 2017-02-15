package br.com.trixlog;

import br.com.trixlog.model.Coordinate;
import br.com.trixlog.model.Route;
import br.com.trixlog.model.Stop;
import br.com.trixlog.model.Vehicle;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastRouteApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void contextLoads() {
        Route route = new Route();
        route.setName("Rota do Trabalho");
        route.setDate(Calendar.getInstance().getTimeInMillis());
        route.setVehicle(new Vehicle("Bus"));
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("Banco do Brasil S/A", new Coordinate(-3.733627, -38.505487)));
        stops.add(new Stop("Shopping Patio Dom Luis", new Coordinate(-3.735463, -38.490222)));
        stops.add(new Stop("Devry Fanor", new Coordinate(-3.749219, -38.462868)));
        route.setStops(stops);
        Gson gson = new Gson();
        try {
            mockMvc.perform(post("/route/save")
                    .contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                            MediaType.APPLICATION_JSON.getSubtype(),
                            Charset.forName("utf8")))
                    .content(gson.toJson(route)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
