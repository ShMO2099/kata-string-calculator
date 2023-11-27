package com.katas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StringCalculator {


  public int add(final String input) throws Exception {
    return input.isEmpty() ? 0: sum(input);
  }

  private int sum(final String input) throws Exception {
    String substring = input;
    String delimiter;
    ArrayList<String> delimiters = new ArrayList<>();

    if(input.startsWith("//"))
    { // get string between // and the first \n
      substring = substring.substring(2);
      delimiter = substring.split("\n")[0];
      substring = substring.substring(delimiter.length() + 1);
      // build up list of delimiters looking into all boxes []
      if(!delimiter.startsWith("["))
      {
        delimiters.add(delimiter);
      }
      while(delimiter.startsWith("["))
      {
        delimiter = delimiter.substring(1);
        String squareDelimiter = delimiter.split("]")[0];
        delimiter = delimiter.substring(squareDelimiter.length());
        delimiters.add(squareDelimiter);
      }
      for(String d: delimiters)
      {
        substring = substring.replace(d,"\n");
      }
    }

    String[] strings = substring.split("\n|,");

    String collect = Arrays.stream(strings)
        .mapToInt(Integer::valueOf)
        .filter(n -> n < 0)
        .mapToObj(String::valueOf)
        .collect(Collectors.joining(" "));
    if(!collect.isEmpty())
    {
      throw new Exception("Negatives not allowed: "+collect);
    }
    return Arrays.stream(strings)
        .mapToInt(Integer::valueOf)
        .filter(n -> n <1001) // ignore numbers more than 1000
        .sum();
  }

}
