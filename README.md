# PLC Final Exam

## 5. Denotational semantics to define selection statement
If Statement:
```txt
if(<booleanExpression>) <statement> [else <statement>]


M_if(if (<booleanExpression>) <statement>, s):
            if M_b(<booleanExpression>, s) == error
                        return error;
                        
            if M_b(<booleanExpression>, s)
            {
              if M_statement(<statement>, s) == error
                   return error;
             }
              return M_statement(<statement>, s)


M_if(if (<booleanExpression>) <statement1> else <statement2>, s):
             if M_b(<booleanExpression>, s) == error
                        return error;
             if M_b(<booleanExpression>, s)
             {
               if M_statement(<statement1>, s) == error
                        return error;
              }
              return M_statement(<statement1>, s)
              
              else
              {
               if M_statement(<statement2>, s) == error
                        return error;
              }
              return M_statement(<statement2>, s)
```

## 6. Denotational semantics to define loop statement
While loop:
```txt
while(<booleanExpression>) <statement>

M_while(while(<booleanExpression>) <statement>):
            if M_b(<booleanExpression>) == error;
                        return error;
            if M_b(<booleanExpression>)
            {
             if M_statement(<statement>) == error
                        return error;
             } 
             return M_statement(<statement>)
```


## 7. Denotational semantics to define Expr statement
Expr statement:
```txt
expr(<variable1> (+|-|*|/) <variable2>)

M_expr(<variable1> (+|-|*|/) <variable2>):
            expr M_b(<variable1> (+|-|*|/) <variable2>) == error;
                        return error;
             expr M_b(<variable1> (+|-|*|/) <variable2>)
            {
             if M_statement(<variable1> (+|-|*|/) <variable2>) == error
                        return error;
             } 
             return M_statement(<variable1> (+|-|*|/) <variable2>)
```


## 8. Denotational semantics to define Expr statement so that it can return a boolean solution
Expr statement:
```txt
expr(<variable1> (+|-|*|/) <variable2>)

M_expr(<variable1> (+|-|*|/) <variable2>):
            expr M_b(<variable1> (+|-|*|/) <variable2>) == error;
                        return false;
             expr M_b(<variable1> (+|-|*|/) <variable2>) == error;
                        return false;
             expr M_b(<variable1> (+|-|*|/) <variable2>)
            {
             expr M_statement(<variable1> (+|-|*|/) <variable2>) == error
                        return false;
             } 
             return M_statement(<variable1> (+|-|*|/) <variable2>)
```


## 9. Attribute Grammar for assignment statement:

