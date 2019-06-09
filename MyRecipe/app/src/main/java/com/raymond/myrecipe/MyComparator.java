package com.raymond.myrecipe;

import java.util.Comparator;

public class MyComparator {

    titleCompare tCompare;
    ctgCompare cCompare;
    levelCompare lCompare;

    public MyComparator(){
        tCompare = new titleCompare();
        cCompare = new ctgCompare();
        lCompare = new levelCompare();
    }

    public class titleCompare implements Comparator<Recipe>{
        public int compare(Recipe a, Recipe b){
            String str1 = a.name;
            String str2 = b.name;
            int temp = str1.compareTo(str2);
            if(temp > 0){
                return 1;
            }else if(temp < 0){
                return -1;
            }else{
                return 0;
            }
        }
    }

    public class ctgCompare implements Comparator<Recipe>{
        public int compare(Recipe a, Recipe b){
            String str1 = a.category;
            String str2 = b.category;
            int temp = str1.compareTo(str2);
            if(temp > 0){
                return 1;
            }else if(temp < 0){
                return -1;
            }else{
                return a.name.compareTo(b.name);
            }
        }
    }

    public class levelCompare implements Comparator<Recipe>{
        public int compare(Recipe a, Recipe b){
            int int1 = a.level;
            int int2 = b.level;
            int temp = int1 - int2;
            if(temp > 0){
                return 1;
            }else if(temp < 0){
                return -1;
            }else{
                return a.name.compareTo(b.name);
            }
        }
    }
}
