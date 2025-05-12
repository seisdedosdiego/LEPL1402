package algorithms;

/**
 * This class can be used to build simple arithmetic expression
 * with binary operator +,-,* and involving one variable 'x'.
 *
 * The expression can be
 * 1) evaluated by replacing the variable x with a specific value
 * 2) derivated to obtain a new expression
 *
 * You must modify this class to make it work
 * You can/should extend this class with inner classes the way you want.
 * You can also modify it but you are not allowed to modify the signature
 * of existing methods
 *
 * As a reminder, the formulas for the derivations as are followed
 *  - (f + g)' = f' + g'
 *  - (f*g)' = f'g + fg'
 *  - (x)' = 1
 *  - (C)' = 0 with C a constant
 */
public abstract class Expression {

     /**
     * Creates the basic variable expression 'x'
     * @return the expression 'x'
     */
     public static Expression x() {
          return X.INSTANCE;
     }

     private static class X extends Expression {
          private static final X INSTANCE = new X(); 
          
          private X() {
          }

          @Override
          public double evaluate(double xValue) {
               return xValue;
          }

          @Override
          public Expression derivate() {
               return value(1);
          }

     }

     /**
     * Creates the basic constant expression 'v'
     * @return the expression 'v'
     */
     public static Expression value(double v) {
          return new Value(v);
     }

     private static class Value extends Expression {
          private final double value;

          private Value(double v){
               this.value= v;
          }

          @Override
          public double evaluate(double xValue) {
               return value;
          }

          @Override 
          public Expression derivate() {
               return value(0);
          }
     }

     /**
     * Creates the binary expression 'this + r'
     * @param r the right operator
     * @return the binary expression 'this + r'
     */
     public Expression plus(Expression r) {
          return new binaryExpression('+', this, r);
     }

     /**
     * Creates the binary expression 'this - r'
     * @param r the right operator
     * @return the binary expression 'this - r'
     */
     public Expression minus(Expression r) {
          return new binaryExpression('-', this, r);
     }

     /**
     * Creates the binary expression 'this * r'
     * @param r the right operator
     * @return the binary expression 'this * r'
     */
     public Expression mul(Expression r) {
          return new binaryExpression('*', this, r);
     }

     private static class binaryExpression extends Expression {

          private final char op;
          private final Expression r1;
          private final Expression r2;

          private binaryExpression(char op, Expression r1, Expression r2) {
               this.op = op;
               this.r1 = r1;
               this.r2 = r2;
          }

          @Override
          public double evaluate(double xValue) {
               double r1Value = r1.evaluate(xValue);
               double r2Value = r2.evaluate(xValue);

               if (op == '+') {
                    return r1Value + r2Value;
               } else if (op == '-') {
                    return r1Value - r2Value;
               } else if (op == '*') {
                    return r1Value*r2Value;
               } else {
                    throw new IllegalArgumentException();
               }
          }

          @Override
          public Expression derivate() {
               Expression r1Derivate = r1.derivate();
               Expression r2Derivate = r2.derivate();

               if (op == '+') {
                    return r1Derivate.plus(r2Derivate);
               } else if (op == '-') {
                    return r1Derivate.minus(r2Derivate);
               } else if (op == '*') {
                    return r1Derivate.mul(r2).plus(r1.mul(r2Derivate));
               } else {
                    throw new IllegalArgumentException();
               }
          }
     }

     /**
     * Evaluate the expression with fixed value for x
     * @param xValue the value taken by x for the evaluation
     * @return the evaluation of the expression considering x=xValue
     */
     public abstract double evaluate(double xValue);

     /**
     * Derivate the expression wrt to 'x'
     * @return the derivative of the expression with respect to 'x'
     */
     public abstract Expression derivate();

}
