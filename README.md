# PLC Final Exam

## 9. Attribute Grammar for assignment statement:
``txt
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

AssignmentStatement.type ->
  if (IDENTIFIER.type == STRING and Expression.type == STRING)
    then STRING
  else if (IDENTIFIER.type == STRING and Expression.type == INTEGER)
    then STRING
  else if (IDENTIFIER.type == INTEGER and Expression.type == STRING)
    then STRING
  else if (IDENTIFIER.type == INTEGER and Expression.type == INTEGER)
    then INTEGER
  else if (IDENTIFIER.type == BOOLEAN and Expression.type == INTEGER)
    then INTEGER
  else if (IDENTIFIER.type == INTEGER and Expression.type == BOOLEAN)
    then INTEGER
  else if (IDENTIFIER.type == CHAR and Expression.type == INTEGER)
    then INTEGER
  else if (IDENTIFIER.type == INTEGER and Expression.type == CHAR)
    then INTEGER
  else if (IDENTIFIER.type == INTEGER and Expression.type == REAL)
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

AssignmentStatement.error -> Expression.error
```
            


## 10. Three syntactically valid assignment statements with atleast 7 tokens:
```txt
int a = b + 2 - c;
```
The above assignment statement passes the semantic rules. 

```txt
int s;
int u = s + true + 5;
```
The above assignment statement fails the semantic rules because a type 'boolean' cannot be assigned to the type 'int'.

```txt
String plc = "Final" + "Exam";
```
The above assignment statement passes the semantic rules.


## 11. Axiomatic Semantics 
