package com.raymond.myrecipe;

public class Recipe {

    int id;
    String name;
    String category;
    int level;
    String intro;
    String ingredient;
    String fact;
    String method;

    public Recipe(int id, String name, String category, int level, String intro, String ingredient, String fact, String method) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.level = level;
        this.intro = intro;
        this.ingredient = ingredient;
        this.fact = fact;
        this.method = method;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
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
