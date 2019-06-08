package com.raymond.myrecipe;

public class Recipe {

    int id;
    String name;
    String category;
    String ingredient;
    String method;

    public Recipe(int id, String name, String category, String ingredient, String method) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.ingredient = ingredient;
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
