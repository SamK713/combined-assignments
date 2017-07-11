package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {
	// put collection/collections here
	HashSet<Capitalist> hash = new HashSet<Capitalist>();
	
	
    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) 
    {
        if(hash.contains(capitalist) || capitalist == null)
        {
        	return false;
        }
        if(capitalist.hasParent()) {
        	this.add(capitalist.getParent());
        	hash.add(capitalist.getParent());
        	return hash.add(capitalist);
        
        }
        else if (capitalist.getClass() == FatCat.class) {
			return hash.add(capitalist);
		}
        else{
        	return false;
        }
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
       return hash.contains(capitalist);
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
        return (Set<Capitalist>) hash.clone();
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
    	HashSet parents = new HashSet();
    	for(Capitalist cap: hash){
    		if(cap instanceof FatCat){
    			parents.add(cap);
    		}
    	}
    	return new HashSet<FatCat>(parents);
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	HashSet<Capitalist> kids = new HashSet<Capitalist>();
    	for(Capitalist capitalist : hash) {
    	  if(capitalist.hasParent()){
    		if(capitalist.getParent() == fatCat)
         	 kids.add(capitalist);
    	  	}
    	  }
    	return (Set<Capitalist>) kids.clone();
    }

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	HashMap<FatCat, Set<Capitalist>> map = new HashMap<>();
    	for(FatCat i: this.getParents()) {
    	        map.put(i, this.getChildren((FatCat) i) );
    	    }
    		return map;
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
    	ArrayList<FatCat> parChain = new ArrayList<>();
    	
    	if(capitalist != null && !hash.isEmpty()){
    		while(capitalist.hasParent()){
        	parChain.add(capitalist.getParent());
        	capitalist = capitalist.getParent();
        	}
    	}
    	return parChain;
    }
}
