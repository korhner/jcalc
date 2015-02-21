grammar Expression;
prog: expr ;

expr: 	expr op=(MUL|DIV) expr			# mulDiv
	|	expr op=(ADD|SUB) expr			# addSub
	|	number							# constant
	|	'(' expr ')'					# parens
	;


number: SUB? (decimal | integer) ;
integer: DIGITS ;
decimal: DIGITS? '.' DIGITS ;


DIGITS: [0-9]+ ;
MUL : '*';
DIV : '/';
ADD : '+';
SUB : '-';



NEWLINE: '\r'? '\n' ;	// return new line to parser
WS: [ \t]+ -> skip ;	// skip whitespace