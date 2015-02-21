grammar Expression;
prog: expr ;

expr: 	expr mulPrecedence expr		# mulPrec
	|	expr addPrecedence expr		# addPrec
	|	number						# constant
	|	'(' expr ')'				# parens
	;



number: MINUS? (decimal | integer) ;
integer: DIGITS ;
decimal: DIGITS? '.' DIGITS ;

mulPrecedence: op=(MUL | DIV | MODUO) ;
addPrecedence: op=(PLUS | MINUS) ;

DIGITS: [0-9]+ ;
MUL: '*' ;
DIV: '/' ;
PLUS: '+' ;
MINUS: '-' ;
MODUO: '%' ; 



NEWLINE: '\r'? '\n' ;	// return new line to parser
WS: [ \t]+ -> skip ;	// skip whitespace