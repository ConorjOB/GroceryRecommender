package com.example.mynam.groceryrecommender;

public class Products {

    private String Calories;
    private String Carbs;
    private String Energy;
    private String Fibre;
    private String Price;
    private String Protein;
    private String Salt;
    private String Saturates;
    private String Sugars;
    private String Vitamins;
    private String Calcium;

    public Products(String Calories, String Carbs, String Energy,String Fibre,String Price,String Protein,String Salt,String Saturates,String Sugars,String Vitamins,String Calcium) {
            this.Calories = Calories;
            this.Carbs = Carbs;
            this.Energy = Energy;
            this.Fibre = Fibre;
            this.Price = Price;
            this.Protein = Protein;
            this.Salt = Salt;
            this.Saturates = Saturates;
            this.Sugars = Sugars;
            this.Vitamins = Vitamins;
            this.Calcium=Calcium;
    }
    public Products (){ }  //no argument constructor

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String Calories) {
        this.Calories = Calories;
    }

    public String getCarbs() {
        return Carbs;
    }

    public void setCarbs(String Carbs) {
        this.Carbs = Carbs;
    }

    public String getEnergy() {
        return Energy;
    }

    public void setEnergy(String Energy) {
        this.Energy = Energy;
    }

    public String getFibre() {
        return Fibre;
    }

    public void setFibre(String Fibre) {
        this.Fibre = Fibre;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getProtein() {
        return Protein;
    }

    public void setProtein(String Protein) {
        this.Protein = Protein;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String Salt) {
        this.Salt = Salt;
    }

    public String getSaturates() {
        return Saturates;
    }

    public void setSaturates(String Saturates) {
        this.Saturates = Saturates;
    }

    public String getSugars() {
        return Sugars;
    }

    public void setSugars(String Sugars) {
        this.Sugars = Sugars;
    }

    public String getVitamins() {
        return Vitamins;
    }

    public void setVitamins(String Vitamins) {
        this.Vitamins = Vitamins;
    }

    public String getCalcium() {
        return Calcium;
    }

    public void setCalcium(String Calcium) {
        this.Calcium = Calcium;
    }
}


