package com.bizreport.consumer.database;

public class Company {
    String name, riskFactors, expenses, income, executiveOfficers;

    public Company(){}

    public Company(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutiveOfficers() {
        return executiveOfficers;
    }

    public void setExecutiveOfficers(String executiveOfficers) {
        this.executiveOfficers = executiveOfficers;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getRiskFactors() {
        return riskFactors;
    }

    public void setRiskFactors(String riskFactors) {
        this.riskFactors = riskFactors;
    }
}
