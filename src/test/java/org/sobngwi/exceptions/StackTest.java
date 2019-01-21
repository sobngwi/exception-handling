package org.sobngwi.exceptions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * Unit test for {@link BoundedStack}.
 */
public class StackTest
{

    private Stack stack;

    @Before
    public void setUp() {
        stack = BoundedStack.Make(2);
    }

    @Test
    public void newlyCreateStack_shouldBeEmpty()
    {
        assertTrue(stack.isEmpty());
        assertThat(0, equalTo(stack.getSize()));
    }

    @Test
    public void afterOnePush_stackSizeShouldBeOne() {

       stack.push(1);

        assertFalse(stack.isEmpty());
        assertThat(1, equalTo(stack.getSize()));
    }

    @Test
    public void popOperationOnStack_shouldDecrementedTheSize() {

        stack.push(1);
        stack.pop();

        assertTrue(stack.isEmpty());
        assertThat(0, equalTo(stack.getSize()));
    }

    @Test(expected = BoundedStack.OverFlowException.class)
    public void whenPushPassLimit_shouldOverFlow() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test(expected = BoundedStack.UnderFlowException.class)
    public void popEmptyStackShouldThrowUnderFlow() {
        stack.pop();
    }

    @Test
    public void whenOneIsPush_OneIsPop() {
        stack.push(1);
        assertThat(1, equalTo(stack.pop()));
    }

    @Test
    public void whenPushOneAndTwo_TwoAndOneArePopped() {
        stack.push(1);
        stack.push(2);
        assertThat(2, equalTo(stack.pop()));
        assertThat(1, equalTo(stack.pop()));
    }

    @Test(expected = Stack.IllegalCapacityException.class)
    public void whenCreatingStackWithNegativecapacity_shouldThrowIllegalCapacity() {
        BoundedStack.Make(-2);
    }

    @Test(expected= BoundedStack.ZerocapacityException.class)
    public void whenCreatingStackWithZeroCapacity_pushShouldOverFlow_aka_nullPattern() {
        stack = BoundedStack.Make(0);
        stack.push(1);

    }

    @Test
    public void whenPush_shouldTop() {
        stack.push(100);
        assertThat(100, equalTo(stack.top()));
    }

    @Test(expected = Stack.ZerocapacityException.class)
    public void withZerocapacity_shouldThrowEmpty() {
        stack.top();
    }

    @Test(expected = Stack.ZerocapacityException.class)
    public void withZeroCapacityStack_topThrowsEmpty() {
        stack = BoundedStack.Make(0);
        stack.top();
    }

    @Test
    public void givenStackWithOneTwoPushed_findOneAndTwo() {
        stack.push(1);
        stack.push(2);
        assertThat(1, equalTo(stack.findIndexOfElement(1).get()));
        assertThat(0, equalTo(stack.findIndexOfElement(2).get()));
    }

    @Test
    public void whenIndexNotFound_shouldReturnAnEmptyOptional() {
        stack.push(1);
        assertThat(Optional.empty(), equalTo(stack.findIndexOfElement(2)));
    }
}
