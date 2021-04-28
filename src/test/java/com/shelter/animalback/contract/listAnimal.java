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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
@Provider("AnimalShelterBack")
@PactFolder("pacts")
public class listAnimal {

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
        MockMvcTestTarget testTarget = new MockMvcTestTarget();
        testTarget.setControllers(animalController);
        context.setTarget(testTarget);
    }

    @State("has animals")
    public void thereIsOneAnimal() {
        ArrayList animals = singleAnimalList();
        Mockito.when(animalService.getAll()).thenReturn(animals);
    }

    @State("there are no animals")
    public void noAnimals() {
        ArrayList animals = new ArrayList();
        Mockito.when(animalService.getAll()).thenReturn(animals);

    }

    private ArrayList singleAnimalList() {
        Animal animal = new Animal();
        animal.setName("Yesid");
        animal.setGender("Male");
        animal.setBreed("Bengali");
        animal.setVaccinated(false);

        ArrayList animals = new ArrayList();
        animals.add(animal);
        return animals;
    }
}
