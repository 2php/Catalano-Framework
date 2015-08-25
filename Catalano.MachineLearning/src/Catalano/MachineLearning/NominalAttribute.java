// Catalano Machine Learning Library
// The Catalano Framework
//
// Copyright © Diego Catalano, 2015
// diego.catalano at live.com
//
// Copyright 2015 Haifeng Li
// haifeng.hli at gmail.com
//
// Based on Smile (Statistical Machine Intelligence & Learning Engine)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package Catalano.MachineLearning;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Nominal attribute. Nominal attributes are defined on a list of the unordered
 * values.
 *
 * @author Haifeng Li
 */
public class NominalAttribute extends Attribute {

    /**
     * True if the string values of the nominal attribute is a open set.
     * The new string values will be added into the attribute when calling
     * the method valueOf(String).
     */
    private boolean open = true;
    
    /**
     * The string values of the nominal attribute.
     */
    private List<String> values;
    
    /**
     * Map a string to an integer level.
     */
    private HashMap<String, Integer> map;

    /**
     * Initializes a new instance of the NominalAttribute class.
     * @param name Name of the attribute.
     */
    public NominalAttribute(String name) {
        this(name, 1.0);
    }

    /**
     * Initializes a new instance of the NominalAttribute class.
     * @param name Name of the attribute.
     * @param weight Weight of the attribute.
     */
    public NominalAttribute(String name, double weight) {
        this(name, null, weight);
    }

    /**
     * Initializes a new instance of the NominalAttribute class.
     * @param name Name of the attribute.
     * @param description Description of the attribute.
     */
    public NominalAttribute(String name, String description) {
        this(name, description, 1.0);
    }

    /**
     * Initializes a new instance of the NominalAttribute class.
     * @param name Name of the attribute.
     * @param description Description of the attribute.
     * @param weight Weight of the attribute.
     */
    public NominalAttribute(String name, String description, double weight) {
        super(Type.NOMINAL, name, description, weight);
        this.values = new ArrayList<String>();
        this.map = new HashMap<String, Integer>();
        this.open = true;
    }
    
    /**
     * Initializes a new instance of the NominalAttribute class.
     * @param name Name of the attribute.
     * @param values The valid string values of nominal attribute.
     */
    public NominalAttribute(String name, String[] values) {
        this(name, 1.0, values);
    }

    /**
     * Initializes a new instance of the NominalAttribute class.
     * @param name Name of the attribute.
     * @param weight Weight of the attribute.
     * @param values The valid string values of nominal attribute.
     */
    public NominalAttribute(String name, double weight, String[] values) {
        this(name, null, weight, values);
    }

    /**
     * Initializes a new instance of the NominalAttribute class.
     * @param name Name of the attribute.
     * @param description Description of the attribute.
     * @param values The valid string values of nominal attribute.
     */
    public NominalAttribute(String name, String description, String[] values) {
        this(name, description, 1.0, values);
    }

    /**
     * Initializes a new instance of the NominalAttribute class.
     * @param name Name of the attribute.
     * @param description Description of the attribute.
     * @param weight Weight of the attribute.
     * @param values The valid string values of nominal attribute.
     */
    public NominalAttribute(String name, String description, double weight, String[] values) {
        super(Type.NOMINAL, name, description, weight);
        this.values = new ArrayList<String>();
        this.map = new HashMap<String, Integer>();
        this.open = false;
        for (int i = 0; i < values.length; i++) {
            this.values.add(values[i]);
            this.map.put(values[i], i);
        }
    }

    /**
     * Returns the number of nominal values.
     * @return the number of nominal values 
     */
    public int size() {
        return values.size();
    }
    
    /**
     * Returns true if the string values of the nominal attribute is a open set.
     * The new string values will be added into the attribute when calling
     * the method valueOf(String).
     * @return true if the string values of the nominal attribute is a open set.
     */
    public boolean isOpen() {
        return open;
    }
    
    /**
     * Sets if the string values of the nominal attribute is a open set.
     * @param open true if the string values of the nominal attribute is a open set.
     */
    public void setOpen(boolean open) {
        this.open = open;
    }
    
    /**
     * Returns the string values of nominal attribute.
     */
    public String[] values() {
        return values.toArray(new String[values.size()]);
    }

    /**
     * Returns the ordinal value of a string value.
     */
    @Override
    public double valueOf(String s) throws ParseException {
        Integer i = map.get(s);
        if (i == null) {
            if (open) {
                i = values.size();
                map.put(s, i);
                values.add(s);
            } else {
                throw new IllegalArgumentException("Invalid string value: " + s);
            }
        }
        
        return i;
    }

    @Override
    public String toString(double x) {
        if (Double.isNaN(x)) {
            return null;
        }
        
        if (Math.floor(x) != x) {
            throw new IllegalArgumentException("The input nominal value is not an integer: " + x);
        }

        if (x < 0 || x >= values.size()) {
            throw new IllegalArgumentException("Invalid nominal value: " + x);
        }

        return values.get((int)x);
    }
}