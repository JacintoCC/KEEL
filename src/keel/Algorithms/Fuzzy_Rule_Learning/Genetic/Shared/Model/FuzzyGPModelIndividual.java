/**
 * <p>
 * @author Written by Luciano S�nchez (Universisty of Oviedo) 21/01/2004
 * @author Modified by M.R. Su�rez (University of Oviedo) 18/12/2008
 * @author Modified by Enrique A. de la Cal (University of Oviedo) 21/12/2008
 * @version 1.0
 * @since JDK1.5
 * </p>
 */

package keel.Algorithms.Fuzzy_Rule_Learning.Genetic.Shared.Model;

import org.core.*;
import keel.Algorithms.Fuzzy_Rule_Learning.Genetic.Shared.Node.*;
import keel.Algorithms.Fuzzy_Rule_Learning.Genetic.Shared.Individual.*;
import keel.Algorithms.Fuzzy_Rule_Learning.Shared.Fuzzy.*;
import keel.Algorithms.Fuzzy_Rule_Learning.Genetic.Shared.Algorithms.*;
import keel.Algorithms.Fuzzy_Rule_Learning.Genetic.Shared.Genotypes.*;
import keel.Algorithms.Shared.Exceptions.*;

public class FuzzyGPModelIndividual extends GeneticIndividualForModels {
/**
 * 
 */
    private static FuzzyPartition[] A;
    private static FuzzyPartition C;
    private static int defuzType;
    private Fuzzy label(int nv, int nlabel) { return A[nv].getComponent(nlabel); }
    int numlabels(int nv) { return A[nv].size(); }



    /**
     * <p>
     * Constructor. Initialize a fuzzy individual for GP model
     * </p>
     * @param a The list of fuzzy partitions
     * @param c The fuzzy partition
     * @param MAXH Maximum height for trees
     * @param tf Type of fitness
     * @param r Random
     * @param td Type of defuzzifier
     */
    public FuzzyGPModelIndividual(FuzzyPartition[] a, FuzzyPartition c,int MAXH, int tf, Randomize r,int td) {
	    super(tf);
        A=a;
        C=c;
        GenotypeFuzzyGP gf=new GenotypeFuzzyGP(A,C,MAXH,r);
        g=gf;
        defuzType=td;
        //The object of class Model shares the tree defined in the genotype
        m=new FuzzyGPModel((NodeRuleBase)(gf.getRootNode()),C,td);
        
    }

    /**
     * <p>
     * Constructor. Inicialize a fuzzy individual for GP model from another one
     * </p>
     * @param p The fuzzy individual for GP model
     */
    public FuzzyGPModelIndividual(FuzzyGPModelIndividual p) {
	    super(p.fitnessType);
        g=p.g.clone();
        GenotypeFuzzyGP gf=(GenotypeFuzzyGP)(g);
        defuzType=p.defuzType;
        m=new FuzzyGPModel((NodeRuleBase)(gf.getRootNode()),C,defuzType);
    }

    /**
     * <p>
     * This method clone a fuzzy individual for GP model
     * </p>
     */
    public GeneticIndividual clone() {
        return new FuzzyGPModelIndividual(this);
    }

    /**
     * <p>
     * This method assing the properties of a fuzzy individual for GP model to another one
     * </p>
     * @param p The fuzzy individual
     */    
    public void set(FuzzyGPModelIndividual p) {
        g=p.g.clone();
        GenotypeFuzzyGP gf=(GenotypeFuzzyGP)(g);
        defuzType=p.defuzType;
        m=new FuzzyGPModel((NodeRuleBase)(gf.getRootNode()),C,defuzType);
    }

    /**
     * <p>
     * This method generate a fuzzy individual for GP model from another one
     * </p>
     * @return The new one
     */    
    public GeneticIndividual FuzzyGPModelIndividualoClona() {
        return  new FuzzyGPModelIndividual(this);
    }

    /**
     * <p>
     * This method obtain the parameters of a genetic individual from the genotype
     * </p>
     */   
    public void parametersFromGenotype() {
        GenotypeFuzzyGP gf=(GenotypeFuzzyGP)(g);
        m=new FuzzyGPModel((NodeRuleBase)(gf.getRootNode()),C,defuzType);
    }

    /**
     * <p>
     * This method generate a random genotype and obtain the parameters from another one
     * </p>
     */

    public void Random() {
        g.Random();
        parametersFromGenotype();
    }

    /**
     * <p>
     * This method implement the mutation operation
     * </p>
     * @param alpha Index mutation
     * @param IDMUTA Type of mutation
     * @throws invalidMutation message if error
     */    
    public void mutation(double alpha, int IDMUTA) throws invalidMutation  {
        g.mutation(alpha,IDMUTA);
        parametersFromGenotype();
    }

    /**
     * <p>
     * This method implement the cross operation.
     * The cross generates two objects of class 'individuogen'
     * </p>
     * @param p2 Genetic individual
     * @param p3 Genetic individual
     * @param p4 Genetic individual
     * @param IDCRUCE Type of cross
     * @throws invalidCrossover Message if error
     */ 
    public void crossover(GeneticIndividual p2, GeneticIndividual p3, GeneticIndividual p4, int IDCRUCE) throws invalidCrossover {

        FuzzyGPModelIndividual f2=(FuzzyGPModelIndividual)(p2);
        FuzzyGPModelIndividual f3=(FuzzyGPModelIndividual)(p3);
        FuzzyGPModelIndividual f4=(FuzzyGPModelIndividual)(p4);

        g.crossover(f2.g,f3.g,f4.g,IDCRUCE);

        //The crossover generates two objects of class 'individuogen'
        f3.parametersFromGenotype();
        f4.parametersFromGenotype();

    }

    /**
     * <p>
     * This method is for debug
     * </p>
     */
    public void debug() { g.debug(); } // Overload debug from IndividuoGenModel
	
	/**
	 * <p>
	 * This method calculate a local optimization
	 * </p>
	 * @param MAXITER Maximum number of iterations
	 * @param idoptimiza Type of optimization
	 * @throws invalidOptim Message if error
	 */    
	public void localOptimization(int MAXITER, int idoptimization) throws invalidOptim {
	   throw new invalidOptim("Optimizacion local no implementada en FuzzyGPModelIndividual");
	}

}


