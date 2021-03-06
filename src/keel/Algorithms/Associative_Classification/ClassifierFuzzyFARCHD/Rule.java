/***********************************************************************

	This file is part of KEEL-software, the Data Mining tool for regression, 
	classification, clustering, pattern mining and so on.

	Copyright (C) 2004-2010
	
	F. Herrera (herrera@decsai.ugr.es)
    L. Sánchez (luciano@uniovi.es)
    J. Alcalá-Fdez (jalcala@decsai.ugr.es)
    S. García (sglopez@ujaen.es)
    A. Fernández (alberto.fernandez@ujaen.es)
    J. Luengo (julianlm@decsai.ugr.es)

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see http://www.gnu.org/licenses/
  
**********************************************************************/

package keel.Algorithms.Associative_Classification.ClassifierFuzzyFARCHD;



	 
import java.util.*;
import org.core.Randomize;

/**
 * <p>Title: Rule</p>
 * <p>Description: This class codes a Fuzzy Rule</p>
 * <p>Copyright: KEEL Copyright (c) 2008</p>
 * <p>Company: KEEL </p>
 * @author Written by Jesus Alcala (University of Granada) 09/02/2011
 * @version 1.0
 * @since JDK1.6
 */
public class Rule implements Comparable {

  int[] antecedent;
  int clas, nAnts;
  double conf, supp, wracc;
  DataBase dataBase;

  

/**
* <p>
* Create a rule with another one
* </p>
* @param r This a rule
*/
  public Rule(Rule r) {
    this.antecedent = new int[r.antecedent.length];
    for (int k = 0; k < this.antecedent.length; k++) {
      this.antecedent[k] = r.antecedent[k];
    }

	this.clas = r.clas;
    this.dataBase = r.dataBase;
	this.conf = r.conf;
	this.supp = r.supp;
	this.nAnts = r.nAnts;
	this.wracc = r.wracc;
  }

/**
* <p>
* Create a new rule
* </p>
* @param dataBase The database
*/
  public Rule(DataBase dataBase) {
    this.antecedent = new int[dataBase.numVariables()];
    for (int i = 0; i < this.antecedent.length; i++)  this.antecedent[i] = -1;  // Don't care
    this.clas = -1;
    this.dataBase = dataBase;
	this.conf = 0.0;
	this.supp = 0.0;
	this.nAnts = 0;
	this.wracc = 0.0;
  }


/**
* <p>
* Clone
* </p>
* @return A copy of the rule
*/
  public Rule clone() {
    Rule r = new Rule(this.dataBase);
    r.antecedent = new int[antecedent.length];
    for (int i = 0; i < this.antecedent.length; i++) {
      r.antecedent[i] = this.antecedent[i];
    }

	r.clas = this.clas;
	r.dataBase = this.dataBase;
	r.conf = this.conf;
	r.supp = this.supp;
	r.nAnts = this.nAnts;
	r.wracc = this.wracc;

    return (r);
  }

    /**
   * <p>
   * It sets the antecedent of the rule
   * </p>
     * @param antecedent Antecedent of the rule
   */
    public void asignaAntecedente(int [] antecedent){
	this.nAnts = 0;
    for (int i = 0; i < antecedent.length; i++) {
		this.antecedent[i] = antecedent[i];
		if (this.antecedent[i] > -1)  this.nAnts++;
	}
  }

    /**
   * <p>
   * It sets the consequent of the rule
   * </p>
   * @param clas Class of the rule
   */
    public void setConsequent(int clas) {
    this.clas = clas;
  }

    /**
   * <p>
   * Function to check if a given example matchs with the rule (the rule correctly classifies it)
   * </p>
     * @param example  Example to be classified
   * @return 0.0 = doesn't match, >0.0 = does.
   */
    public double matching(double[] example) {
    return (this.degreeProduct(example));
  }

  private double degreeProduct(double[] example) {
    double degree;

    degree = 1.0;
    for (int i = 0; i < antecedent.length && degree > 0.0; i++) {
      degree *= dataBase.matching(i, antecedent[i], example[i]);
    }

    return (degree * this.conf);
  }

    /**
   * <p>
   * It sets the confidence of the rule
   * </p>
   * @param conf Confidence to be set
   */
    public void setConfidence(double conf) {
    this.conf = conf;
  }

