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

/**
 * Abstract class to represent a named attribute/variable.
 * @author Haifeng Li
 */
public abstract class Attribute {
    /**
     * The type of attributes.
     */
    public enum Type {
        /**
         * Numeric attribute.
         */
        NUMERIC,
        
        /**
         * Nominal attribute. Variables assessed on a nominal scale are called
         * categorical variables.
         */
        NOMINAL,
    }

    /**
     * The type of attribute.
     */
    public final Type type;
    
    /**
     * Optional weight of this attribute. By default, it is 1.0. The particular
     * meaning of weight depends on applications and machine learning algorithms.
     * Although there are on explicit requirements on the weights, in general,
     * they should be positive.
     */
    public final double weight;

    /**
     * The name of attribute.
     */
    public final String name;

    /**
     * The detailed description of the attribute.
     */
    public final String description;

    /**
     * Initializes a new instance of the Attribute class.
     * @param type Type of the attribute.
     * @param name Name of the attribute.
     */
    public Attribute(Type type, String name) {
        this(type, name, 1.0);
    }

    /**
     * Initializes a new instance of the Attribute class.
     * @param type Type of the attribute.
     * @param name Name of the attribute.
     * @param weight Weight of this attribute.
     */
    public Attribute(Type type, String name, double weight) {
        this(type, name, null, weight);
    }

    /**
     * Initializes a new instance of the Attribute class.
     * @param type Type of the attribute.
     * @param name Name of the attribute.
     * @param description Description of this attribute.
     */
    public Attribute(Type type, String name, String description) {
        this(type, name, description, 1.0);
    }

    /**
     * Initializes a new instance of the Attribute class.
     * @param type Type of the attribute.
     * @param name Name of the attribute.
     * @param description Description of this attribute.
     * @param weight Weight of this attribute.
     */
    public Attribute(Type type, String name, String description, double weight) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Returns the string representation of a double value of this attribute.
     * @param x a double value of this attribute. NaN means missing value.
     * @return the string representation of x. For nominal, date and string
     * attributes, null will be returned for missing values. For numeric
     * attributes, "NaN" will be returned for missing values.
     */
    public abstract String toString(double x);

    /**
     * Returns the double value of a string of this attribute.
     * @param s a string value of this attribute.
     */
    public abstract double valueOf(String s) throws ParseException;

    @Override
    public boolean equals(Object o) {
        if (o instanceof Attribute) {
            Attribute a = (Attribute) o;

            if (name.equals(a.name) && type == a.type) {
                if (description != null && a.description != null && description.equals(a.description)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + type.hashCode();
        hash = 37 * hash + (name != null ? name.hashCode() : 0);
        hash = 37 * hash + (description != null ? description.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append('[');
        sb.append(name);
        sb.append(']');

        return sb.toString();
    }
}