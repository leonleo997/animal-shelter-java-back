package com.shelter.animalback.contract.api.Matcher;

import com.shelter.animalback.domain.Animal;
import org.mockito.ArgumentMatcher;

public class AnimalMatcher implements ArgumentMatcher<Animal> {

    private Animal animal;

    public AnimalMatcher(Animal actualanimal){
        animal=actualanimal;
    }

    @Override
    public boolean matches(Animal argument) {
        return animal.getBreed().equals(argument.getBreed()) && animal.getGender().equals(argument.getGender())
                && animal.isVaccinated()==argument.isVaccinated() && animal.getName().equals(argument.getName());
    }
}
