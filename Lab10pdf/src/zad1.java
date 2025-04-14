import RunInput.Input;

import java.util.*;

public class zad1 {
    public zad1(){
        Input input = new Input();

        System.out.println("Podaj tekst: ");
        String txt = input.inputLine();
        txt = txt.replaceAll("\\p{Punct}", "").toLowerCase();
        String[] splitTxt = txt.split("\\s+");
        Map<String, Integer> map = new HashMap<>();
        for(String s : splitTxt){
            if(map.get(s)!=null){
                map.put(s,map.get(s)+1);
            }else{
                map.put(s,1);
            }
        }

        for(String s : map.keySet()){
            System.out.println(s + " " + map.get(s));
        }
    }
}
