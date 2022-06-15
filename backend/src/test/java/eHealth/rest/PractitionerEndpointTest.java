package eHealth.rest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ActiveProfiles({"test", "datagen"}) // enable "test" spring profile during test execution in order to pick up configuration from application-test.yml
@SpringBootTest
@EnableWebMvc
@WebAppConfiguration
public class PractitionerEndpointTest {

//    @Autowired
//    private WebApplicationContext webAppContext;
//    private MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
//    }
//
//    @Test
//    public void gettingAllPractitioners() throws Exception {
//        byte[] body = mockMvc
//                .perform(MockMvcRequestBuilders
//                        .get("/practitioners")
//                        .accept(MediaType.APPLICATION_JSON)
//                ).andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsByteArray();
//
//        List<PractitionerDto> practitionerResult = objectMapper.readerFor(PractitionerDto.class).<PractitionerDto>readValues(body).readAll();
//
//        assertThat(practitionerResult).isNotNull();
//        assertThat(practitionerResult.size()).isEqualTo(3);
//        assertThat(practitionerResult.get(0).id()).isEqualTo(-3);
//        assertThat(practitionerResult.get(0).name()).isEqualTo("Dr. Kellner");
//    }
//
//    @Test
//    public void gettingNonexistentUrlReturns404() throws Exception {
//        mockMvc
//                .perform(MockMvcRequestBuilders
//                        .get("/asdf123")
//                ).andExpect(status().isNotFound());
//    }
}
