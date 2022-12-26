package com.example.dictionaryhari11december;

import java.util.HashMap;

public class DictionaryUsingHashMap {

    private HashMap<String,String> dictionary;

    public DictionaryUsingHashMap() {
        this.dictionary = new HashMap<>();
        addWordList();
    }

    public boolean addWord(String word, String meaning){

        try{
            dictionary.put(word,meaning);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public String getMeaning(String word){
        if(dictionary.containsKey(word)){
            return dictionary.get(word);
        }
        else{
            return "word does not exist in dictionary";
        }
    }

    private void addWordList(){
        addWord("shubh", "Auspicious");
        addWord("phase", "a stage in the development of something");
        addWord("project", "an individual or collaborative enterprise that is carefully planned to achieve a particular aim");
        addWord("start", "beginning");
        addWord("depreciating assets ", "Whose value decreases with time");
        addWord("absence", "lack");
        addWord("academic", "scholastic");
        addWord("boundary", "bounds");
        addWord("borrow", "take");
        addWord("characteristics", "typical");
        addWord("embrace", "accept");
        addWord("foreign", "overseas");
        addWord("incorporate", "include");
        addWord("justify", "defend");
        addWord("generate", "produce");
        addWord("dialogue", "conversation");

    }
}
