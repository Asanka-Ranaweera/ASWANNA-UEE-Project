package com.example.aswanna.Model;

public class Proposal {

    private String PID;

    private String documentID;
    private String farmerID;

    private String projectName;
    private String projectType;
    private String projectLocation;
    private String projectDurationInMonths;
    private String projectDescription;
    private String fundingRequired;
    private String expectedReturnsOnInvestment;
    private String imageOneLink;
    private String imageTwoLink;


    private String Status;

    // Constructors
    public Proposal() {

    }

    public Proposal(String PID, String documentID, String farmerID, String projectName, String projectType, String projectLocation, String projectDurationInMonths, String projectDescription, String fundingRequired, String expectedReturnsOnInvestment, String imageOneLink, String imageTwoLink, String status) {
        this.PID = PID;
        this.documentID = documentID;
        this.farmerID = farmerID;
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectLocation = projectLocation;
        this.projectDurationInMonths = projectDurationInMonths;
        this.projectDescription = projectDescription;
        this.fundingRequired = fundingRequired;
        this.expectedReturnsOnInvestment = expectedReturnsOnInvestment;
        this.imageOneLink = imageOneLink;
        this.imageTwoLink = imageTwoLink;
        Status = status;
    }

    public String getPID() {
        return PID;
    }

    public String getDocumentID() {
        return documentID;
    }

    public String getFarmerID() {
        return farmerID;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getProjectLocation() {
        return projectLocation;
    }

    public String getProjectDurationInMonths() {
        return projectDurationInMonths;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getFundingRequired() {
        return fundingRequired;
    }

    public String getExpectedReturnsOnInvestment() {
        return expectedReturnsOnInvestment;
    }

    public String getImageOneLink() {
        return imageOneLink;
    }

    public String getImageTwoLink() {
        return imageTwoLink;
    }

    public String getStatus() {
        return Status;
    }

    // Getters and setters

}

