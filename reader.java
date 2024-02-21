import java.io.*;
import java.util.*;

public class reader {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data" + File.separator + "Recipe_Book.csv");
        Scanner scanner = new Scanner(file);
        ArrayList<Recipe> recipeList = new ArrayList<>();

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String dataline = scanner.nextLine();

            StringTokenizer tokenizer = new StringTokenizer(dataline, ",");
            
            String category = tokenizer.nextToken().trim();
            String name = tokenizer.nextToken().trim();
            String recipe = tokenizer.nextToken().trim();

            recipeList.add(new Recipe(category, name, recipe));
        }
        scanner.close();

        for (Recipe recipe : recipeList) {
            System.out.println(recipe.getCategory() + ", " + recipe.getfoodName() + ", " + recipe.getRecipe());
        }
    }
}