package sample;

import java.io.Serializable;

/**
 * Created by ryan on 13/07/17.
 */
public class Name implements Serializable, Comparable<Name> {
    private String name;

    public Name(String n){
        name = n;
    }

    public String getName(){
        return name;
    }

    public int compareTo(Name other){
        return getName().compareTo(other.getName());
    }

}
