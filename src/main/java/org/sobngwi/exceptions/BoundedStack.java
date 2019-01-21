package org.sobngwi.exceptions;

import java.util.Optional;

public class BoundedStack implements Stack {

    private final int capacity;
    private int size =0;
    private int elements[];

    public static Stack Make(int capacity ){
        if ( capacity < 0) throw new IllegalCapacityException();
        if ( capacity == 0) return new ZeroCapacity();
        return new BoundedStack(capacity);
    }
    private static class ZeroCapacity implements Stack {
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public int getSize() {
            return 0;
        }

        @Override
        public void push(int i) {
            throw new ZerocapacityException();
        }

        @Override
        public int pop() {
            throw new UnderFlowException();
        }

        @Override
        public int top() {
            throw new ZerocapacityException();
        }

        @Override
        public Optional<Integer> findIndexOfElement(int i) {
            throw new ZerocapacityException();
        }
    }

    public static class  OverFlowException extends RuntimeException { }
    public static class  UnderFlowException extends RuntimeException { }

    private BoundedStack(int capacity) {
        this.capacity= capacity;
        elements = new int[capacity];
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void push(int i) {
        if ( capacity == 0) throw new ZerocapacityException();
        else if ( size < capacity) {
            this.elements[size] = i;
            ++this.size;
        }
        else throw new OverFlowException();
    }

    @Override
    public int pop() {
        
        if(size == 0) throw new UnderFlowException();
        --this.size;
        return elements[size];
    }

    @Override
    public int top() {
        if(isEmpty()) throw new ZerocapacityException();
        return elements[--size];
    }

    @Override
    public Optional<Integer> findIndexOfElement(int element) {
        for (int index = size -1  ; index > -1 ; index--)
            if( elements[index] == element) return Optional.ofNullable((size - 1) - index) ;
        return  Optional.empty();
    }
}