```txt
Statement -> IDENTIFIER = Expression

Expression -> Expression + Expression
            | Expression * Expression
            | IDENTIFIER
            | REAL_LITERAL
            | NATURAL_LITERAL
            | BOOL_LITERAL
            | CHAR_LITERAL
            | STRING_LITERAL
            | UNARY_NEGATION

IDENTIFIER.type -> SymbolTable.lookup(IDENTIFIER.lexeme).type
REAL_LITERAL.type -> REAL
NATURAL_LITERAL.type -> NATURAL
BOOL_LITERAL.type -> BOOLEAN
CHAR_LITERAL.type -> CHAR
STRING_LITERAL.type -> STRING


Expression.type -> 
  if (Expression1.type == STRING and Expression2.type == STRING)
    then STRING
  else if (Expression1.type == STRING and Expression2.type == INTEGER)
    then STRING
  else if (Expression1.type == INTEGER and Expression2.type == STRING)
    then STRING
  else if (Expression1.type == INTEGER and Expression2.type == INTEGER)
    then INTEGER
  else if (Expression1.type == BOOLEAN and Expression2.type == INTEGER)
    then INTEGER
  else if (Expression1.type == INTEGER and Expression2.type == BOOLEAN)
    then INTEGER
  else if (Expression1.type == CHAR and Expression2.type == INTEGER)
    then INTEGER
  else if (Expression1.type == INTEGER and Expression2.type == CHAR)
    then INTEGER
  else if (Expression1.type == INTEGER and Expression2.type == REAL)
    then REAL
  else
    ERROR
    
    
UNARY_NEGATION.type -> Expression.type


Statement.type ->
  if (IDENTIFIER.type == STRING and Expression.type == STRING)
    then STRING
  else if (IDENTIFIER.type == STRING and Expression.type == NATURAL)
    then NATURAL
  else if (IDENTIFIER.type == NATURAL and Expression.type == BOOLEAN)
    then NATURAL
  else if (IDENTIFIER.type == BOOLEAN and Expression.type == NATURAL)
    then BOOLEAN
  else if (IDENTIFIER.type == NATURAL and Expression.type == CHAR)
    then INTEGER
  else if (IDENTIFIER.type == INTEGER and Expression.type == BOOLEAN)
    then INTEGER
  else if (IDENTIFIER.type == CHAR and Expression.type == INTEGER)
    then INTEGER
  else if (IDENTIFIER.type == INTEGER and Expression.type == CHAR)
    then INTEGER
  else if (IDENTIFIER.type == REAL and Expression.type == REAL)
    then REAL
  else if (IDENTIFIER.type != Expression.type)
    then ERROR
  else
    IDENTIFIER.type

Expression.error -> 
  if (Expression1.type == INTEGER and Expression2.type == INTEGER and (Expression1.value == 0 or Expression2.value == 0))
    then DIVIDE_BY_ZERO
  else if (Expression1.type == INTEGER and Expression2.type == INTEGER and Expression1.value == 0 and Expression2.value == 0)
    then MODULO_BY_ZERO
  else
    Expression1.error or Expression2.error

UNARY_NEGATION.error -> Expression.error

Statement.error -> Expression.error
```
            
            
## 10. Three syntactically valid assignment statements with atleast 7 tokens:

```txt
int a = b + 2 - c;
```
The above assignment statement passes the semantic rules as it assigns a value of int to a data type of 'int'.

```txt
int s;
int u = s + true + 5;
```
The above assignment statement fails the semantic rules because a type 'boolean' cannot be assigned to the type 'int'.

```txt
String plc = "Final" + "Exam";
```
The above assignment statement passes the semantic rules as it concatenates the strings and assigns it to the variable of type 'String'.


## 11. Axiomatic Semantics 
### a. a = 2 * (b - 1) - 1  {a > 0}
```txt
   2 * (b - 1) - 1 > 0
   2 * (b - 1) > 1
   (b - 1) > 1/2
   b > 1/2 + 1
   b > 3/2
   b >= 2
The weakest precondition is a > 0, b >= 2
```
   
### b. if (x < y) 
###     x = x + 1;
###    else 
###     x = 3 * x;
###    {x < 0}
```txt
if:
   x + 1 < 0
   x < -1
else:
   3 * x < 0
   x < 0
The weakest precondition is x < -1
```


### c. y = a * 2 * (b - 1) - 1 
###    if (x < y)
###     x = y + 1;
###    else 
###     x = 3 * x;
###    {x < 0}
```txt
   y + 1 < 0
   y < -1
   
    a * 2 * (b - 1) - 1 < -1
   a * 2 * (b - 1) < 0
   a < 0
   
   a * 2 * (b - 1) - 1 < -1
   a * 2 * (b - 1) < 0
   a * (b - 1) < 0
   b < 1
   
The weakest precondition is x < 0, y < -1, a < 0, b < 1
```
  
### d. a = 3 * (2 * b + a);
###    b = 2 * a - 1
###    {b > 5}
```txt
   2 * a - 1 > 5
   2 * a > 6
   a > 6/2
   a > 3
   
   a = 3 * (2 * b + a)
   a/3 = 2 * b + a
   a/3 - a = 2 * b
   -2a/3 = 2 * b
   b = -a/3
   a = -3b
   
   Since a > 3,
   -3b > 3
   b < -1
   
The weakest precondition is a > 3, b < -1
```
