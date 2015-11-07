grammar ALang;

@header {
    import com.nishtahir.evaluator.ValueEvaluator;
    import com.nishtahir.value.IntegerValue;
    import com.nishtahir.value.StringValue;
    import com.nishtahir.value.Value;
    import com.nishtahir.utils.*;
}

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

forLoop
    : 'for' '('Identifier ':=' range ')' statements
    ;

range
    :   expression '~' expression
    ;

printStatement
    :   'print' '(' parameter ')'
    |   'println' '(' parameter ')'
    ;

parameter returns [String value]
    : literal           {$value = $literal.value.toString();}
    | expression        {$value = $expression.value.toString();}
    | Identifier        {$value = null; }
    ;

expression returns [Value value]
//    :   expression ('++' | '--')
    :   expression ('*'|'/') expression
    |   a=expression c=('+'|'-') b=expression     { $value = ValueEvaluator.evaluate($a.value, $b.value, $c.text.trim());}
    |   expression ('<=' | '>=' | '>' | '<' | '=') expression
    |   '(' expression ')'
    |   expressionList
    |   index
    |   literal                 { $value = $literal.value; }
    ;

expressionList
    :   '[' expression (',' expression)* ']'
    ;

index
    :   Identifier '^' expression
    ;

literal returns [Value value]
    :   NUMBERS                             { $value = new IntegerValue($NUMBERS.text.trim()); }
    |   StringLiteral                       { $value = new StringValue(StringUtilities.clean($StringLiteral.text)); }
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