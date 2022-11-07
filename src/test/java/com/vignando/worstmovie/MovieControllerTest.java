package com.vignando.worstmovie;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest({MovieController.class})
class MovieControllerTest {

	@Autowired
    private MockMvc mockMvc;

	@MockBean
    MovieService movieService;
	
	
	@Test
    public void customer_fetch_in_db_success() throws Exception {

		List<ProducerInterval> producers = new ArrayList<ProducerInterval>();
		producers.add(new ProducerInterval("Producer 1", "min", 2008, 2009, 1));
		producers.add(new ProducerInterval("Producer 1", "max", 1900, 1999, 99));
		producers.add(new ProducerInterval("Producer 2", "min", 2018, 2019, 1));
		producers.add(new ProducerInterval("Producer 2", "max", 2000, 2099, 99));
		
		producers.add(new ProducerInterval("Producer 3", "max", 2010, 2020, 10));

        when(movieService.winMinMax()).thenReturn(producers);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/interval-awards"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().json("{'min':[{}, {}], 'max': [{}, {}, {}]}"));
        
        // {"min":[{"producer":"Producer 1","type":"min","previousWin":2008,"followingWin":2009,"interval":1},{"producer":"Producer 2","type":"min","previousWin":2018,"followingWin":2019,"interval":1}],"max":[{"producer":"Producer 1","type":"max","previousWin":1900,"followingWin":1999,"interval":99},{"producer":"Producer 2","type":"max","previousWin":2000,"followingWin":2099,"interval":99},{"producer":"Producer 3","type":"max","previousWin":2010,"followingWin":2020,"interval":10}]}
    }

}
