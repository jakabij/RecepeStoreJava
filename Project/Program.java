package RecepeStoreJava.Project;

import java.util.ArrayList;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) throws Exception {
      Store store = new Store();
      RecipeBook r1 = new RecipeBook("First");
      RecipeBook r2 = new RecipeBook("Second");

      Food f1 = r1.createFood("3", "Desszert", true, new ArrayList<>(Arrays.asList("elso","masodik")));
      r1.addFood(f1);

      Food f2 = r2.createFood("2", "Madosik", false, new ArrayList<>(Arrays.asList("mindent", "is", "tartalmaz")));
      r2.addFood(f2);

      store.addRecipeBook(r1);
      store.addRecipeBook(r2);

      XMLSaver saver = new XMLSaver();
      saver.saveToXml(store);
    }
}
