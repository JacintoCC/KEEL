package keel.GraphInterKeel.experiments;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all the information about the use case of an algorithm
 * @author Jos� Antonio Delgado Osuna
 * @version 1.0
 *
 */
public class UseCase {

    /**
     * The method's name
     */
    private String name;
    /**
     * The method's references
     */
    private List references;
    /**
     * The method's type
     */
    private String type;
    /**
     * The method's objective
     */
    private String objective;
    /**
     * How the method works
     */
    private String howWork;
    /**
     * The method's parameters
     */
    private List parameters;
    /**
     * An example for the method
     */
    private String example;
    /////////////////////////////////////
    //------------The method's properties
    /////////////////////////////////////
    /**
     * This property saids if the methods has continuous variables
     */
    private boolean isContinuous;
    /**
     * This property saids if the methods has discretized variables
     */
    private boolean isDiscretized;
    /**
     * This property saids if the methods has integer variables
     */
    private boolean isInteger;
    /**
     * This property saids if the methods has nominal variables
     */
    private boolean isNominal;
    /**
     * This property saids if the methods has variables without values
     */
    private boolean isWhitoutValues;
    /**
     * This property saids if the methods has variables whit imprecise values
     */
    private boolean isWithImprecise;

    /////////////////////////////////
    //--------------------Constructor
    /////////////////////////////////
    public UseCase() {
        this.name = new String();
        this.references = new ArrayList();
        this.type = new String();
        this.objective = new String();
        this.howWork = new String();
        this.parameters = new ArrayList();
    }

    ////////////////////////////////
    //-----------Getters and Setters
    ////////////////////////////////
    /**
     * @return How the method works
     */
    public String getHowWork() {
        return howWork;
    }

    /**
     * Sets how the method works
     *
     * @param howWork
     */
    public void setHowWork(String howWork) {
        this.howWork = howWork;
    }

    /**
     * @return If the method's variables are continuous
     */
    public boolean isContinuous() {
        return isContinuous;
    }

    /**
     * Sets if the method's variables are continuous
     *
     * @param isContinuous
     */
    public void setContinuous(boolean isContinuous) {
        this.isContinuous = isContinuous;
    }

    /**
     * @return If the method's variables are dicretized
     */
    public boolean isDiscretized() {
        return isDiscretized;
    }

    /**
     * Sets if the method's variables are discretized
     *
     * @param isDiscretized
     */
    public void setDiscretized(boolean isDiscretized) {
        this.isDiscretized = isDiscretized;
    }

    /**
     * @return If the method's variables are integer
     */
    public boolean isInteger() {
        return isInteger;
    }

    /**
     * Sets if the method's variables are integer
     *
     * @param isInteger
     */
    public void setInteger(boolean isInteger) {
        this.isInteger = isInteger;
    }

    /**
     * @return If the method's variables are nominal
     */
    public boolean isNominal() {
        return isNominal;
    }

    /**
     * Sets if the method's variables are nominal
     *
     * @param isNominal
     */
    public void setNominal(boolean isNominal) {
        this.isNominal = isNominal;
    }

    /**
     * @return If the method's variables has no values for some examples
     */
    public boolean isWhitoutValues() {
        return isWhitoutValues;
    }

    /**
     * Sets if the method's variables has no values for some examples
     *
     * @param isWhitoutValues
     */
    public void setWhitoutValues(boolean isWhitoutValues) {
        this.isWhitoutValues = isWhitoutValues;
    }

    /**
     * @return If the method's variables has imprecise values for some examples
     */
    public boolean isWithImprecise() {
        return isWithImprecise;
    }

    /**
     * Sets if the method's variables has imprecise values for some examples
     *
     * @param isWithImprecise
     */
    public void setWithImprecise(boolean isWithImprecise) {
        this.isWithImprecise = isWithImprecise;
    }

    /**
     * @return The method's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the method's name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The method's objective
     */
    public String getObjective() {
        return objective;
    }

    /**
     * Sets the method's objective
     *
     * @param objective
     */
    public void setObjective(String objective) {
        this.objective = objective;
    }

    /**
     * @return The method's parameters
     */
    public List getParameters() {
        return parameters;
    }

    /**
     * Sets the method's parameters
     *
     * @param parameters
     */
    public void addParameter(String parameter) {
        this.parameters.add(parameter);
    }

    /**
     * @return The method's references
     */
    public List getReferences() {
        return references;
    }

    /**
     * Adds a new method's reference
     *
     * @param references
     */
    public void addReference(String ref) {
        this.references.add(ref);
    }

    /**
     * @return The method's type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the method's type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The method's example
     */
    public String getExample() {
        return example;
    }

    /**
     * Sets the method's example
     *
     * @param example
     */
    public void setExample(String example) {
        this.example = example;
    }

    /**
     * Writes the Use Case to an String
     *
     * @return A String representing the Use Case
     */
    public String toString() {

        String useCase = "NAME\n";
        useCase += this.getName() + "\n\n";   //Name of the method

        useCase += "REFERENCES\n";
        //References
        for (int i = 0; i < this.getReferences().size(); i++) {
            useCase += "- " + ((String) this.getReferences().get(i)) + "\n";
        }
        useCase += "\n";

        useCase += "TYPE\n";
        useCase += this.getType() + "\n\n"; //Type

        useCase += "OBJECTIVE\n";
        useCase += this.getObjective() + "\n\n";//Objective

        useCase += "HOW IT WORKS\n";
        useCase += this.getHowWork() + "\n\n";//How Works

        useCase += "PARAMETERS\n";
        //Parameters
        for (int i = 0; i < this.getParameters().size(); i++) {
            useCase += "- " + ((String) this.getParameters().get(i)) + "\n";
        }
        useCase += "\n";

        useCase += "PROPERTIES\n";
        //Properties
        useCase += "- Continuous Variables: " + this.isContinuous() + "\n";
        useCase += "- Nominal Variables: " + this.isNominal() + "\n";
        useCase += "- Discretized Variables: " + this.isDiscretized() + "\n";
        useCase += "- Integer Variables: " + this.isInteger() + "\n";
        useCase += "- Variables without values for some examples: " + this.isWhitoutValues() + "\n";
        useCase += "- Variables with imprecise values for some examples: " + this.isWithImprecise() + "\n\n";

        useCase += "EXAMPLE\n";
        useCase += this.getExample();  //example

        return useCase;

    }
}