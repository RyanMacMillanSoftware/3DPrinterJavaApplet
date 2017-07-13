package sample;

import java.io.Serializable;

/**
 * Created by ryan on 13/07/17.
 */
public class Project implements Serializable, Comparable<Project> {
    private String name;

    public Project(String n){
        name = n;
    }


    public String getName(){
        return name;
    }

    public int compareTo(Project other){
        return getName().compareTo(other.getName());
    }
}
