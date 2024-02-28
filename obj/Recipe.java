package obj;

public class Recipe {
    private String category;
    private String food_name;
    private String recipe;
    
    public Recipe(){
        this.category = "";
        this.food_name = "";
        this.recipe = "";
    }
    public Recipe(String category,String food_name, String recipe){
        this.category = category;
        this.food_name = food_name;
        this.recipe = recipe;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setfoodName(String food_name){
        this.food_name = food_name;
    }
    public void setRecipe(String recipe){
        this.recipe = recipe;
    }
    public String getCategory(){
        return this.category;
    }
    public String getfoodName(){
        return this.food_name;
    }
    public String getRecipe(){
        return this.recipe;
    }
}