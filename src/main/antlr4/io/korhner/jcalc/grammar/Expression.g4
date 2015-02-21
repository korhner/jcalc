grammar Expression;
prog: expr ;

expr: 	expr op=(MUL|DIV) expr			# mulDiv
	|	expr op=(ADD|SUB) expr			# addSub
	|	NUMBER						# number
	|	'(' expr ')'				# parens
	;

MUL : '*';
DIV : '/';
ADD : '+';
SUB : '-';

NUMBER: [0-9]+ ;
NEWLINE: '\r'? '\n' ;	// return new line to parser
WS: [ \t]+ -> skip ;	// skip whitespace