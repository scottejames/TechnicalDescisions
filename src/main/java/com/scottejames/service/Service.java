package com.scottejames.service;


import com.scottejames.model.Model;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("SameParameterValue")
public class Service<M extends Model> {
    private int id = 0;

    void setTestUser(String testUser) {
        this.testUser = testUser;
    }

    private String testUser = null;

    private static final String SESSION_USERNAME = "username";

    private final HashMap<String,List<M>> ownedData = new HashMap<>();

    private String getUser(){
        String username;
        if (testUser == null){
            username = (String) VaadinSession.getCurrent().getAttribute(
                    SESSION_USERNAME);
        } else {
            username = testUser;
        }
        ownedData.putIfAbsent(username, new ArrayList<>());
        return username;
    }
    //private List<M> data = new ArrayList<>();
    public Service(){

    }
    public  Service(List<M> data) {
        this.ownedData.put(getUser(), data);
    }

    int getNextId(){
        return ++id;
    }


    public M getById(int id){
        M result = null;
        for (M item: this.ownedData.get(getUser())){
            if (item.getId() == id){
                result = item;
            }
        }
        if (result == null) {
            System.err.println("Did not find item id " + id);
        }
        return result;
    }
    public void reset(){
        for (String userName: ownedData.keySet())
            for (Model m : ownedData.get(userName)){
                m.setId(0);
            }
        HashMap<String,List<M>> ownedData = new HashMap<>();
        id = 0;
    }
    public  List<M> getAll(){

        return ownedData.get(getUser());

    }
    public void add(M model){
        model.setId(this.getNextId());
        ownedData.get(getUser()).add(model);
    }
    public void remove(M model){
        if (model.getId() == 0){
            System.out.println("Unable to remove model without ID");
            System.exit(-1);
        }
        int index = findIndex(model.getId());
        ownedData.get(getUser()).remove(index);

    }
    public  void update(M model){
        if (model.getId() == 0){
            System.out.println("Unable to update model without ID");
            System.exit(-1);
        }
        int index = findIndex(model.getId());
        ownedData.get(getUser()).remove(index);
        ownedData.get(getUser()).add(model);
    }

    private int findIndex(int id){
        int result = 0;
        for (int i = 0; i < ownedData.get(getUser()).size(); i++){
            if (this.ownedData.get(getUser()).get(i).getId() == id){
                result = i;
            }
        }
        return result;
    }

    public int size() {
        return ownedData.get(getUser()).size();
    }

    public void removeById(int i) {
        int index = findIndex(i);
        ownedData.get(getUser()).remove(index);

    }
}
