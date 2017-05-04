/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Some3rdLibrary;

import java.io.Serializable;
import java.util.Comparator;

//This is the innofensive class supposed to be used originally
public class InoffensiveComparator implements Comparator,Serializable{

    @Override
    public int compare(Object t, Object t1) {

        return 1;
    
    }
    
}