    /**
   * <p>
   * It sets the support of the rule
   * </p>
     * @param supp  Support to be set
   */
    public void setSupport(double supp) {
    this.supp = supp;
  }
    /**
   * <p>
   * It sets the Wracc of the rule
   * </p>
   * @param wracc Wracc to be set
   */
  public void setWracc(double wracc) {
    this.wracc = wracc;
  }

    /**
   * <p>
   * It returns the Confidence of the rule
   * </p>
   * @return Confidence of the rule
   */
    public double getConfidence() {
    return (this.conf);
  }

    /**
   * <p>
   * It returns the support of the rule
   * </p>
   * @return Support of the rule
   */
    public double getSupport() {
    return (this.supp);
  }

    /**
   * <p>
   * It returns the Wracc of the rule
   * </p>
   * @return Wracc of the rule
   */
    public double getWracc() {
    return (this.wracc);
  }

    /**
   * <p>
   * It returns the output class of the rule
   * </p>
   * @return Output class of the rule
   */
    public int getClas() {
    return (this.clas);
  }

    /**
   * <p>
   * Function to check if a given rule is a subrule of this rule
   * </p>
   * @param a Rule to be examinated
   * @return false = it isn't, true = it is.
   */
    public boolean isSubset(Rule a) {
	  if ((this.clas != a.clas) || (this.nAnts > a.nAnts))  return (false);
	  else {
		  for (int k = 0; k < this.antecedent.length; k++) {
			  if (this.antecedent[k] > -1) {
				  if (this.antecedent[k] != a.antecedent[k])  return (false);
			  }
		  }
	      return (true);
	  }
  }

/**
* <p>
* Calculate Wracc for this rule.
* The value of the measure Wracc for this rule will be stored on the attribute "wracc".
* </p>
* @param train Training dataset
* @param exampleWeight Weights of the patterns
*/
  public void calculateWracc (myDataset train, ArrayList<ExampleWeight> exampleWeight) {
	  int i;
	  double n_A, n_AC, n_C, degree;
	  ExampleWeight ex;

	  n_A = n_AC = 0.0;
	  n_C = 0.0;
	  
	  for (i=0; i < train.size(); i++) {
		  ex = exampleWeight.get(i);

		  if (ex.isActive()) {
			  degree = this.matching(train.getExample(i));
			  if (degree > 0.0) {
				  degree *= ex.getWeight();
				  n_A += degree;
				  
				  if (train.getOutputAsInteger(i) == this.clas) {
					  n_AC += degree;
					  n_C += ex.getWeight();
				  }
			  }
			  else if (train.getOutputAsInteger(i) == this.clas)  n_C += ex.getWeight();
		  }
	  }

	  if ((n_A < 0.0000000001) || (n_AC < 0.0000000001) || (n_C < 0.0000000001))  this.wracc = -1.0;
	  else  this.wracc = (n_AC / n_C) * ((n_AC / n_A) - train.frecuentClass(this.clas));
  }
  
    /**
     * Reduces the weight of the examples that match with the rule (the rule correctly classifies them)
     * @param train training examples given to match them to the rule.
     * @param exampleWeight Each example weight to be updated.
     * @return Number of examples that have become not active after the weight reduction.
     */
    public int reduceWeight (myDataset train, ArrayList<ExampleWeight> exampleWeight) {
	  int i, count;
	  ExampleWeight ex;

	  count = 0;

	  for (i=0; i < train.size(); i++) {
		  ex = exampleWeight.get(i);
		  if (ex.isActive()) {
			  if (this.matching(train.getExample(i)) > 0.0) {
				  ex.incCount();
				  if ((!ex.isActive()) && (train.getOutputAsInteger(i) == this.clas))  count++;
			  }
		  }
	  }

	  return (count);
  }

   /**
   * It sets the label for a given position in the antecedent (for a given attribute)
   * @param pos Location of the attribute which we want to set the label
   * @param label New label value to set
   */
    public void setLabel(int pos, int label) {
	if ((antecedent[pos] < 0) && (label > -1))  this.nAnts++;
	if ((antecedent[pos] > -1) && (label < 0))  this.nAnts--;
	this.antecedent[pos] = label;
  }

    /**
   * Function to compare objects of the Rule class.
   * Necessary to be able to use "sort" function
   * It sorts in an decreasing order of wracc
   * @param a Rule object to compare with.
   * @return 1 if a is bigger, -1 if smaller and 0 otherwise.
   */
  public int compareTo(Object a) {
	  if (((Rule) a).wracc < this.wracc)  return -1;
	  if (((Rule) a).wracc > this.wracc)  return 1;
	  return 0;
  }

}
