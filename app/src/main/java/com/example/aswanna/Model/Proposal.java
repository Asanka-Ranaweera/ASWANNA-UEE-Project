package com.example.aswanna.Model;


import java.io.Serializable;

public class Proposal implements Serializable {

    private String PID;

    private String documentID;
    private String farmerID;

    private String projectName;
    private String projectType;
    private String projectLocation;
    private String projectDurationInMonths;
    private String projectDescription;
    private int fundingRequired;
    private String expectedReturnsOnInvestment;
    private String imageOneLink;
    private String imageTwoLink;

    private String farmerName;
    private String farmerProfileImage;
    private String farmerLevel;
    private String Status;

    // Constructors
    public Proposal() {

    }

    public Proposal(String farmerLevel,String farmerProfileImage,String farmerName,String PID, String documentID, String farmerID, String projectName, String projectType, String projectLocation, String projectDurationInMonths, String projectDescription, int fundingRequired, String expectedReturnsOnInvestment, String imageOneLink, String imageTwoLink, String status) {
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
        this.farmerProfileImage=farmerProfileImage;
        this.farmerName=farmerName;
        this.farmerLevel=farmerLevel;
        Status = status;
    }

    public void setFarmerLevel(String farmerLevel) {
        this.farmerLevel = farmerLevel;
    }

    public String getFarmerLevel() {
        return farmerLevel;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public void setFarmerProfileImage(String farmerProfileImage) {
        this.farmerProfileImage = farmerProfileImage;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public String getFarmerProfileImage() {
        return farmerProfileImage;
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

    public int getFundingRequired() {
        return fundingRequired;
    }

    public String getExpectedReturnsOnInvestment() {
        return expectedReturnsOnInvestment;
    }

    public String getImageOneLink() {
        return imageOneLink;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public void setFarmerID(String farmerID) {
        this.farmerID = farmerID;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }

    public void setProjectDurationInMonths(String projectDurationInMonths) {
        this.projectDurationInMonths = projectDurationInMonths;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setFundingRequired(int fundingRequired) {
        this.fundingRequired = fundingRequired;
    }

    public void setExpectedReturnsOnInvestment(String expectedReturnsOnInvestment) {
        this.expectedReturnsOnInvestment = expectedReturnsOnInvestment;
    }

    public void setImageOneLink(String imageOneLink) {
        this.imageOneLink = imageOneLink;
    }

    public void setImageTwoLink(String imageTwoLink) {
        this.imageTwoLink = imageTwoLink;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getImageTwoLink() {
        return imageTwoLink;
    }

    public String getStatus() {
        return Status;
    }

    // Getters and setters

}
