package org.sobngwi.exceptions;

import java.util.Optional;

public interface Stack {
    boolean isEmpty();

    int getSize();

    void push(int i);

    int pop();

    int top();

    Optional<Integer> findIndexOfElement(int i);

    class IllegalCapacityException extends RuntimeException{ }
    class ZerocapacityException extends RuntimeException{}
}
