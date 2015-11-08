grammar ALang;

compilationUnit
    :   statements EOF
    ;

statement
    :   assignment
    |   forLoop
    |   ifStatement
    |   printStatement
    ;

statements
    : statement*
    | '{' statement* '}'
    ;

ifStatement
    : 'if' expression statements ('else' statements)?
    ;

assignment
    :   Identifier ':=' expression
    ;

// for (i := 1 ~ 10)
forLoop
    : 'for' '('Identifier ':=' range ')' statements
    ;

range
    :   expression RNG expression
    ;

printStatement
    :   'print' '(' parameter ')'       #print
    |   'println' '(' parameter ')'     #printLine
    ;

parameter
    : literal
    | expression
    | Identifier
    ;

expression
    :   expression op=(INCR|DECR)            #exprIncrDecr
    |   expression op=(MULT|DIV) expression    #exprMultDiv
    |   expression op=(ADD|SUB) expression     #exprAddSub
    |   expression op=('>' | '<' | '=') expression     #exprBoolean
    |   '(' expression ')'                  #exprBracket
    |   expressionList                      #exprList
    |   index                               #exprIndex
    |   literal                             #literalExpression
    ;

INCR    :   '++';
DECR    :   '--';
MULT    :   '*';
DIV     :   '/';
ADD     :   '+';
SUB     :   '-';

GTR     :   '>';
LESS    :   '<';
EQL     :   '=';

RNG     :   '~';

expressionList
    :   '[' expression (',' expression)* ']'
    ;

index
    :   Identifier '^' expression
    ;

literal
    :   NUMBERS                     #literalNumber
    |   StringLiteral               #literalString
    |   Identifier                  #literalIdentifier
    ;

Identifier
    :   LETTERS (LETTERS|NUMBERS)*
    ;

StringLiteral
    :   '"' StringCharacters? '"'
    ;

fragment
StringCharacters
    :   StringCharacter+
    ;

fragment
StringCharacter
    :   ~["\\]
    ;

LETTERS
    :   [a-zA-Z]+
    ;

NUMBERS
    :   [0-9]+
    ;

WS  :  [ \n\r\t\u000C]+ -> skip
    ;

LINE_COMMENT
    :  '#'  ~[\r\n]* -> skip
    ;