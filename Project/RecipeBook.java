package RecepeStoreJava.Project;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;

public class RecipeBook {
    UI ui = new UI();
    String id;
    int pageCount;
    String name;
    ArrayList<Food> listOfFoods;

    public RecipeBook(){ }
    public RecipeBook(String name)
    {
        this.name=name;
    }

    public Food createFood(String typeOfFood, String nameOfFood,boolean serveCold, ArrayList<String> listOfIngredients) throws Exception
    {
        Food food;
            if(typeOfFood.toLowerCase().equals("1"))
            {
                food = createAppetizer(typeOfFood, nameOfFood, serveCold, listOfIngredients);
            }
            else if(typeOfFood.toLowerCase().equals("2"))
            {
                food = createSecondMeal(typeOfFood, nameOfFood, serveCold, listOfIngredients);
            }

            else if(typeOfFood.toLowerCase().equals("3"))
            {
                food = createDessert(typeOfFood, nameOfFood, serveCold, listOfIngredients);
            }
            else
            {
                throw new Exception("NoFoodLikeThatException");
            }

            return food;
    }


    public Food createAppetizer(String typeOfFood, String nameOfFood, boolean serveCold, ArrayList<String> listOfIngredients) throws Exception
        {
            String id = ui.idGenerator();
            Time timeToPrepare;

            String userInput = ui.getInputFromUser("\nThe time to prepare it [in 0:0:0 format]: ");
            String[] timeAfterSplit = userInput.split(":");
            if (timeAfterSplit.length < 3 || timeAfterSplit.length > 3)
            {
                throw new Exception("ParseError");
            }
            else
            {
                int hoursIndex = 0;
                int minutesIndex = 1;
                int secondsIndex = 2;
                

                try
                {
                    int hours = Integer.parseInt(timeAfterSplit[hoursIndex]);
                    int minutes = Integer.parseInt(timeAfterSplit[minutesIndex]);
                    int seconds = Integer.parseInt(timeAfterSplit[secondsIndex]);
                    timeToPrepare = new Time(hours, minutes, seconds);
                }
                catch(Exception ex)
                {
                    throw new Exception("ParseError");
                }
            }
            return new Appetizer(id, nameOfFood, serveCold, listOfIngredients, timeToPrepare);
        }

        public Food createSecondMeal(String typeOfFood, String nameOfFood, boolean serveCold, ArrayList<String> listOfIngredients) throws Exception
        {
            String id = ui.idGenerator();
            String userInput = ui.getInputFromUser("\n[yes or no]\nNeed to cook: ");
            boolean needToCook;
            if (userInput.toLowerCase().equals("yes") || userInput.toLowerCase().equals("y"))
            {
                needToCook = true;
            }
            else if (userInput.toLowerCase().equals("no") || userInput.toLowerCase().equals("n"))
            {
                needToCook = false;
            }
            else
            {
                throw new Exception("ParseError");
            }


            Time timeToPrepare;
            userInput = ui.getInputFromUser("\nThe time to prepare it [in 0:0:0 format]: ");
            String[] inputSplitting = userInput.split(":");
            if (inputSplitting.length < 3 || inputSplitting.length > 3)
            {
                throw new Exception("ParseError");
            }
            else
            {
                int hoursIndex = 0;
                int minutesIndex = 1;
                int secondsIndex = 2;
                
                try
                {
                    int hours = Integer.parseInt(inputSplitting[hoursIndex]);
                    int minutes = Integer.parseInt(inputSplitting[minutesIndex]);
                    int seconds = Integer.parseInt(inputSplitting[secondsIndex]);
                    timeToPrepare = new Time(hours, minutes, seconds);
                }
                catch(Exception ex)
                {
                    throw new Exception("ParseError");
                }
            }

            userInput = ui.getInputFromUser("\nList of the spices separated by ',': ");
            inputSplitting = userInput.split(",");

            ArrayList<String> listOfSpices = new ArrayList<String>();
            Collections.addAll(listOfSpices, inputSplitting);
           
            String toCheck = "'~ˇ+^!˘%°/˛=`´˝¨\\|€÷×łŁ$ß#&@<?;.:*";
            for(var spice : listOfSpices)
            {
                for (int i = 0; i < toCheck.length(); i++)
                {
                    if (spice.contains(""+toCheck.charAt(i)))
                        throw new Exception("InvalidAttribute");
                }
            }

            return new SecondMeal(id, nameOfFood, serveCold, listOfIngredients, needToCook, timeToPrepare, listOfSpices);
        }

        public Food createDessert(String typeOfFood, String nameOfFood, boolean serveCold, ArrayList<String> listOfIngredients) throws Exception
        {
            String id = ui.idGenerator();
            String userInput = ui.getInputFromUser("Need to cook it: ");
            boolean needToCook;
            if (userInput.toLowerCase().equals("yes") || userInput.toLowerCase().equals("y"))
            {
                needToCook = true;
            }
            else if (userInput.toLowerCase().equals("no") || userInput.toLowerCase().equals("n"))
            {
                needToCook = false;
            }
            else
            {
                throw new Exception("ParseError");
            }

            Time timeToPrepare;
            userInput = ui.getInputFromUser("The time to prepare it: ");
            String[] inputSplitting = userInput.split(":");
            if (inputSplitting.length < 3 || inputSplitting.length > 3)
            {
                throw new Exception("ParseError");
            }
            else
            {
                int hoursIndex = 0;
                int minutesIndex = 1;
                int secondsIndex = 2;
                
                try
                {
                    int hours = Integer.parseInt(inputSplitting[hoursIndex]);
                    int minutes = Integer.parseInt(inputSplitting[minutesIndex]);
                    int seconds = Integer.parseInt(inputSplitting[secondsIndex]);
                    timeToPrepare = new Time(hours, minutes, seconds);
                }
                catch(Exception ex)
                {
                    throw new Exception("ParseError");
                }
            }

            return new Dessert(id, nameOfFood, serveCold, listOfIngredients, needToCook, timeToPrepare);
        }



    public void addFood(Food food)
    {
        listOfFoods.add(food);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Food> getListOfFoods() {
        return listOfFoods;
    }

    public void setListOfFoods(ArrayList<Food> listOfFoods) {
        this.listOfFoods = listOfFoods;
    }
}
