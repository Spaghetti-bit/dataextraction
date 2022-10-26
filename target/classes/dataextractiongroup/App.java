package dataextractiongroup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public final class App {
    private App() {
    }

    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        BufferedReader br = new BufferedReader(new FileReader("people-2.to.regex.csv"));

        String line = "";
        Person person = new Person();
        DataValidation validator = new DataValidation();
        Field[] personFields = Person.class.getFields();
        Method[] dataMethods = DataValidation.class.getMethods();
        while ((line = br.readLine()) != null)
        {
            // CSV :)
            String[] personStrArray = line.split(",");

            // Iterate through fields AND the personStrArray, hydrate with valid data.
            for (int i = 0; i < personStrArray.length && i < personFields.length; i++)
            {
                //Validate data.
                /*
                 * name
                 * ssn
                 * email
                 * phone
                 */
                Object result = dataMethods[i].invoke(validator, personStrArray[i]);
                if (result instanceof Boolean)
                {
                    Boolean valid = (Boolean) result;
                    //If Data is valid, set it.
                    if(valid) personFields[i].set(person, personStrArray[i]);
                }
            }
        }
    }
}