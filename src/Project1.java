/*
 *  Given a dictionary, find the largest set of anagram words
 *  Words that use the same set of letters
 *
 *  The dictionary used for this project was downloaded from:
 *  https://github.com/dwyl/english-words
 *  There are roughly 480,000 words in the dictionary
 */
import java.util.*;
import java.io.*;

public class Project1 {
    // Use a map to store the keys and the associated keys
    public Map<String, LinkedList<String>> hashmap = new HashMap<>();



    public void readData(String filename) {
        Scanner inFS = null;
        FileInputStream fileByteStream = null;

        try {
            // open the File and set delimiters
            fileByteStream = new FileInputStream(filename);
            inFS = new Scanner(fileByteStream);
            inFS.useDelimiter("[,\r\n]+");

            // continue while there is more data to read
            while(inFS.hasNext()) {
                String word = inFS.next();
                String key = convertToKey(word);

                //Check if the key is already present in the map
                //If it is not present in the map
                //Instantiate a new list, add this word to the list
                //Add this key and this new list to the map
                    if(!hashmap.containsKey(key)){
                        LinkedList<String> wordSet = new LinkedList<>();
                        wordSet.add(key);
                        hashmap.put(key,wordSet);
                    }
                    //if it is present in the map
                    else{
                        // Retrieve the list associated with this key
                        // Add this word to that list
                        hashmap.get(key).add(word);
                    }

            }
            fileByteStream.close();

            // error while reading the file
        }catch(IOException error1) {
            System.out.println("Oops! Error related to: " + filename);
        }
    }

    //Visit all the entries in the map and find the largest list
    //Print the largest list
    // This method traverses the entire map and looks for the largest set
    // After traversing the entire map, it prints:
    //  - The key for the largest set
    //  - The size of the set
    //  - The elements in the set
    public void findMaxSize() {
        String maxKey = "";
        LinkedList<String> maxWordset = new LinkedList<>();
        for(Map.Entry <String,LinkedList<String>> entry: hashmap.entrySet()){
            int currentSize = entry.getValue().size();
            if(currentSize > maxWordset.size()){
                maxWordset = entry.getValue();
                maxKey = entry.getKey();
            }
        }

        System.out.println("Largest list of anagrams: ");
        System.out.print("Key: " + maxKey + "\t");
        System.out.println("Size: " + maxWordset.size() + " ");
        System.out.print("Elements: ");
        for(String word : maxWordset){
            System.out.print(word + " ");
        }
    }


   
    //Takes a string and converts it into an array of characters
    //Sorts array of chars, that way all anagrams will have the same key after being sorted 
    public String convertToKey(String word) {
        char [] chars = word.toCharArray();
        Arrays.sort(chars);
        String result = new String(chars);
        return result;
    }

    public static void main(String args[]) {
        Project1 p1 = new Project1();
        p1.readData("words.txt");
        p1.findMaxSize();
    }
}
