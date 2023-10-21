package com.example.aswanna.SendNotificationPack;

public class Data {

    public String projectName;
    public String projectType;

    public Data(String projectName, String projectType) {
        this.projectName = projectName;
        this.projectType = projectType;
    }

    public Data() {
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
}
