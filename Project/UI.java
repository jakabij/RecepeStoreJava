package RecepeStoreJava.Project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UI {
    public void start()
        {
            System.out.println("\n\n\t\t\t--Welcome to the CodeCool's recepe store!--\n\nPress:");
            System.out.println("\t- 1) To list the known recepe(s).\n\t" +
                "- 2) To create new recepe book.\n\t" +
                "- 3) To modify a recepe book.\n\t" +
                "- 4) To remove a recepe book.\n\t" +
                "- 5) To find book by food name.\n\t" +
                "- 6) To show Book's recepe by book ID.\n\t" +
                "- 7) To save to xml.\n\t" +
                "- 0) To exit.\n\n\n");
        }

        public void printUpdateMenu(String bookName)
        {
            System.out.println("\tThe "+ bookName +" is front of you.\nPress:\n\n\t- 1) To delete a food recepe.\n\t" +
                "- 2) To add new food.\n\t" +
                "- 3) To add comment to a food.\n\t" +
                "- 4) To go back to book choosing.\n\n\n");
        }

        public void tableCloser(boolean isTop, int nameCellWidth, int idCellWidth)
        {
            if(isTop)
            {
                System.out.print("\n\n/");
                printLine(nameCellWidth + 1, idCellWidth);
                System.out.println("\\");
            }
            else
            {
                System.out.print("\\");
                printLine(nameCellWidth+1, idCellWidth);
                System.out.println("/\n\n");
            }
        }
        public void printLine(int nameCellWidth, int idCellWidth)
        {
            for(int i = 0; i < nameCellWidth; i++)
            {
                System.out.print("-");
            }

            for(int i = 0; i < idCellWidth; i++)
            {
                System.out.print("-");
            }
            System.out.println();
        }

        public void tableDatas(ArrayList<RecipeBook> listOfBooks,int nameCellWidth,int idCellWidth)
        {
            String id = "ID";
            String name = "Name";
            
            System.out.println("|"+ id + "|" + name +"|");          //TODO modifing

            System.out.print("|");
            printLine(nameCellWidth + 1, idCellWidth);
            System.out.println("|");

            for (int count = 0; count < listOfBooks.size(); count++)
            {
                System.out.println("|" + listOfBooks.get(count).getId() + "|" + listOfBooks.get(count).getName() + "|");
                if (count < listOfBooks.size() - 1)
                {
                    System.out.print("|");
                    printLine(nameCellWidth + 1, idCellWidth);
                    System.out.println("|");
                }
            }
        }

        public String idGenerator()
        {
            String characters = "0123456789qwertzuiopasdfghjklyxcvbnmQWERTZUIOPASDFGHJKLYXCVBNM";
            String id = "";
            
            Random random = new Random();
            for(int count = 0; count < 9; count++)
            {
                int randomCharIndex = random.nextInt(characters.length());
                id+=characters.charAt(randomCharIndex);
            }
            return id;
        }

        public String getInputFromUser(String message)
        {
            System.out.println(message);
            Scanner sc = new Scanner(System.in);
            return sc.nextLine();
        }

        public void getInfo(String message)
        {
                System.out.print("[INFO]:");
                System.out.println("\t"+message);
        }

        public void getResult(ArrayList<String> result,boolean isFail)
        {
            if(!isFail)
            {
                System.out.println("The result:");
                for(String element : result)
                {
                    System.out.println("\t-"+element);
                }
            }
        }

        public void getBookFoods(RecipeBook book)
        {
            System.out.println(book.getName());
            printLine(book.getName().length(), 0);
            System.out.println();

            for(var item : book.getListOfFoods())
            {
                System.out.println("\t- "+ item.getNameOfFood() + "  ( " + item.getId() +" )");
            }
            questionForRecepe(book);
        }

        public boolean questionForFoodAdding()
        {
            String input = getInputFromUser("Do you want to add food for it? ");
            if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes"))
                return true;
            else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no"))
                return false;
            else
            { 
                getInfo("Invalid attribute!");
                return false;
            }
        }

        public void questionForRecepe(RecipeBook book)
        {
            String wantToContinue = getInputFromUser("Do you want to read a recipe from "+ book.getName() + "?");
            while(true)
            {
                if (wantToContinue.toLowerCase().equals("yes") || wantToContinue.toLowerCase().equals("y"))
                {
                    getFoodDatas(book);
                    wantToContinue = getInputFromUser("Do you want to read a recipe from " + book.getName() + "?");
                }
                else if (wantToContinue.toLowerCase().equals("no") || wantToContinue.toLowerCase().equals("n"))
                {
                    break;
                }
                else
                {
                    getInfo("Invalid attribute!");
                    wantToContinue = getInputFromUser("Do you want to read a recipe from " + book.getName() +"?");
                }
            }
        }

        public void getFoodDatas(RecipeBook book)
        {
            String foodName = getInputFromUser("The searched food's name: ");

            for (var item : book.getListOfFoods())
            {
                if (item.getNameOfFood().equals(foodName))
                {
                    printLine(40, 0);
                    if (item instanceof Appetizer)
                    {
                        Appetizer appetizer = (Appetizer)item;
                        System.out.println("\nThis food is an appetizer.\nPreparing time: "
                        + appetizer.getTimeToPrepare().toString());

                        System.out.println("\n\tThe ingredients you need:");
                        for(var ingredient : appetizer.getListOfIngredients())
                        {
                            System.out.println("\t\t- "+ ingredient);
                        }

                        if (appetizer.isServeCold())
                            System.out.println("\nThis appetizer is need to be served cold.\n");
                        else
                            System.out.println("\nThis appetizer is need to be served hot.\n");
                    }
                    else if (item instanceof SecondMeal)
                    {
                        SecondMeal secondMeal = (SecondMeal)item;
                        System.out.println("\n\tThis food is a second meal.\nPreparing time: "
                        + secondMeal.getTimeToPrepare().toString());

                        if (secondMeal.isNeedToCook())
                            System.out.println("It needs to be cooked.");

                        System.out.println("\n\tThe ingredients you need:");
                        for(var ingredient : secondMeal.getListOfIngredients())
                        {
                            System.out.println("\t\t- "+ ingredient);
                        }

                        for(var spice : secondMeal.getListOfSpices())
                        {
                            System.out.println("\t\t- " + spice);
                        }

                        if (secondMeal.isServeCold())
                            System.out.println("\nThis meal is need to be served cold.\n");
                        else
                            System.out.println("\nThis meal is need to be served hot.\n");

                    }
                    else
                    {
                        Dessert dessert = (Dessert)item;
                        System.out.println("\n\tThis food is a dessert.\nPreparing time: "
                        + dessert.getTimeToPrepare().toString());
                        if (dessert.isNeedToCook())
                            System.out.println("It needs to be cooked.");

                        System.out.println("\n\tThe ingredients you need:");
                        for(var ingredient : dessert.getListOfIngredients())
                        {
                            System.out.println("\t\t- " + ingredient);
                        }

                        if (dessert.isServeCold())
                            System.out.println("\nThis dessert is need to be served cold.\n");
                        else
                            System.out.println("\nThis dessert is need to be served hot.\n");
                    }
                    Scanner sc = new Scanner(System.in);
                    sc.nextLine();
                }
            }
        }
}
