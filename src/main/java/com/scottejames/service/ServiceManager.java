package com.scottejames.service;

import com.scottejames.model.DecisionModel;

public class ServiceManager {

    private static ServiceManager instance = null;

    public static synchronized ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
            // Create some sample data
            instance.createTestData();
        }
        return instance;
    }

    private final DecisionService decisionService = new DecisionService();

    public DecisionService getDecisionService() {
        return decisionService;
    }
    private void createTestData() {

        DecisionModel decisionOne = new DecisionModel(true,"Do Some Work");
        DecisionModel decisionTwo = new DecisionModel(false, "Not approveed");

        decisionService.add(decisionOne);
        decisionService.add(decisionTwo);

    }
}