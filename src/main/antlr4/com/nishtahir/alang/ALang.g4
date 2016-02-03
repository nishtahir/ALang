grammar ALang;

compilationUnit
    :   annotationDeclaration? statements EOF
    ;

annotationDeclaration
    :   '@' (importDeclaration)
    ;

importDeclaration
    :   'import' importReferenceList
    ;

importReferenceList
    :   importReference ( ',' importReference)*
    ;

importReference
    :   Identifier
    ;

block
    : '{' blockStatements* '}'
    ;

blockStatements
    :   statement
    ;

statement
    :   assignment
    |   forLoop
    |   whileLoop
    |   ifStatement
    |   printStatement
    |   function
    ;

statements
    : statement*
    | '{' statement* '}'
    ;

ifStatement
    : 'if' expression statements ('else' statements)?
    ;

assignment
    :   Identifier ':=' expression      #identifierAssignment
    |   index      ':=' expression      #indexAssignment
    ;

// for (i := 1 ~ 10)
forLoop
    : 'for' '('Identifier ':=' range ')' statements
    ;

whileLoop
    : 'while' '(' expression ')' statements
    ;

range
    :   expression RNG expression
    ;

printStatement
    :   'print'   ('(' parameter ')' | parameter )    #print
    |   'println' ('(' parameter ')' | parameter )    #printLine
    ;

parameter
    : literal
    | expression
    | Identifier
    ;

parameterList
    : '(' parameter (',' parameter)* ')'
    ;

expression
    :   expression op=(INCR|DECR)            #exprIncrDecr
    |   expression op=(MULT|DIV) expression    #exprMultDiv
    |   expression op=(ADD|SUB) expression     #exprAddSub
    |   expression op=(GTR | GTRE | LESS | LESSE | EQL | NEQL) expression     #exprBoolean
    |   op=(MIN|MAX)  expression expression             #exprMinMax
    |   '(' expression ')'                  #exprBracket
    |   expressionList                      #exprList
    |   index                               #exprIndex
    |   literal                             #literalExpression
    |   Identifier                          #identifierExpression
    ;

documentation
    :   DOC_COMMENT_COMMENT
    ;

function
    : documentation? Identifier parameterList ':' Identifier block
    ;

INCR    :   '++';
DECR    :   '--';
MULT    :   '*' ;
DIV     :   '/' ;
ADD     :   '+' ;
SUB     :   '-' ;

GTR     :   '>' ;
GTRE    :   '>=';
LESS    :   '<' ;
LESSE   :   '<=';
EQL     :   '=' ;
NEQL    :   '!=';

RNG     :   '~' ;
MIN     :   'min'|'MIN' ;
MAX     :   'max'|'MAX' ;

expressionList
    :   '[' expression (',' expression)* ']'
    ;

index
    :   Identifier '^' expression
    ;

literal
    :   NumberLiteral               #literalNumber
    |   StringLiteral               #literalString
    |   Identifier                  #literalIdentifier
    ;

Identifier
    :   LETTERS (LETTERS|NumberLiteral)*
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

fragment
SignedInteger
    :   Sign? NumberLiteral
    ;

fragment
Sign
    :   [+-]
    ;

LETTERS
    :   [a-zA-Z0-9$_]+
    ;

NumberLiteral
    :   [0-9]+
    ;

BooleanLiteral
    :   'true'
    |   'false'
    ;

WS  :  [ \n\r\t\u000C]+ -> skip
    ;

SINGLE_LINE_COMMENT
    :  '//'  ~[\r\n]* -> skip
    ;

DOC_COMMENT_COMMENT
    :   '/**' .*? '*/'
    ;

MULTI_LINE_COMMENT
    :   '/*' .*? '*/' -> skip
    ;


