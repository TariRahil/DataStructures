package com.tarrah;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Expression {
    private final List<Character> rightBrackets = Arrays.asList(')' , ']' , '}' , '>');
    private final List<Character> leftBrackets = Arrays.asList('(' , '[' , '{' , '<');
    private String str;
    Stack<Character> stack = new Stack<>();

    public Expression(String str){
        this.str = str;
    }

    public boolean isBalanced(){
        for (char ch : str.toCharArray()) {
            if (isLeftBrackets(ch))
                stack.push(ch);
            if (isRightBrackets(ch)) {
                if (stack.empty()) return false;
                    var top = stack.pop();
                    if (bracketsMatch(ch , top))
                        return false;
            }
        }
        return stack.empty();
    }

    private boolean bracketsMatch(char right, char left) {
       return rightBrackets.indexOf(right) != leftBrackets.indexOf(left);
    }

    private boolean isRightBrackets(char ch) {
        return rightBrackets.contains(ch);
    }

    private boolean isLeftBrackets(char ch) {
         return leftBrackets.contains(ch);
    }

}
