public class PostfixEvaluationWithLinkedList {

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private static class Stack {
        private Node top;

        public boolean isEmpty() {
            return top == null;
        }

        public void push(int value) {
            Node newNode = new Node(value);
            newNode.next = top;
            top = newNode;
        }

        public int pop() {
            if (isEmpty()) throw new IllegalStateException("Stack is empty");
            int value = top.value;
            top = top.next;
            return value;
        }

        public int peek() {
            if (isEmpty()) throw new IllegalStateException("Stack is empty");
            return top.value;
        }
    }

    public static int evaluatePostfix(String expression) {
        Stack stack = new Stack();

        String[] tokens = expression.split("\\s+");

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } else {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand1 - operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    case "/":
                        if (operand2 == 0)
                            throw new IllegalArgumentException("Cannot divide by zero");
                        stack.push(operand1 / operand2);
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String postfix = "3 4 + 5 6 * 9 2 - + * ";
        int result = evaluatePostfix(postfix);
        System.out.println("The result of the postfix expression is: " + result);
    }
}
