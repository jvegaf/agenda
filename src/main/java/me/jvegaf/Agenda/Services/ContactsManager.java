package me.jvegaf.Agenda.Services;

import me.jvegaf.Agenda.JDBC.DBRepository;
import me.jvegaf.Agenda.Model.Contact;

import java.util.ArrayList;

public class ContactsManager {

    private DBRepository repository;

    public ContactsManager() {
        this.repository = new DBRepository();
    }

    public ArrayList<Contact> getAll() {
        GetAllContacstUseCase getAllUC = new GetAllContacstUseCase(this.repository);
        return getAllUC.execute();
    }

    public void add(String name, int phone, String address) {
        AddContactUseCase addUC = new AddContactUseCase(this.repository);
        addUC.execute(new Contact(null, name, phone, address));
    }

    public void update(Integer id, String name, int phone, String address){
        UpdateContactUseCase updateUseCase = new UpdateContactUseCase(this.repository);
        updateUseCase.execute(new Contact(id, name, phone, address));
    }

    public Contact find(Integer id) {
        FindContactUseCase findUseCase = new FindContactUseCase(this.repository);
        return findUseCase.execute(id);
    }

    public void remove(Integer id) {
        RemoveContactUseCase removeUseCase = new RemoveContactUseCase(this.repository);
        removeUseCase.execute(id);
    }
}
