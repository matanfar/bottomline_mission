package com.example.bottomline.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class Trie
{
    private TrieNode root;
    ArrayList<String> words;
    TrieNode prefixRoot;
    String curPrefix;

    public Trie()
    {
        root = new TrieNode();
        words  = new ArrayList<String>();
    }

    // Inserts a word into the trie.
    public void insert(String word)
    {
        HashMap<Character, TrieNode> children = root.children;

        TrieNode crntparent;

        crntparent = root;

        //cur children parent = root

        for(int i=0; i<word.length(); i++)
        {
            char c = word.charAt(i);

            TrieNode t;
            if(children.containsKey(c)){ t = children.get(c);}
            else
            {
                t = new TrieNode(c);
                t.parent = crntparent;
                children.put(c, t);
            }

            children = t.children;
            crntparent = t;

            //set leaf node
            if(i==word.length()-1)
                t.isLeaf = true;
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word)
    {
        TrieNode t = searchNode(word);
        if(t != null && t.isLeaf){return true;}
        else{return false;}
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix)
    {
        if(searchNode(prefix) == null) {return false;}
        else{return true;}
    }

    public TrieNode searchNode(String str)
    {
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for(int i=0; i<str.length(); i++)
        {
            char c = str.charAt(i);
            if(children.containsKey(c))
            {
                t = children.get(c);
                children = t.children;
            }
            else{return null;}
        }

        prefixRoot = t;
        curPrefix = str;
        words.clear();
        return t;
    }


    ///////////////////////////


    public void wordsFinderTraversal(TrieNode node, int offset)
    {
        //  print(node, offset);

        if(node.isLeaf==true)
        {
            //println("leaf node found");

            TrieNode altair;
            altair = node;

            Stack<String> hstack = new Stack<String>();

            while(altair != prefixRoot)
            {
                //println(altair.c);
                hstack.push( Character.toString(altair.c) );
                altair = altair.parent;
            }

            String wrd = curPrefix;

            while(hstack.empty()==false)
            {
                wrd = wrd + hstack.pop();
            }

            //println(wrd);
            words.add(wrd);

        }

        Set<Character> kset = node.children.keySet();
        //println(node.c); println(node.isLeaf);println(kset);
        Iterator itr = kset.iterator();
        ArrayList<Character> aloc = new ArrayList<Character>();

        while(itr.hasNext())
        {
            Character ch = (Character)itr.next();
            aloc.add(ch);
            //println(ch);
        }

        // here you can play with the order of the children

        for( int i=0;i<aloc.size();i++)
        {
            wordsFinderTraversal(node.children.get(aloc.get(i)), offset + 2);
        }

    }


    public List<String> displayFoundWords()
    {
        System.out.println("_______________");
        for(int i=0;i<words.size();i++)
        {
            System.out.println(words.get(i));
        }
        System.out.println("________________");
        return words;
    }


}//


