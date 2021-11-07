package com.example.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.project.ProjectApplication;
import com.first.project.classes_for_requestbody.car_for_edit;
import com.first.project.entities.car;
import com.first.project.entities.parameters;
import com.first.project.repositories.repository_car;
import com.first.project.repositories.repository_parameter;
import com.first.project.services.parameters_services;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ProjectApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.datasource.url=jdbc:h2:mem:test",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
})

class car_ProjectApplicationTests {

    @Autowired
    com.first.project.repositories.repository_car repository_car;

    @Autowired
    MockMvc mockMvc;

@MockBean
com.first.project.services.parameters_services parameters_services;









@Test
public void contextLoads_delete_car()throws Exception{
    Mockito.when(parameters_services.getbyname("seatnumber")).thenReturn((float) 4);

    car c=new car();

    c.setName("www");
    c.setPrice(300);

    repository_car.save(c);


    System.out.println("first count : "+repository_car.count());


    List<car> list_car_from_data=repository_car.findAll();
    for(car t:list_car_from_data) {
        if (t.getPrice()==c.getPrice()) {


            RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/checkdeletecar").flashAttr("car",t);

            MvcResult mvcResult= mockMvc.perform(requestBuilder).andReturn();

        }
    }




    System.out.println("end count : "+repository_car.count());


        }



    @Test
    public void contextLoads_edit_car() throws Exception {


        Mockito.when(parameters_services.getbyname("seatnumber")).thenReturn((float) 4);


        car c=new car();

        c.setName("www");
        c.setPrice(300);



        repository_car.save(c);



        List<car> list_car_from_data=repository_car.findAll();
        for(car t:list_car_from_data) {
            if (t.getPrice()==c.getPrice()) {
                c = t;
            }
        }

        System.out.println("name first : "+c.getName());

        car_for_edit c_e=new car_for_edit();


        c_e.setId(c.getId());
        c_e.setName("qqq");




        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/checkeditcar").flashAttr("car_for_edit",c_e);

        MvcResult mvcResult= mockMvc.perform(requestBuilder).andReturn();



        list_car_from_data=repository_car.findAll();
        for(car t:list_car_from_data) {
            if (t.getPrice()==c.getPrice()) {
                c = t;
            }
        }
        System.out.println("name end : "+c.getName());

        //System.out.println("result : new name = "+repository_car.getOne(car_id).getName());


    }


    @Test
    public void contextLoads_add_car() throws Exception {
       Mockito.when(parameters_services.getbyname("seatnumber")).thenReturn((float) 4);


        car c=new car();

        c.setName("www");
        c.setPrice(300);



        System.out.println("result : first = "+repository_car.count());



        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/checkaddcar").flashAttr("car",c);

        MvcResult mvcResult= mockMvc.perform(requestBuilder).andReturn();


        System.out.println("result : end = "+repository_car.count());
     }


}
