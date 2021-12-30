package org.but.feec.eshop.service;

import org.but.feec.eshop.api.*;
import org.but.feec.eshop.data.PersonRepository;

import java.util.List;

public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDetailView getPersonDetailView(Long id) {
        return personRepository.findPersonDetailedView(id);
    }

    public List<PersonBasicView> getPersonsBasicView() {
        return personRepository.getPersonsBasicView();
    }

    public void createPerson(PersonCreateView personCreateView) {
        personRepository.createPerson(personCreateView);
    }

    public void editPerson(PersonEditView personEditView) {
        personRepository.editPerson(personEditView);
    }

    public void deletePerson(PersonDeleteView personDeleteView) {
        personRepository.deletePerson(personDeleteView);
    }

}
