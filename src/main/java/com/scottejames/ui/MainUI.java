package com.scottejames.ui;

import com.scottejames.model.DecisionModel;
import com.scottejames.service.DecisionService;
import com.scottejames.service.ServiceManager;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("")
public class MainUI extends VerticalLayout {

    private final DecisionService service = ServiceManager.getInstance().getDecisionService();
    private final Grid<DecisionModel> descisionGrid = new Grid<>();

    public MainUI() {

        descisionGrid.addColumn(DecisionModel::getDescription).setHeader("Description");
        descisionGrid.addColumn(DecisionModel::isApproval).setHeader("Approval");
        descisionGrid.setHeightByRows(true);

        buildUI();


    }

    private void buildUI() {
        removeAll();

        add(descisionGrid);
        updateDescisionGrid();
        setClassName("main-layout");


    }

    private void updateDescisionGrid() {
        descisionGrid.setItems(service.getAll());

    }
}