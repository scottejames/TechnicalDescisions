package com.scottejames.model;

public class DecisionModel extends Model {

    private boolean approval = false;
    private String description = null;

    public DecisionModel(boolean approval, String description) {
        this.approval = approval;
        this.description = description;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproval() {

        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }
}
