package com.shelter.animalback.contract;


import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import com.shelter.animalback.controller.AnimalController;
import com.shelter.animalback.domain.Animal;
import com.shelter.animalback.service.interfaces.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("AnimalShelterBack")
@PactFolder("pacts")
@Slf4j
public class listAnimal {
    @LocalServerPort
    private int port;

    @Mock
    private AnimalService animalService;

    @InjectMocks
    private AnimalController animalController;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        MockitoAnnotations.initMocks(this);
        MockMvcTestTarget target = new MockMvcTestTarget();
        target.setControllers(animalController);
        context.setTarget(target);
    }

//    @BeforeEach// UNCOMMENT THE ANNOTATION TO MAKE IT WORKS
    private void createAnimal() {
        Animal animal = new Animal();
        animal.setName("Yesid");
        animal.setGender("Male");
        animal.setBreed("Bengali");
        animal.setVaccinated(true);
        animal.setVaccines(new String[]{"Leucemia"});
        ArrayList animals = new ArrayList();
        animals.add(animal);

        Mockito.when(animalService.getAll()).thenReturn(animals);
    }

    @State("there are animals")
    public void getAnimals() {
        createAnimal(); //IT SHOULD WORK HERE BUT IT DOES NOT WORK
    }
}
